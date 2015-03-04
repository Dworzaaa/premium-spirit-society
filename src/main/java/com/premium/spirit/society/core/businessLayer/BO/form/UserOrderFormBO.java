package com.premium.spirit.society.core.businessLayer.BO.form;

import javax.persistence.Column;

/**
 * Created by Martin on 12. 1. 2015.
 */
public class UserOrderFormBO {
    private int id;

    private int orderId;

    private int user_id;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
