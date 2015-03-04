package com.premium.spirit.society.core.businessLayer.BO.form;

import javax.persistence.Column;

/**
 * Created by Martin on 11. 1. 2015.
 */
public class OrderProductFormBO {
    private int id;

    /**
     * The role_id.
     */
    private int orderId;

    /**
     * The user_id.
     */
    private int userId;

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
