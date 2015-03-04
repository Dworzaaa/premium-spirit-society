package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductSubcategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
public interface ProductCategoryService extends GenericService<ProductCategoryFormBO, ProductCategoryEntity> {


    public int getCountOfUnhidden(String searchString);


    public List<ProductCategoryDisplayBO> getAllUnhidden();
    public List<ProductCategoryDisplayBO> getAllHidden();

    public List<ProductCategoryDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);

    public void hide(int catId);

    public void restore(int catId);
}

