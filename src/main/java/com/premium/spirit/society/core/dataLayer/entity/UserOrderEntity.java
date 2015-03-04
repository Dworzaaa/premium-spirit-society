package com.premium.spirit.society.core.dataLayer.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Martin on 12. 1. 2015.
 */
public class UserOrderEntity {
    @Id
    @Column(name = "ID", precision = 6, scale = 0, nullable = false)
    @GeneratedValue
    private int id;

    /**
     * The role_id.
     */
    @Column(name = "ORDER_ID", nullable = false)
    private int orderId;

    /**
     * The user_id.
     */
    @Column(name = "USER_ID", nullable = false)
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
