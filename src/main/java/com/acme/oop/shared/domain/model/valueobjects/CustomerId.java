package com.acme.oop.shared.domain.model.valueobjects;

import java.util.UUID;

/**
 * Represents a unique identifier for a Customer.
 * This value object is immutable and shared across bounded contexts.
 *
 * @author Jean Pierr Aldave
 */
public record CustomerId(UUID value) {
    /**
     * Constructor for CustomerId
     * @param value the UUID value of the customer ID
     * @throws IllegalArgumentException if the value is null
     */
    public CustomerId{
        if(value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }
    /**
     * Constructor for CustomerId with a random UUID
     */
    public CustomerId(){
        this(UUID.randomUUID());
    }
}
