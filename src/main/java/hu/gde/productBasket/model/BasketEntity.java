package hu.gde.productBasket.model;

import hu.gde.productBasket.model.ProductEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.Set;

@Entity
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long basketId;

    @ManyToMany
    @JoinTable(
            name = "basket_product",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> basketProducts;

    private String basketName;
    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }
    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public Set<ProductEntity> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(Set<ProductEntity> basketProducts) {
        this.basketProducts = basketProducts;
    }
}
