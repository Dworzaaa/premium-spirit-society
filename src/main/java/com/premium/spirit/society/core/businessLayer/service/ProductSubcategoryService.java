package com.premium.spirit.society.core.businessLayer.service;


import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductSubcategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductSubcategoryFormBO;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
public interface ProductSubcategoryService extends GenericService<ProductSubcategoryFormBO, ProductSubcategoryEntity> {

    public int getCountOfUnhidden(String searchString);

    public List<ProductSubcategoryDisplayBO> getAllUnhidden();
    public List<ProductSubcategoryDisplayBO> getAllHidden();

    public List<ProductSubcategoryDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);

    public void hide(int catId);

    public void restore(int catId);
}
