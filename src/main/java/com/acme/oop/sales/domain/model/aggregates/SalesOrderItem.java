package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;

import java.util.UUID;
/**
 * Represents an item in a sales order.
 * This value object is immutable and shared across bounded contexts.
 *
 * @author Jean Pierr Aldave
 */
@Getter
public class SalesOrderItem {
  private final ProductId productId;
  private final int quantity;
  private final Money unitPrice;

  /**
   * Constructor for SalesOrderItem
   * @param productId the ID of the product
   * @param quantity the quantity of the product
   * @param unitPrice the unit price of the product
   * @throws IllegalArgumentException if any of the parameters are invalid
   * */
    public SalesOrderItem(ProductId productId, int quantity, Money unitPrice) {
        if (unitPrice.amount().scale() <= 0) throw new IllegalArgumentException("Unit price must be greater than zero");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        if (unitPrice == null) throw new IllegalArgumentException("Unit price cannot be null");
        if (productId == null || productId.value() ==null ) throw new IllegalArgumentException("Product ID cannot be null");

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    /**
     * Calculates the subtotal for this item.
     * @return the subtotal as a Money object
     */
    public Money calculateSubtotal() {
        return unitPrice.multiply(quantity);
    }
}
