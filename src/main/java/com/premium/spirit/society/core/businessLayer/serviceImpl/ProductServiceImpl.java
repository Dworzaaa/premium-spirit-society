package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.service.ProductService;
import com.premium.spirit.society.core.dataLayer.DAO.ProductDAO;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Service
public class ProductServiceImpl extends GenericServiceImpl<ProductFormBO, ProductEntity> implements ProductService {


    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public int getCountOfUnhidden(String searchString) {
        return productDAO.getCountOfUnhidden(searchString);
    }

    @Override
    @Transactional
    public List<ProductDisplayBO> getAllUnhidden() {
        List<ProductEntity>productList = productDAO.getAllUnhidden();
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductDisplayBO> getAllHidden() {
        List<ProductEntity>productList = productDAO.getAllHidden();
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        List<ProductEntity>productList = productDAO.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductDisplayBO> getEverythingByCatWithPagination(int maxResults, int pageNumber, int catId) {
        List<ProductEntity>productList = productDAO.getEverythingByCatWithPagination(maxResults, pageNumber, catId);
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }


    @Override
    @Transactional
    public List<ProductDisplayBO> getEverythingWithPagination(int maxResults, int pageNumber){
        List<ProductEntity>productList = productDAO.getEverythingWithPagination(maxResults, pageNumber);
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductDisplayBO> getEverythingBySubcatAndCatWithPagination(int maxResults, int pageNumber, int subcatId, int catId){
        List<ProductEntity>productList = productDAO.getEverythingBySubcatAndCatWithPagination(maxResults, pageNumber, subcatId, catId);
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }

    @Override
    @Transactional
    public List<ProductDisplayBO> getPromoted() {
        List<ProductEntity>productList = productDAO.getPromoted();
        List<ProductDisplayBO> productDisplayVoList = new ArrayList<>();
        for (ProductEntity product : productList) {
            ProductDisplayBO productDisplayBO = dozer.map(product, ProductDisplayBO.class);
            productDisplayVoList.add(productDisplayBO);
        }
        return productDisplayVoList;
    }


    @Override
    @Transactional
    public void hide(int catId) {
        productDAO.hide(catId);
    }

    @Override
    @Transactional
    public void restore(int catId) {
        productDAO.restore(catId);
    }
}
