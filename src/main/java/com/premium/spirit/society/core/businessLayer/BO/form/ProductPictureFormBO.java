package com.premium.spirit.society.core.businessLayer.BO.form;

import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;

/**
 * Created by Martin on 2. 1. 2015.
 */
public class ProductPictureFormBO {

    private int id;

    private byte[] picture;

    private String name;

    private int hidden;

    private ProductEntity product;

    private int picOrder;

    private int productId;

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicOrder() {
        return picOrder;
    }

    public void setPicOrder(int picOrder) {
        this.picOrder = picOrder;
    }
}
