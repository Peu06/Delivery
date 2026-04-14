package github.peu06.v1.api_delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "OPCAO_PEDIDO_ITEM")
@NoArgsConstructor
@Getter
@Setter
public class OrderItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;

    @JsonBackReference("item-option")
    @ManyToOne
    private OrderItem item;
}
