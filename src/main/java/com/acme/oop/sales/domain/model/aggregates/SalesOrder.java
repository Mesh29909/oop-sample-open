package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
/**
 * Represents a sales order in the sales bounded context.
 * This aggregate is responsible for managing the lifecycle of a sales order.
 *
 * @author Jean Pierr Aldave
 */
@Getter
public class SalesOrder {
    private final UUID id;
    private final CustomerId customerId;
    private final LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    private Money totalAmount;
    /**
     * Constructor for SalesOrder
     * @param customerId the ID of the customer placing the order
     * @throws IllegalArgumentException if the customer ID is null
     */
    public SalesOrder(CustomerId customerId){
        if (customerId == null) throw new IllegalArgumentException("Customer ID cannot be null");
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalAmount = Money.zero();
    }
    /**
     * Adds an item to the sales order.
     * @param productId the ID of the product
     * @param unitPrice the unit price of the product
     * @param quantity the quantity of the product
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public void addItem(ProductId productId, Money unitPrice, int quantity){
        if (productId == null) throw new IllegalArgumentException("Product ID cannot be null");
        if (unitPrice == null || unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Unit price must be greater than zero");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");

        SalesOrderItem item = new SalesOrderItem(productId, quantity, unitPrice);
        items.add(item);
        totalAmount = calculateTotalAmount();
    }

    /**
     * Calculates the total amount of the order.
     * @return total amount of the sales order as a Money object.
     */
    private Money calculateTotalAmount(){
        return items.stream().map(SalesOrderItem::calculateSubtotal).reduce(Money.zero(), Money::add);
    }
}
