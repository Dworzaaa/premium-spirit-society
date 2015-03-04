package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.display.OrderDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.service.OrderService;
import com.premium.spirit.society.core.dataLayer.DAO.OrderDAO;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderFormBO, OrderEntity> implements OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    @Transactional
    public List<OrderDisplayBO> getOrdersByUserId(int id) {
        List<OrderEntity> listOfOrders = orderDAO.getOrdersByUserId(id);
        List<OrderDisplayBO> orderDisplayBOs = new ArrayList<>();
        for (OrderEntity order : listOfOrders) {
            OrderDisplayBO orderDisplayBO = dozer.map(order, OrderDisplayBO.class);
            orderDisplayBOs.add(orderDisplayBO);
        }
        return orderDisplayBOs;
    }
}
