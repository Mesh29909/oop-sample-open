package com.acme.oop.crm.domain.model.aggregates;

import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Customer {
    private final CustomerId id;
    @Setter private String name;
    @Setter private String email;
    @Setter private Address address;
    
    public Customer(String name, String email, Address address) {
        if (name == null || name.trim().isEmpty()) 
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");
        
        this.id= new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }
    public void updateContactInfo(String email, Address address) {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        this.email = email;
        this.address = address;
    }
}
