package com.jwattsuk.shopping;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BasketCalculatorMain {

    public static void main(String[] args) {
        ShoppingBasket basket = new ShoppingBasket();

        // Display available items and offers
        ConfigurationManager.displayAvailableItems();
        ConfigurationManager.displayActiveOffers();

        // Add items to basket
        System.out.println("\n=== ADDING ITEMS ===");
        basket.addItems("APPLE", 3);
        basket.addItems("BANANA", 2);
        basket.addItems("MELON", 3); // Should trigger BOGO offer
        basket.addItems("LIME", 4);  // Should trigger buy 3 for 2 offer

        // Display basket contents
        basket.displayBasket();

        // Calculate total
        basket.calculateTotal();

        // Demonstrate configuration changes
        System.out.println("\n=== CONFIGURATION CHANGES ===");
        ConfigurationManager.updateItemPrice("APPLE", new BigDecimal("0.40"));
        ConfigurationManager.addNewOffer("BANANA", "BOGO", LocalDate.of(2024, 12, 31));

        // Recalculate with new configuration
        System.out.println("\n=== AFTER CONFIGURATION CHANGES ===");
        basket.calculateTotal();

        // Demonstrate removing items
        System.out.println("\n=== REMOVING ITEMS ===");
        basket.removeItem("APPLE");
        basket.removeItem("LIME");
        basket.calculateTotal();
    }
}
