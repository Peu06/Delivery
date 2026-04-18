package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Product;
import github.peu06.v1.api_delivery.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product){
        return repository.save(product);
    }

    public Product readById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product update(Long id, Product updateProduct){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setNome(updateProduct.getNome());
        product.setDescricao(updateProduct.getDescricao());
        product.setUrlImage(updateProduct.getUrlImage());
        product.setPreco(updateProduct.getPreco());
        product.setDiponivel(updateProduct.isDiponivel());

        return repository.save(product);
    }

    public void delete(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        repository.delete(product);
    }
}