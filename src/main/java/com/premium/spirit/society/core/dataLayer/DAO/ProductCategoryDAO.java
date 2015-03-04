package com.premium.spirit.society.core.dataLayer.DAO;

import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 4. 1. 2015.
 */

public interface ProductCategoryDAO  extends GenericDAO<ProductCategoryEntity> {
    public int getCountOfUnhidden(String searchString);
    public List<ProductCategoryEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);
    public List<ProductCategoryEntity> getAllUnhidden();
    public List<ProductCategoryEntity> getAllHidden();
    public void hide (int catId);
    public void restore (int catId);
}
