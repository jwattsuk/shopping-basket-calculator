package com.jwattsuk.shopping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

class ConfigurationManager {
    public static void updateItemPrice(String itemId, BigDecimal newPrice) {
        ItemFactory.updatePrice(itemId, newPrice);
        System.out.println("Updated " + itemId + " price to £" + newPrice);
    }

    public static void addNewOffer(String itemId, String offerType, LocalDate expiryDate) {
        PricingStrategy strategy;

        switch (offerType.toUpperCase()) {
            case "BOGO":
                strategy = new BuyOneGetOneFreeStrategy(expiryDate);
                break;
            case "BUY3FOR2":
                strategy = new BuyThreeForTwoStrategy(expiryDate);
                break;
            default:
                throw new IllegalArgumentException("Unknown offer type: " + offerType);
        }

        OfferFactory.addOffer(itemId, strategy);
        System.out.println("Added " + offerType + " offer for " + itemId +
                (expiryDate != null ? " (expires " + expiryDate + ")" : ""));
    }

    public static void displayAvailableItems() {
        System.out.println("\n=== AVAILABLE ITEMS ===");
        for (String itemId : ItemFactory.getAvailableItems()) {
            Item item = ItemFactory.createItem(itemId);
            System.out.println(item);
        }
    }

    public static void displayActiveOffers() {
        System.out.println("\n=== ACTIVE OFFERS ===");
        Map<String, String> offers = OfferFactory.getActiveOffers();
        for (Map.Entry<String, String> entry : offers.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

