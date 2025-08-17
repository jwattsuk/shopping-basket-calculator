package com.jwattsuk.shopping;

import com.jwattsuk.shopping.PricingStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

class BuyThreeForTwoStrategy implements PricingStrategy {
    private final LocalDate expiryDate;

    public BuyThreeForTwoStrategy(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public BigDecimal calculatePrice(Item item, int quantity) {
        if (!isOfferActive()) {
            return item.getBasePrice().multiply(BigDecimal.valueOf(quantity));
        }

        int groupsOfThree = quantity / 3;
        int remainder = quantity % 3;
        int paidItems = (groupsOfThree * 2) + remainder;

        return item.getBasePrice().multiply(BigDecimal.valueOf(paidItems));
    }

    @Override
    public String getOfferDescription() {
        return "Buy 3 for 2" + (expiryDate != null ? " (expires " + expiryDate + ")" : "");
    }

    @Override
    public boolean isOfferActive() {
        return expiryDate == null || LocalDate.now().isBefore(expiryDate) || LocalDate.now().equals(expiryDate);
    }
}
