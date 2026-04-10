package github.peu06.v1.api_delivery.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO_OPCIONAL")
public class ProductsOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;

    @ManyToOne
    private Products product;

    @ManyToOne
    private OptionGroup group;
}
