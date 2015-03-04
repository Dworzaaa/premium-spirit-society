package com.premium.spirit.society.core.dataLayer.DAO;

import java.util.List;

/**
 * The Interface GenericDAO.
 *
 * @param <Entity> the generic type
 */
public interface GenericDAO<Entity> {

    /**
     * Save.
     *
     * @param entity the entity
     */
    public int save(Entity entity);

    public Entity getById(int id, Class<Entity> entityClass);

    /**
     * update.
     *
     * @param entity the entity
     */
    public void update(Entity entity);

    public void saveOrUpdate(Entity entity);

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(Entity entity);

    public void merge(Entity entity);

    /**
     * Find all.
     *
     * @param myClass the my class
     * @return the list
     */
    public List<Entity> findAll(Class<Entity> myClass);

    public int getCount(Class<Entity> myClass);
}
