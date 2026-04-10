package github.peu06.v1.api_delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO_OPCIONAL")
@NoArgsConstructor
@Getter
@Setter
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;

    @JsonBackReference("product-option")
    @ManyToOne
    private Product product;

    @JsonBackReference("group-option")
    @ManyToOne
    private OptionGroup group;
}
