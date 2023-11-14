package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling purchase success and error pages.
 * This controller provides methods to display pages for successful and failed purchase outcomes.
 */
@Controller
public class PurchaseController {

    /**
     * Handles the display of the purchase success page.
     *
     * @return The name of the purchase success view template.
     */
    @GetMapping("/purchaseSuccess")
    public String purchaseSuccess() {
        return "purchaseSuccess";
    }

    /**
     * Handles the display of the purchase error page.
     *
     * @return The name of the purchase error view template.
     */
    @GetMapping("/purchaseError")
    public String purchaseError() {
        return "purchaseError";
    }
}
