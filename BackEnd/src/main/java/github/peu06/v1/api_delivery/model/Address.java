package github.peu06.v1.api_delivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ENDERECO")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String lagradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Clients clients;
}
