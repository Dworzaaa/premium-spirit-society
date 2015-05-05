package com.premium.spirit.society.core.dataLayer.DAO;

import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
public interface OrderDAO extends GenericDAO<OrderEntity> {
    public List<OrderEntity> getOrdersByUserId(int id);

   public OrderEntity getByOrderNumber(String orderNumber);
}
