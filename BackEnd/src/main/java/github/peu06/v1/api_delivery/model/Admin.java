package github.peu06.v1.api_delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADMINS")
@NoArgsConstructor
@Getter
@Setter
public class Admin extends Users{

    private boolean usuarioAdmin = true;
}
