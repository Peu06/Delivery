package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {
}
