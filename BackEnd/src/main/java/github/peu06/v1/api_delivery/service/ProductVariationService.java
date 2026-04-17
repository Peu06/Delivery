package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Product;
import github.peu06.v1.api_delivery.model.ProductVariation;
import github.peu06.v1.api_delivery.repository.ProductRepository;
import github.peu06.v1.api_delivery.repository.ProductVariationRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductVariationService {

    private final ProductVariationRepository variationRepository;
    private final ProductRepository productRepository;

    public ProductVariationService(ProductVariationRepository variationRepository,
                                   ProductRepository productRepository) {
        this.variationRepository = variationRepository;
        this.productRepository = productRepository;
    }

    public ProductVariation create(Long productId, ProductVariation variation) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        variation.setProduct(product);

        return variationRepository.save(variation);
    }

    public ProductVariation update(Long id, ProductVariation data) {

        ProductVariation variation = variationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variação não encontrada"));

        variation.setNome(data.getNome());
        variation.setPreco(data.getPreco());

        return variationRepository.save(variation);
    }

    public void delete(Long id) {
        variationRepository.deleteById(id);
    }
}
