package github.peu06.v1.api_delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CLIENTES")
@NoArgsConstructor
@Getter
@Setter
public class Clients extends Users{

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, length = 11)
    private String telefone;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    private List<Address> enderecos;

    @JsonIgnore
    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    private List<Order> order;
}
