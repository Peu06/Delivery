package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {
}
