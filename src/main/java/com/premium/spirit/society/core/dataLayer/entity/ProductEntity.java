package com.premium.spirit.society.core.dataLayer.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Entity
@Table(name = "PRODUCT")

public class ProductEntity implements Comparable<ProductEntity> {

    @Id
    @Column(name = "ID", precision = 6, scale = 0, unique = true, nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "COUNT", nullable = false)
    private int count;

    @Column(name = "NAME", length = 100, nullable = false)

    private String name;

    @Column(name = "DESCRIPTION", length = 1000, nullable = false)

    private String description;

    @Column(name = "URL", length = 100, nullable = false)
    private String url;

    @Column(name = "HIDDEN", nullable = false)
    private boolean hidden;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "VOLUME", nullable = false)
    private int volume;

    @Column(name = "METHANOL_VOLUME", nullable = false)
    private int methanolVolume;

    @Column(name = "IS_PROMOTED", nullable = false)
    private boolean promoted;

    @Column(name = "PROMOTION_TEXT", nullable = true)
    private String promotionText;

    @Column(name = "PREFERENCE", nullable = false)
    private int preference;

    @Column(name = "country_of_origin", nullable = true)
    private String countryOfOrigin;

     @Column(name="order_amount", nullable=false)
    private int orderAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_subcategory_id", insertable = false, updatable = false)
    private ProductSubcategoryEntity productSubcategory;

    @Column(name = "product_subcategory_id", nullable = false)
    private int productSubcategoryID;

    @Column(name = "product_subcategory_product_category_id", nullable = false)
    private int productCategoryID;

    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "promotion_header", nullable = false)
    private String promotionHeader;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<OrderEntity> orders;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int order_amount) {
        this.orderAmount = order_amount;
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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public int getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(int productCategoryID) {
        this.productCategoryID = productCategoryID;
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

    public int getMethanolVolume() {
        return methanolVolume;
    }

    public void setMethanolVolume(int methanolVolume) {
        this.methanolVolume = methanolVolume;
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

    @Override
    public int compareTo(ProductEntity o) {
        int dateComparison = 0;
        int idComparison = this.id - o.getId();
        if (dateComparison > 0) {
            return -1;
        } else if (dateComparison == 0) {
            if (idComparison < 0) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 1;
        }

    }
}
