package com.jwattsuk.shopping;

import com.jwattsuk.shopping.PricingStrategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class ShoppingBasket {
    private static final int MAX_ITEMS = 100;
    private final Map<Item, Integer> items = new HashMap<>();
    private int totalItemCount = 0;

    public void addItem(String itemId) {
        if (totalItemCount >= MAX_ITEMS) {
            throw new IllegalStateException("Basket is full! Maximum " + MAX_ITEMS + " items allowed.");
        }

        Item item = ItemFactory.createItem(itemId);
        items.merge(item, 1, Integer::sum);
        totalItemCount++;

        System.out.println("Added: " + item.getName() + " (Total: " + items.get(item) + ")");
    }

    public void addItems(String itemId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addItem(itemId);
        }
    }

    public void removeItem(String itemId) {
        Item item = ItemFactory.createItem(itemId);
        Integer currentQuantity = items.get(item);

        if (currentQuantity == null || currentQuantity == 0) {
            System.out.println("Item " + item.getName() + " not found in basket");
            return;
        }

        if (currentQuantity == 1) {
            items.remove(item);
        } else {
            items.put(item, currentQuantity - 1);
        }
        totalItemCount--;

        System.out.println("Removed: " + item.getName() + " (Remaining: " + items.getOrDefault(item, 0) + ")");
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        System.out.println("\n=== BASKET CALCULATION ===");
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();

            PricingStrategy strategy = OfferFactory.getPricingStrategy(item.getId());
            BigDecimal itemTotal = strategy.calculatePrice(item, quantity);

            System.out.printf("%s x%d: £%.2f (%s)%n",
                    item.getName(), quantity, itemTotal, strategy.getOfferDescription());

            total = total.add(itemTotal);
        }

        System.out.printf("TOTAL: £%.2f%n", total);
        System.out.println("Items in basket: " + totalItemCount + "/" + MAX_ITEMS);

        return total;
    }

    public void displayBasket() {
        System.out.println("\n=== SHOPPING BASKET ===");
        if (items.isEmpty()) {
            System.out.println("Basket is empty");
            return;
        }

        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            PricingStrategy strategy = OfferFactory.getPricingStrategy(item.getId());

            System.out.printf("%s x%d (£%.2f each) - %s%n",
                    item.getName(), quantity, item.getBasePrice(), strategy.getOfferDescription());
        }
    }

    public void clear() {
        items.clear();
        totalItemCount = 0;
        System.out.println("Basket cleared");
    }

    public int getItemCount() {
        return totalItemCount;
    }
}
