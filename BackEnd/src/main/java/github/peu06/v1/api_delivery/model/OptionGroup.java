package github.peu06.v1.api_delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "GRUPO_OPCIONAL")
@NoArgsConstructor
@Getter
@Setter
public class OptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer minEscolhas;
    private Integer maxEscolhas;

    @JsonBackReference("product-group")
    @ManyToOne
    private Product product;

    @JsonManagedReference("group-option")
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> options;
}
