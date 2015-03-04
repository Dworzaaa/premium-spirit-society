package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.businessLayer.service.ProductCategoryService;
import com.premium.spirit.society.core.dataLayer.DAO.ProductCategoryDAO;
import com.premium.spirit.society.core.dataLayer.DAO.UserDAO;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Service
public class ProductCategoryServiceImpl extends GenericServiceImpl<ProductCategoryFormBO, ProductCategoryEntity> implements ProductCategoryService {

    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryDAO productCategoryDAO) {
        this.productCategoryDAO = productCategoryDAO;
    }

    @Override
    @Transactional
    public int getCountOfUnhidden(String searchString) {
        return productCategoryDAO.getCountOfUnhidden(searchString);
    }

    @Override
    @Transactional
    public List<ProductCategoryDisplayBO> getAllUnhidden() {
        List<ProductCategoryEntity>categoryList = productCategoryDAO.getAllUnhidden();
        List<ProductCategoryDisplayBO> productCategoryDisplayVoList = new ArrayList<>();
        for (ProductCategoryEntity category : categoryList) {
            ProductCategoryDisplayBO categoryDisplayBO = dozer.map(category, ProductCategoryDisplayBO.class);
            productCategoryDisplayVoList.add(categoryDisplayBO);
        }
        return productCategoryDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductCategoryDisplayBO> getAllHidden() {
        List<ProductCategoryEntity>categoryList = productCategoryDAO.getAllHidden();
        List<ProductCategoryDisplayBO> productCategoryDisplayVoList = new ArrayList<>();
        for (ProductCategoryEntity category : categoryList) {
            ProductCategoryDisplayBO categoryDisplayBO = dozer.map(category, ProductCategoryDisplayBO.class);
            productCategoryDisplayVoList.add(categoryDisplayBO);
        }
        return productCategoryDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductCategoryDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        List<ProductCategoryEntity>categoryList = productCategoryDAO.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        List<ProductCategoryDisplayBO> productCategoryDisplayVoList = new ArrayList<>();
        for (ProductCategoryEntity category : categoryList) {
            ProductCategoryDisplayBO categoryDisplayBO = dozer.map(category, ProductCategoryDisplayBO.class);
            productCategoryDisplayVoList.add(categoryDisplayBO);
        }
        return productCategoryDisplayVoList;
    }

    @Override
    @Transactional
    public void hide(int catId) {
        productCategoryDAO.hide(catId);
    }

    @Override
    @Transactional
    public void restore(int catId) {
        productCategoryDAO.restore(catId);
    }


}
