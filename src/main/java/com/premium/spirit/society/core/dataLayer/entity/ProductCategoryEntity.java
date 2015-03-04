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
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategoryEntity {

    @Id
    @Column(name = "ID", precision = 6, scale = 0, nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 1000, nullable = false)
    private String description;

    @Column (name="HIDDEN", nullable = false)
    private boolean hidden;


    @Column(name = "URL", length = 100, nullable = false)
    private String url;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
    @Cascade({CascadeType.ALL})
    private Set<ProductSubcategoryEntity> productSubcategoriesList;

    public Set<ProductSubcategoryEntity> getProductSubcategoriesList() {
        return productSubcategoriesList;
    }

    public void setProductSubcategoriesList(Set<ProductSubcategoryEntity> productSubcategoriesList) {
        this.productSubcategoriesList = productSubcategoriesList;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
