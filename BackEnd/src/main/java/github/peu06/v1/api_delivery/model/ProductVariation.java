package github.peu06.v1.api_delivery.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "VARIACAO_PRODUTO")
public class ProductsVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal preco;

    @ManyToOne
    private Products products;
}
