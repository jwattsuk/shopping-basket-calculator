package com.jwattsuk.shopping;

import java.math.BigDecimal;

interface PricingStrategy {
    BigDecimal calculatePrice(Item item, int quantity);
    String getOfferDescription();
    boolean isOfferActive();
}

// Regular pricing strategy (no offers)
class RegularPricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(Item item, int quantity) {
        return item.getBasePrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String getOfferDescription() {
        return "Regular price";
    }

    @Override
    public boolean isOfferActive() {
        return true;
    }
}