package com.premium.spirit.society.core.dataLayer.DAO;

import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
public interface ProductDAO extends GenericDAO<ProductEntity> {
    public int getCountOfUnhidden(String searchString);
    public List<ProductEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);
    public List<ProductEntity> getEverythingByCatWithPagination(int maxResults, int pageNumber, int catId);
    public List<ProductEntity> getEverythingWithPagination(int maxResults, int pageNumber);
    public List<ProductEntity> getEverythingBySubcatAndCatWithPagination(int maxResults, int pageNumber, int subcatId, int catId);
    public List<ProductEntity> getPromoted();

    public List<ProductEntity> getAllUnhidden();
    public List<ProductEntity> getAllHidden();
    public void hide (int catId);
    public void restore (int catId);
}
