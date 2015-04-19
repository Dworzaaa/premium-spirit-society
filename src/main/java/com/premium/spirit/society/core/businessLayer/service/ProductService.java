package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductSubcategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;

import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
public interface ProductService extends GenericService<ProductFormBO, ProductEntity> {
    public int getCountOfUnhidden(String searchString);
    public int getCountOfUnhiddenInCat(String searchString, int cat);
    public int getCountOfUnhiddenInSubcat(String searchString, int subcat);

    public List<ProductDisplayBO> getAllUnhidden();
    public List<ProductDisplayBO> getAllHidden();
    public List<ProductDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);
    public List<ProductDisplayBO> getEverythingByCatWithPagination(int maxResults, int pageNumber, int CatId);
    public List<ProductDisplayBO> getEverythingWithPagination(int maxResults, int pageNumber);
    public List<ProductDisplayBO> getEverythingBySubcatWithPagination(int maxResults, int pageNumber, int subcatId);
    public List<ProductDisplayBO> getPromoted();



    public void hide(int catId);

    public void restore(int catId);

}
