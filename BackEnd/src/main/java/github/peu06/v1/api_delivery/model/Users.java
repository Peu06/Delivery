package github.peu06.v1.api_delivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUsers role;
}
