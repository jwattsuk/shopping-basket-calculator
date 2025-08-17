package com.jwattsuk.shopping;

import com.jwattsuk.shopping.PricingStrategy;
import com.jwattsuk.shopping.RegularPricingStrategy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class OfferFactory {
    private static final Map<String, PricingStrategy> activeOffers = new HashMap<>();

    static {
        // Configuration Pattern - Default offers
        activeOffers.put("MELON", new BuyOneGetOneFreeStrategy(LocalDate.of(2024, 12, 31)));
        activeOffers.put("LIME", new BuyThreeForTwoStrategy(LocalDate.of(2024, 12, 31)));
    }

    public static PricingStrategy getPricingStrategy(String itemId) {
        return activeOffers.getOrDefault(itemId.toUpperCase(), new RegularPricingStrategy());
    }

    public static void addOffer(String itemId, PricingStrategy strategy) {
        activeOffers.put(itemId.toUpperCase(), strategy);
    }

    public static void removeOffer(String itemId) {
        activeOffers.put(itemId.toUpperCase(), new RegularPricingStrategy());
    }

    public static Map<String, String> getActiveOffers() {
        return activeOffers.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getOfferDescription()
                ));
    }
}
