package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
