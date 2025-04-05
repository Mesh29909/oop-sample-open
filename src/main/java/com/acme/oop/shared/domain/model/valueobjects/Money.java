package com.acme.oop.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents a monetary amount with a currency
 * This value object is  immutable and shared across bounded contexts
 * @author Open Source Application Developement Team
 */
public record Money(BigDecimal amount, Currency currency) {
    /**
     *
     * @param amount
     * @param currency
     * @throws IllegalArgumentException
     */
    public Money{
        if(amount == null)
            throw new IllegalArgumentException("Amount cannot be null");
        if(currency == null)
            throw new IllegalArgumentException("Currency cannot be null");
        if (amount.scale()>currency.getDefaultFractionDigits())
            throw new IllegalArgumentException("Too manu decimal places for currency"+ currency.getCurrencyCode());
    }
    public static Money zero(){
        return new Money(BigDecimal.ZERO, Currency.getInstance("USD"));
    }
    public Money add(Money other){
        if (!currency.equals(other.currency))
            throw new IllegalArgumentException("Currency does not match");
        return new Money(amount.add(other.amount), currency);
    }
    public Money multiply(int multiplier){
        return new Money(amount.multiply(BigDecimal.valueOf(multiplier)), currency);
    }
}
