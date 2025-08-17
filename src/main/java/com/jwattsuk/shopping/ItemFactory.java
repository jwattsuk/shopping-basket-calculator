package com.jwattsuk.shopping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ItemFactory {
    private static final Map<String, ItemConfig> itemConfigurations = new HashMap<>();

    static {
        // Configuration Pattern - Could be loaded from external file
        itemConfigurations.put("APPLE", new ItemConfig("Apple", new BigDecimal("0.35")));
        itemConfigurations.put("BANANA", new ItemConfig("Banana", new BigDecimal("0.20")));
        itemConfigurations.put("MELON", new ItemConfig("Melon", new BigDecimal("0.50")));
        itemConfigurations.put("LIME", new ItemConfig("Lime", new BigDecimal("0.15")));
    }

    public static Item createItem(String itemId) {
        ItemConfig config = itemConfigurations.get(itemId.toUpperCase());
        if (config == null) {
            throw new IllegalArgumentException("Unknown item: " + itemId);
        }
        return new Item(itemId.toUpperCase(), config.name, config.basePrice);
    }

    public static void updatePrice(String itemId, BigDecimal newPrice) {
        ItemConfig config = itemConfigurations.get(itemId.toUpperCase());
        if (config != null) {
            config.basePrice = newPrice;
        }
    }

    public static Set<String> getAvailableItems() {
        return new HashSet<>(itemConfigurations.keySet());
    }

    private static class ItemConfig {
        String name;
        BigDecimal basePrice;

        ItemConfig(String name, BigDecimal basePrice) {
            this.name = name;
            this.basePrice = basePrice;
        }
    }
}
