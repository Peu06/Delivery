package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
