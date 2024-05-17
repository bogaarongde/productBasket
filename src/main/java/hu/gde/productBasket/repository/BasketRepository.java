package hu.gde.productBasket.repository;


import hu.gde.productBasket.model.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<BasketEntity,Long > {
}
