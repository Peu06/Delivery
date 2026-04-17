package github.peu06.v1.api_delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUTOS")
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private String urlImg;

    private boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) 
    private Set<ProductVariation> variation;

    @JsonManagedReference("product-group")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OptionGroup> groups;


    @Transient
    public List<ProductVariation> getVariationsWithDefault() {

        List<ProductVariation> list = new ArrayList<>();

        // 🔥 VARIAÇÃO PADRÃO (virtual)
        ProductVariation defaultVar = new ProductVariation();
        defaultVar.setId(0L); // padrão
        defaultVar.setNome("Padrão");
        defaultVar.setPreco(this.preco);
        defaultVar.setProduct(this);

        list.add(defaultVar);

        if (variation != null) {
            list.addAll(variation);
        }

        return list;
    }
}
