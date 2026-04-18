package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Product;
import github.peu06.v1.api_delivery.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return service.create(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return service.readById(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public Product putProduct(@PathVariable Long id, @RequestBody Product product){
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        service.delete(id);
    }
}
