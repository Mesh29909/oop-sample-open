package com.acme.oop.shared.domain.model.valueobjects;

public record Address(String street,
                      String number,
                      String city,
                      String stateOrRegion,
                      String postalCode,
                      String country) {
    /**
     *
     * @param street
     * @param number
     * @param city
     * @param stateOrRegion
     * @param postalCode
     * @param country
     */
    public Address {
        if (street == null || street.trim().isEmpty())
            throw new IllegalArgumentException("street cannot be null or empty");
        if (number == null || number.trim().isEmpty())
            throw new IllegalArgumentException("number cannot be null or empty");
        if (city == null || city.trim().isEmpty())
            throw new IllegalArgumentException("city cannot be null or empty");
        if (postalCode == null || postalCode.trim().isEmpty())
            throw new IllegalArgumentException("postalCode cannot be null or empty");
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException("country cannot be null or empty");
    }
    public Address(String street,
                   String number,
                   String city,

                   String postalCode,
                   String country){
        this(street, number, city, null, postalCode, country);
    }
}
