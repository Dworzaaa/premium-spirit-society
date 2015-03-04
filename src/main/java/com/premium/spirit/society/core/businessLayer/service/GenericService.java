package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;

import java.util.List;

public interface GenericService<VO, Entity> {

    public int save(VO vo, Class<Entity> entityClass);

    public void  merge(VO vo, Class<Entity> entityClass);

    public VO getById(int id, Class<VO> voClass, Class<Entity> entityClass);

    public void update(VO vo, Class<Entity> entityClass);

    public void saveOrUpdate(VO vo, Class<Entity> entityClass);

    public void delete(int id, Class<Entity> entityClass);

    public List<VO> getAll(Class<VO> voClass, Class<Entity> entityClass);

    public int getCount( Class<Entity> entityClass);
}
