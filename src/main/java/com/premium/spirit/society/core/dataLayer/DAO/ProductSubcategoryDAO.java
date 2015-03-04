package com.premium.spirit.society.core.dataLayer.DAO;


import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
public interface ProductSubcategoryDAO extends GenericDAO<ProductSubcategoryEntity> {
    public int getCountOfUnhidden(String searchString);
    public List<ProductSubcategoryEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);

    public List<ProductSubcategoryEntity> getAllUnhidden();
    public List<ProductSubcategoryEntity> getAllHidden();
    public void hide (int catId);
    public void restore (int catId);
}
