package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.display.OrderDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Scope(value = "session")
public interface OrderService extends GenericService<OrderFormBO, OrderEntity> {
    public List<OrderDisplayBO> getOrdersByUserId(int id);
}
