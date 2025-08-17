package com.jwattsuk.shopping;

import com.jwattsuk.shopping.PricingStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

class BuyOneGetOneFreeStrategy implements PricingStrategy {
    private final LocalDate expiryDate;

    public BuyOneGetOneFreeStrategy(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public BigDecimal calculatePrice(Item item, int quantity) {
        if (!isOfferActive()) {
            return item.getBasePrice().multiply(BigDecimal.valueOf(quantity));
        }

        int paidItems = (quantity + 1) / 2; // Round up division
        return item.getBasePrice().multiply(BigDecimal.valueOf(paidItems));
    }

    @Override
    public String getOfferDescription() {
        return "Buy 1 Get 1 Free" + (expiryDate != null ? " (expires " + expiryDate + ")" : "");
    }

    @Override
    public boolean isOfferActive() {
        return expiryDate == null || LocalDate.now().isBefore(expiryDate) || LocalDate.now().equals(expiryDate);
    }
}
