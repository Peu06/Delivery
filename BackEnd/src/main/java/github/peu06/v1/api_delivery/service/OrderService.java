package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.*;
import github.peu06.v1.api_delivery.repository.AddressRepository;
import github.peu06.v1.api_delivery.repository.ClientsRepository;
import github.peu06.v1.api_delivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientsRepository clientsRepository;
    private final AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository, ClientsRepository clientsRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.clientsRepository = clientsRepository;
        this.addressRepository = addressRepository;
    }

    public Order create(Order order){

        if (order.getClients() == null || order.getClients().getId() == null) {
            throw new RuntimeException("Cliente é obrigatório");
        }

        if (order.getAddress() == null || order.getAddress().getId() == null) {
            throw new RuntimeException("Endereço é obrigatório");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("Pedido deve ter pelo menos um item");
        }

        Long clientId = order.getClients().getId();

        Clients client = clientsRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        order.setClients(client);

        Long addressId = order.getAddress().getId();

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        if (!address.getClients().getId().equals(client.getId())) {
            throw new RuntimeException("Endereço não pertence ao cliente");
        }

        order.setAddress(address);

        order.setData(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {

            item.setOrder(order);

            BigDecimal itemTotal = item.getPrecoBase()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));

            if (item.getOptions() != null){
                for (OrderItemOption option : item.getOptions()){
                    option.setItem(item);

                    itemTotal = itemTotal.add(
                            option.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()))
                    );
                }
            }

            total = total.add(itemTotal);
        }

        order.setTotal(total);

        return orderRepository.save(order);
    }

    public Order read(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}
