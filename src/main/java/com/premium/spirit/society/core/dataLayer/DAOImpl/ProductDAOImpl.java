package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.dataLayer.DAO.ProductDAO;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Repository
public class ProductDAOImpl extends GenericDAOImpl<ProductEntity> implements ProductDAO {
    @Override
    public int getCountOfUnhidden(String searchString) {
        Long count;
        count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(id) " +
                        "from ProductEntity" +
                        " where hidden=0 AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "%')")
                .uniqueResult());

        return count.intValue();
    }

    @Override
    public int getCountOfUnhiddenInCat(String searchString, int cat) {
        Long count;
        count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(id) " +
                        "from ProductEntity" +
                        " where hidden=0 AND productCategoryID=:catId " +
                        "AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "%')")
                .setParameter("catId", cat)
                .uniqueResult());

        return count.intValue();
    }


    @Override
    public int getCountOfUnhiddenInSubcat(String searchString, int subcat) {
        Long count;
        count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(id) " +
                        "from ProductEntity" +
                        " where hidden=0 AND productSubcategoryID=:subcatId " +
                        "AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "%')")
                .setParameter("subcatId", subcat)
                .uniqueResult());

        return count.intValue();
    }



    @Override
    public List<ProductEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity" +
                        " where hidden=0 AND (name like '" + searchString + "%'" +
                        " OR id like '" + searchString + "') ORDER BY preference,id, name")
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);

        return (List<ProductEntity>) query.list();
    }
    @Override
    public List<ProductEntity> getEverythingWithPagination(int maxResults, int pageNumber) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity" +
                        " where hidden=0 ORDER BY preference,id, name")
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);


        return (List<ProductEntity>) query.list();
    }

    @Override
      public List<ProductEntity> getEverythingByCatWithPagination(int maxResults, int pageNumber, int catId) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity" +
                        " where hidden=0 AND productCategoryID=:catId ORDER BY preference,id, name")
                .setParameter("catId", catId)
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);


        return (List<ProductEntity>) query.list();
    }



    @Override
    public List<ProductEntity> getEverythingBySubcatWithPagination(int maxResults, int pageNumber, int subcatId) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity" +
                        " where hidden=0 AND productSubcategoryID=:subcatId   ORDER BY preference,id, name")
                .setParameter("subcatId", subcatId)
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);


        return (List<ProductEntity>) query.list();
    }

    @Override
    public List<ProductEntity> getPromoted() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity where (hidden = 0 AND promoted=1) ORDER BY preference");

        return (List<ProductEntity>) query.list();
    }

    @Override
    public List<ProductEntity> getAllUnhidden() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity  where hidden=0");

        return (List<ProductEntity>) query.list();
    }

    @Override
    public List<ProductEntity> getAllHidden() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from ProductEntity  where hidden=1");

        return (List<ProductEntity>) query.list();
    }

    public void hide(int catId){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE ProductEntity  SET hidden = true WHERE id = :catId");
        query.setParameter("catId", catId);
        query.executeUpdate();
    }

    public void restore(int catId){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE ProductEntity  SET hidden = false  WHERE id = :catId");
        query.setParameter("catId", catId);
        query.executeUpdate();
    }
}
