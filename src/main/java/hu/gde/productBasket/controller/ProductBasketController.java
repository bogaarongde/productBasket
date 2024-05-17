package hu.gde.productBasket.controller;

import hu.gde.productBasket.model.BasketEntity;
import hu.gde.productBasket.repository.BasketRepository;
import hu.gde.productBasket.model.ProductEntity;
import hu.gde.productBasket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class ProductBasketController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    @GetMapping("/getProducts")
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/addProduct")
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        return productRepository.save(product);
    }

    @GetMapping("/getBasketProducts/{id}")
    public List<ProductEntity> getBasketProducts(@PathVariable Long id) {
        BasketEntity basket = basketRepository.findById(id).orElseThrow(() -> new RuntimeException("Basket not found!"));
        return new ArrayList<>(basket.getBasketProducts());
    }

    @PutMapping("/updateBasketName/{basketId}")
    public BasketEntity updateBasketName(@PathVariable Long basketId, @RequestBody String name) {
        BasketEntity basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with ID: " + basketId));
        basket.setBasketName(name);
        return basketRepository.save(basket);
    }
    @PostMapping("/addProductToBasket/{basketId}/{productId}")
    public BasketEntity addProductToBasket(@PathVariable Long basketId, @PathVariable Long productId) {
        BasketEntity basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with ID: " + basketId));
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        basket.getBasketProducts().add(product);
        return basketRepository.save(basket);
    }

    @GetMapping("/getBasketPrice/{basketId}")
    public Long getBasketPrice(@PathVariable Long basketId) {
        BasketEntity basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with ID: " + basketId));

        long totalPrice = 0;

        for (ProductEntity product : basket.getBasketProducts()) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }

}
