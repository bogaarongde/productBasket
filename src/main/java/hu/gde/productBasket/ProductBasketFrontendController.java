package hu.gde.productBasket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductBasketFrontendController {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/baskets")
    public String getAllBaskets(Model model) {
        List<BasketEntity> baskets = basketRepository.findAll();
        model.addAttribute("baskets", baskets);
        return "baskets";
    }

    @PostMapping("/createBasket")
    public String createBasket(@RequestParam("name") String name) {
        BasketEntity newBasket = new BasketEntity();
        newBasket.setBasketName(name);
        basketRepository.save(newBasket);
        return "redirect:/baskets";
    }

    @GetMapping("/basket/{id}")
    public String viewBasketDetails(@PathVariable Long id, Model model) {
        BasketEntity basket = basketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Basket not found with ID: " + id));

        model.addAttribute("products", basket.getBasketProducts());
        return "basketDetails";
    }

}
