package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.ProductVariation;
import github.peu06.v1.api_delivery.service.ProductVariationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductVariationController {
    @RestController
    @RequestMapping("/variations")
    public class ProductVariationController {

        private final ProductVariationService service;

        public ProductVariationController(ProductVariationService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<ProductVariation> create(
                @RequestParam Long productId,
                @RequestBody ProductVariation variation
        ) {
            return ResponseEntity.ok(service.create(productId, variation));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ProductVariation> update(
                @PathVariable Long id,
                @RequestBody ProductVariation variation
        ) {
            return ResponseEntity.ok(service.update(id, variation));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
    }
}
