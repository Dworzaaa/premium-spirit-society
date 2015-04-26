package com.premium.spirit.society.core.businessLayer.BO.form;

/**
 * Created by Martin on 2. 3. 2015.
 */
public class ProductFormWrapperBO extends ProductFormBO{
    public ProductFormWrapperBO(ProductFormBO productFormBO,OrderFormBO order ) {
        this.setId(productFormBO.getId());
        this.setOrderId(order.getId());
        this.setName(productFormBO.getName());
        this.setDescription(productFormBO.getDescription());
        this.setPrice(productFormBO.getPrice());
        this.setUrl(productFormBO.getUrl());
        this.setProductSubcategory(productFormBO.getProductSubcategory());
        this.setVolume(productFormBO.getVolume());
        this.setEthanolVolume(productFormBO.getEthanolVolume());
        this.amount = 1;
    }
    private int orderId;

    private int amount;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
