package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.dataLayer.DAO.ProductCategoryDAO;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */

@Repository
public class ProductCategoryDAOImpl extends GenericDAOImpl<ProductCategoryEntity> implements ProductCategoryDAO {
    @Override
    public int getCountOfUnhidden(String searchString) {
        Long count;
        count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(id) " +
                        "from ProductCategoryEntity" +
                        " where hidden=0 AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "%')")
                .uniqueResult());

        return count.intValue();
    }

    @Override
    public List<ProductCategoryEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductCategoryEntity" +
                        " where hidden=0 AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "') ORDER BY id, name")
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);

        return (List<ProductCategoryEntity>) query.list();
    }

    @Override
    public List<ProductCategoryEntity> getAllUnhidden() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductCategoryEntity  where hidden=0");

        return (List<ProductCategoryEntity>) query.list();
    }

    @Override
    public List<ProductCategoryEntity> getAllHidden() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductCategoryEntity  where hidden=1");

        return (List<ProductCategoryEntity>) query.list();

    }



    public void hide(int catId){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE ProductCategoryEntity  SET hidden = true WHERE id = :catId");
        query.setParameter("catId", catId);
        query.executeUpdate();
    }

    public void restore(int catId){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE ProductCategoryEntity  SET hidden = false  WHERE id = :catId");
        query.setParameter("catId", catId);
        query.executeUpdate();
    }
}
