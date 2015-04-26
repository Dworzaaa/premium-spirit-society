package com.premium.spirit.society.core.businessLayer.BO.form;

import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;

import java.io.Serializable;

/**
 * Created by Martin on 2. 1. 2015.
 */

public class ProductFormBO implements Serializable {


    private static final long serialVersionUID = 1L;
    private int id;

    private int count;

    private String name;

    private String description;

    private String url;

    private boolean hidden;

    private int price;

    private int volume;

    private int ethanolVolume;

    private ProductSubcategoryEntity productSubcategory;

    private int productSubcategoryID;

    private int productCategoryID;

    private boolean promoted;

    private String promotionText;

    private int preference;

    private String promotionHeader;

    private String countryOfOrigin;

    private int orderAmount;

    private String producer;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getPromotionHeader() {
        return promotionHeader;
    }

    public void setPromotionHeader(String promotionHeader) {
        this.promotionHeader = promotionHeader;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean isPromoted) {
        this.promoted = isPromoted;
    }

    public String getPromotionText() {
        return promotionText;
    }

    public void setPromotionText(String promotionText) {
        this.promotionText = promotionText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getEthanolVolume() {
        return ethanolVolume;
    }

    public void setEthanolVolume(int ethanolVolume) {
        this.ethanolVolume = ethanolVolume;
    }

    public ProductSubcategoryEntity getProductSubcategory() {
        return productSubcategory;
    }

    public void setProductSubcategory(ProductSubcategoryEntity productSubcategory) {
        this.productSubcategory = productSubcategory;
    }

    public int getProductSubcategoryID() {
        return productSubcategoryID;
    }

    public void setProductSubcategoryID(int productSubcategoryID) {
        this.productSubcategoryID = productSubcategoryID;
    }

    public int getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(int productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}