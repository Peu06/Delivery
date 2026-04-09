package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
