package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.*;
import github.peu06.v1.api_delivery.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ClientsRepository clientsRepository;
    private final ProductRepository productRepository;
    private final ProductVariationRepository productVariationRepository;
    private final ProductOptionRepository productOptionRepository;

    public CartService(CartRepository cartRepository, ClientsRepository clientsRepository, ProductRepository productRepository, ProductVariationRepository productVariationRepository, ProductOptionRepository productOptionRepository) {
        this.cartRepository = cartRepository;
        this.clientsRepository = clientsRepository;
        this.productRepository = productRepository;
        this.productVariationRepository = productVariationRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public Cart getOrCreate(Long clientId) {

        return cartRepository.findByClientsId(clientId)
                .orElseGet(() -> {

                    Clients client = clientsRepository.findById(clientId)
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

                    Cart cart = new Cart();
                    cart.setClients(client);
                    cart.setTotal(BigDecimal.ZERO);

                    return cartRepository.save(cart);
                });
    }

    public Cart findByClient(Long clientId) {
        return cartRepository.findByClientsId(clientId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }

    public Cart clearCart(Long clientId) {

        Cart cart = getOrCreate(clientId);

        cart.getItems().clear();
        cart.setTotal(BigDecimal.ZERO);

        return cartRepository.save(cart);
    }

    public Cart updateQuantity(Long clientId, Long itemId, Integer quantity) {

        Cart cart = getOrCreate(clientId);

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantidade inválida");
        }

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setQuantidade(quantity);

        calculateTotal(cart);

        return cartRepository.save(cart);
    }

    private void calculateTotal(Cart cart) {

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()) {

            BigDecimal itemTotal = item.getPrecoBase();

            BigDecimal optionsTotal = item.getOptions() == null
                    ? BigDecimal.ZERO
                    : item.getOptions().stream()
                      .map(CartItemOption::getPreco)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

            itemTotal = itemTotal.add(optionsTotal);
            itemTotal = itemTotal.multiply(BigDecimal.valueOf(item.getQuantidade()));

            total = total.add(itemTotal);
        }

        cart.setTotal(total);
    }

    public Cart addItem(Long clientId, Long productId, Long variationId, Integer quantity, List<Long> optionIds) {

        Cart cart = getOrCreate(clientId);

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantidade inválida");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setNomeProduto(product.getNome());
        item.setQuantidade(quantity);

        BigDecimal basePrice = product.getPreco();

        // 🔥 TRATAMENTO DA VARIAÇÃO (PADRÃO = 0 ou null)
        if (variationId != null && variationId != 0) {

            ProductVariation variation = productVariationRepository.findById(variationId)
                    .orElseThrow(() -> new RuntimeException("Variação não encontrada"));

            // 🔥 VALIDA se pertence ao produto
            if (!variation.getProduct().getId().equals(productId)) {
                throw new RuntimeException("Variação não pertence ao produto");
            }

            item.setProductVariation(variation);
            basePrice = variation.getPreco();
        }

        item.setPrecoBase(basePrice);

        // 🔥 OPTIONS
        List<CartItemOption> options = new ArrayList<>();

        if (optionIds != null) {
            for (Long optionId : optionIds) {

                ProductOption option = productOptionRepository.findById(optionId)
                        .orElseThrow(() -> new RuntimeException("Opção não encontrada"));

                CartItemOption opt = new CartItemOption();
                opt.setItem(item);
                opt.setNome(option.getNome());
                opt.setPreco(option.getPreco());

                options.add(opt);
            }
        }

        item.setOptions(options);

        cart.getItems().add(item);

        calculateTotal(cart);

        return cartRepository.save(cart);
    }

    public Cart removeItem(Long clientId, Long itemId) {

        Cart cart = getOrCreate(clientId);

        boolean removed = cart.getItems().removeIf(item -> item.getId().equals(itemId));

        if (!removed) {
            throw new RuntimeException("Item não encontrado");
        }

        calculateTotal(cart);

        return cartRepository.save(cart);
    }
}
