package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.form.OrderProductFormBO;
import com.premium.spirit.society.core.businessLayer.service.GenericService;
import com.premium.spirit.society.core.businessLayer.service.OrderProductService;
import com.premium.spirit.society.core.dataLayer.entity.OrderProductEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Martin on 11. 1. 2015.
 */
@Service
public class OrderProductServiceImpl extends GenericServiceImpl<OrderProductFormBO,OrderProductEntity> implements OrderProductService {
}
