package github.peu06.v1.api_delivery.repository;

import github.peu06.v1.api_delivery.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"variation", "groups", "groups.options"})
    @Query("SELECT p FROM Product p")
    List<Product> findAllWithDetails();

}
