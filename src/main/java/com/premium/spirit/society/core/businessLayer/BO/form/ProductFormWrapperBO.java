package com.premium.spirit.society.core.businessLayer.BO.form;

/**
 * Created by Martin on 2. 3. 2015.
 */
public class ProductFormWrapperBO extends ProductFormBO{
    public ProductFormWrapperBO(ProductFormBO productFormBO) {
        this.setId(productFormBO.getId());
        this.setName(productFormBO.getName());
        this.setDescription(productFormBO.getDescription());
        this.setPrice(productFormBO.getPrice());
        this.setUrl(productFormBO.getUrl());
        this.setProductSubcategory(productFormBO.getProductSubcategory());
        this.amount = 1;
    }

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
