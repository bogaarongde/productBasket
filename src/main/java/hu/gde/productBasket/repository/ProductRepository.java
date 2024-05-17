package hu.gde.productBasket.repository;


import hu.gde.productBasket.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long > {
}
