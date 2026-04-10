package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.OptionGroup;
import github.peu06.v1.api_delivery.model.Product;
import github.peu06.v1.api_delivery.model.ProductOption;
import github.peu06.v1.api_delivery.model.ProductVariation;
import github.peu06.v1.api_delivery.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        if (product.getVariation() != null) {
            for (ProductVariation variation : product.getVariation()) {
                variation.setProduct(product);
            }
        }
        if (product.getGroups() != null) {
            for (OptionGroup group : product.getGroups()) {
                group.setProduct(product);

                if (group.getOptions() != null) {
                    for (ProductOption option : group.getOptions()) {
                        option.setGroup(group);
                        option.setProduct(product);
                    }
                }
            }
        }
        return repository.save(product);
    }

    public Product read(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (product.getVariation() != null) {
            product.getVariation().size();
        }

        if (product.getGroups() != null) {
            product.getGroups().size();


            for (OptionGroup group : product.getGroups()) {
                if (group.getOptions() != null) {
                    group.getOptions().size();
                }
            }
        }
        return product;
    }

    public Product update(Long id, Product updateProduct){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setNome(updateProduct.getNome());
        product.setDescricao(updateProduct.getDescricao());
        product.setPreco(updateProduct.getPreco());
        product.setUrlImg(updateProduct.getUrlImg());
        product.setAtivo(updateProduct.isAtivo());

        product.getVariation().clear();

        if (updateProduct.getVariation() != null) {
            for (ProductVariation variation : updateProduct.getVariation()) {
                variation.setId(null);
                variation.setProduct(product);
                product.getVariation().add(variation);
            }
        }

        product.getOption().clear();

        if (updateProduct.getOption() != null) {
            for (ProductOption option : updateProduct.getOption()) {
                option.setId(null);
                option.setProduct(product);
                option.setGroup(null);
                product.getOption().add(option);
            }
        }

        product.getGroups().clear();

        if (updateProduct.getGroups() != null) {
            for (OptionGroup group : updateProduct.getGroups()) {

                group.setId(null);
                group.setProduct(product);

                if (group.getOptions() != null) {
                    for (ProductOption option : group.getOptions()) {
                        option.setId(null);
                        option.setProduct(product);
                        option.setGroup(group);
                    }
                }

                product.getGroups().add(group);
            }
        }

        return repository.save(product);
    }

    public void delete(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não localizado"));

        repository.delete(product);
    }
}