package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.dataLayer.DAO.OrderDAO;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */

@Repository
public class OrderDAOImpl extends GenericDAOImpl<OrderEntity> implements OrderDAO {
    @Override
    public List<OrderEntity> getOrdersByUserId(int id) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from OrderEntity" +
                        " where userID=:userID ")
        .setParameter("userID", id);

        return (List<OrderEntity>) query.list();
    }
}
