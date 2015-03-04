package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.dataLayer.DAO.ProductCategoryDAO;
import com.premium.spirit.society.core.dataLayer.DAO.ProductSubcategoryDAO;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Repository
public class ProductSubcategoryDAOImpl extends GenericDAOImpl<ProductSubcategoryEntity> implements ProductSubcategoryDAO {
    @Override
    public int getCountOfUnhidden(String searchString) {
        Long count;
        count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(id) " +
                        "from ProductSubcategoryEntity" +
                        " where hidden=0 AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "%')")
                .uniqueResult());

        return count.intValue();
    }

    @Override
    public List<ProductSubcategoryEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductSubcategoryEntity" +
                        " where hidden=0 AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "') ORDER BY id, name")
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);

        return (List<ProductSubcategoryEntity>) query.list();
    }

    @Override
    public List<ProductSubcategoryEntity> getAllUnhidden() {
            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery("from ProductSubcategoryEntity  where hidden=0");

            return (List<ProductSubcategoryEntity>) query.list();
            }

    @Override
    public List<ProductSubcategoryEntity> getAllHidden() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductSubcategoryEntity  where hidden=1");

        return (List<ProductSubcategoryEntity>) query.list();
    }

    public void hide(int catId){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE ProductSubcategoryEntity  SET hidden = true WHERE id = :catId");
        query.setParameter("catId", catId);
        query.executeUpdate();
    }

    public void restore(int catId){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE ProductSubcategoryEntity  SET hidden = false  WHERE id = :catId");
        query.setParameter("catId", catId);
        query.executeUpdate();
    }
}
