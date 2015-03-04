package com.premium.spirit.society.core.dataLayer.DAOImpl;


import com.premium.spirit.society.core.dataLayer.DAO.GenericDAO;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implements GenericDAO.
 *
 * @param <Entity> the generic type
 */
@Repository
public class GenericDAOImpl<Entity> implements GenericDAO<Entity> {

    /**
     * The session factory.
     */
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public int save(Entity entity) {

        return (int) sessionFactory.getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entity getById(int id, Class<Entity> entityCass) {
        return (Entity) sessionFactory.getCurrentSession().get(entityCass, id);
    }

    public void update(Entity entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public void saveOrUpdate(Entity entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Entity entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void merge(Entity entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> findAll(Class<Entity> myClass) {

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from " + myClass.getName());

        return (List<Entity>) query.list();
    }

    @Override
    public int getCount(Class<Entity> myClass) {

        Long count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(*) from " + myClass.getName() + " WHERE status = 0")
                .uniqueResult());

        return count.intValue();
    }
}
