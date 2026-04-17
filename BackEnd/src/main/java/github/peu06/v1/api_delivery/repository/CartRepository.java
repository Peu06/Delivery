package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByClientsId(Long clientId);
}
