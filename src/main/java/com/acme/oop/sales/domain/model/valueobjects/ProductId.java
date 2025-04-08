package com.acme.oop.sales.domain.model.valueobjects;

import java.util.UUID;
/**
 * Represents a unique identifier for a Product.
 * This value object is immutable and shared across bounded contexts.
 *
 * @author Jean Pierr Aldave
 */
public record ProductId(UUID value) {
    /**
     * Constructor for ProductId
     * @param value the UUID value of the product ID
     * @throws IllegalArgumentException if the value is null
     */
    public ProductId {
        if (value == null) throw new IllegalArgumentException("Product ID cannot be null");

    }

    public ProductId() {
        this(UUID.randomUUID());
    }
}
