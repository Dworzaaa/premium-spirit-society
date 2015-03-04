package com.premium.spirit.society.core.dataLayer.entity;

import javax.persistence.*;

/**
 * Created by Martin on 11. 1. 2015.
 */

@Entity
@Table(name = "SHOPPING_ORDER_HAS_PRODUCT")
public class OrderProductEntity {
    @Id
    @Column(name = "ID", precision = 6, scale = 0, nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "SHOPPING_ORDER_ID", nullable = false)
    private int shoppingOrderID;


    @Column(name = "PRODUCT_ID", nullable = false)
    private int productID;


    /**
     * Getters and setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShoppingOrderID() {
        return shoppingOrderID;
    }

    public void setShoppingOrderID(int shoppingOrderID) {
        this.shoppingOrderID = shoppingOrderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
