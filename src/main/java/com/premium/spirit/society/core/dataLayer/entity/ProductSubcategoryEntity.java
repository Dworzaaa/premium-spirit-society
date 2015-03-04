package com.premium.spirit.society.core.dataLayer.entity;

import com.premium.spirit.society.core.util.CollectionConverter;
import com.premium.spirit.society.core.util.Sorter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Entity
@Table(name = "PRODUCT_SUBCATEGORY")
public class ProductSubcategoryEntity implements Comparable<ProductSubcategoryEntity> {
    @Id
    @Column(name = "ID", precision = 6, scale = 0, unique = true, nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "URL", length = 100, nullable = false)
    private String url;

    @Column(name = "DESCRIPTION", length = 1000, nullable = false)
    private String description;

    @Column (name="HIDDEN", nullable = false)
    private boolean hidden;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category_id", insertable = false, updatable = false)
    private ProductCategoryEntity  productCategory;

    @Column(name = "product_category_id", nullable = false)
    private int productCategoryID;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productSubcategory")
    @Cascade({CascadeType.ALL})
    private Set<ProductEntity> productsList;


public Set<ProductEntity> getProductsList() {
        return productsList;
    }

    public void setProductsList(Set<ProductEntity> productsList) {
        this.productsList = productsList;
    }

        public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProductCategoryEntity getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryEntity productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(int productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public int compareTo(ProductSubcategoryEntity o) {
        int dateComparison =0;
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
