package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
/**
 * The controller responsible for handling product purchase operations.
 * This controller allows users to purchase a product, decrement its inventory, and
 * redirects to a success or error page based on the purchase outcome.
 */
@Controller
public class BuyProductController {

    private final ProductRepository productRepository;
    /**
     * Constructs a new BuyProductController with the specified ProductRepository.
     *
     * @param productRepository The repository for retrieving and updating product information.
     */
    @Autowired
    public BuyProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    /**
     * Handles the purchase of a product.
     *
     * @param productId The ID of the product to purchase.
     * @return A redirection URL to either the purchase success or error page.
     */
    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productId") Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            if (product.getInv() > 0){
                //Decrement the Inventory and save
                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                return "redirect:/purchaseSuccess"; //will redirect to a success page
            }
        }
        return  "redirect:/purchaseError"; //will redirect to an Error Page
    }
}
