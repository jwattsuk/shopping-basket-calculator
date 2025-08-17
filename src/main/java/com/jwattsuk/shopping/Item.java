package com.jwattsuk.shopping;

import java.math.BigDecimal;
import java.util.Objects;

class Item {
    private final String name;
    private final String id;
    private BigDecimal basePrice;

    public Item(String id, String name, BigDecimal basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getBasePrice() { return basePrice; }
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s (£%.2f)", name, basePrice);
    }
}
