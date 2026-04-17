package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Cart;
import github.peu06.v1.api_delivery.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    public CartController(CartService service) {
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

    @PostMapping("/add")
    public ResponseEntity<Cart> addItem(
            @RequestParam Long clientId,
            @RequestParam Long productId,
            @RequestParam(required = false) Long variationId,
            @RequestParam Integer quantity,
            @RequestParam(required = false) List<Long> optionIds
    ) {
        return ResponseEntity.ok(
                service.addItem(clientId, productId, variationId, quantity, optionIds)
        );
    }

    @PutMapping("/item/quantity")
    public ResponseEntity<Cart> updateQuantity(
            @RequestParam Long clientId,
            @RequestParam Long itemId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(
                service.updateQuantity(clientId, itemId, quantity)
        );
    }

    @DeleteMapping("/item")
    public ResponseEntity<Cart> removeItem(
            @RequestParam Long clientId,
            @RequestParam Long itemId
    ) {
        return ResponseEntity.ok(
                service.removeItem(clientId, itemId)
        );
    }
}
