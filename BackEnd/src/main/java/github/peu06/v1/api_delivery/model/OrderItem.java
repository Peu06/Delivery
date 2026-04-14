package github.peu06.v1.api_delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ITENS_PEDIDO")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private BigDecimal precoBase;
    private Integer quantidade;

    @JsonBackReference("order-item")
    @ManyToOne
    private Order order;

    @JsonManagedReference("item-option")
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItemOption> options;

    @ManyToOne
    private ProductVariation productVariation;
}
