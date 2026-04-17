package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Cart;
import github.peu06.v1.api_delivery.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartRepository {
    private final CartService service;

    public CartRepository(CartService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam Long clientId) {
        return ResponseEntity.ok(service.getOrCreate(clientId));
    }

    @GetMapping("/by-client")
    public ResponseEntity<Cart> findByClient(@RequestParam Long clientId) {
        return ResponseEntity.ok(service.findByClient(clientId));
    }

    @PutMapping("/clear")
    public ResponseEntity<Cart> clear(@RequestParam Long clientId) {
        return ResponseEntity.ok(service.clearCart(clientId));
    }
}
