package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductSubcategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductSubcategoryFormBO;
import com.premium.spirit.society.core.businessLayer.service.ProductSubcategoryService;
import com.premium.spirit.society.core.dataLayer.DAO.ProductCategoryDAO;
import com.premium.spirit.society.core.dataLayer.DAO.ProductSubcategoryDAO;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Service
public class ProductSubcategoryServiceImpl extends GenericServiceImpl<ProductSubcategoryFormBO, ProductSubcategoryEntity> implements ProductSubcategoryService {

    private ProductSubcategoryDAO productSubcategoryDAO;

    @Autowired
    public ProductSubcategoryServiceImpl(ProductSubcategoryDAO productSubcategoryDAO) {
        this.productSubcategoryDAO = productSubcategoryDAO;
    }

    @Override
    @Transactional
    public int getCountOfUnhidden(String searchString) {
        return productSubcategoryDAO.getCountOfUnhidden(searchString);
    }

    @Override
    @Transactional
    public List<ProductSubcategoryDisplayBO> getAllUnhidden() {
        List<ProductSubcategoryEntity>  productSubcategoryEntities = productSubcategoryDAO.getAllUnhidden();
        List<ProductSubcategoryDisplayBO> productSubcategoryDisplayVoList = new ArrayList<>();
        for (ProductSubcategoryEntity productSubcategoryEntity : productSubcategoryEntities) {
            ProductSubcategoryDisplayBO productSubcategoryDisplayBO = dozer.map(productSubcategoryEntity, ProductSubcategoryDisplayBO.class);
            productSubcategoryDisplayVoList.add(productSubcategoryDisplayBO);
        }
        return productSubcategoryDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductSubcategoryDisplayBO> getAllHidden() {
        List<ProductSubcategoryEntity>  productSubcategoryEntities = productSubcategoryDAO.getAllHidden();
        List<ProductSubcategoryDisplayBO> productSubcategoryDisplayVoList = new ArrayList<>();
        for (ProductSubcategoryEntity productSubcategoryEntity : productSubcategoryEntities) {
            ProductSubcategoryDisplayBO productSubcategoryDisplayBO = dozer.map(productSubcategoryEntity, ProductSubcategoryDisplayBO.class);
            productSubcategoryDisplayVoList.add(productSubcategoryDisplayBO);
        }
        return productSubcategoryDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductSubcategoryDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        List<ProductSubcategoryEntity>  productSubcategoryEntities = productSubcategoryDAO.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        List<ProductSubcategoryDisplayBO> productSubcategoryDisplayVoList = new ArrayList<>();
        for (ProductSubcategoryEntity productSubcategoryEntity : productSubcategoryEntities) {
            ProductSubcategoryDisplayBO productSubcategoryDisplayBO = dozer.map(productSubcategoryEntity, ProductSubcategoryDisplayBO.class);
            productSubcategoryDisplayVoList.add(productSubcategoryDisplayBO);
        }
        return productSubcategoryDisplayVoList;
    }

    @Override
    @Transactional
    public void hide(int catId) {
        productSubcategoryDAO.hide(catId);
    }

    @Override
    @Transactional
    public void restore(int catId) {
        productSubcategoryDAO.restore(catId);
    }

}
