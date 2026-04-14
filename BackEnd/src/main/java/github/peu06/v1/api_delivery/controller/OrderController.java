package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Order;
import github.peu06.v1.api_delivery.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order){
        return service.create(order);
    }

    @GetMapping
    public Order getOrder(Long id){
        return service.read(id);
    }
}
