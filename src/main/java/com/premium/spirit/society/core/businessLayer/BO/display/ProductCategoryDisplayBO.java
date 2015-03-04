package com.premium.spirit.society.core.businessLayer.BO.display;

import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.util.CollectionConverter;
import com.premium.spirit.society.core.util.Sorter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 4. 1. 2015.
 */
public class ProductCategoryDisplayBO {

    private int id;

    private String name;
    private String description;

    private boolean hidden;

    private String url;
    private List<ProductSubcategoryEntity> productSubcategoriesList;

    public List<ProductSubcategoryEntity> getProductSubcategoriesList() {
        return productSubcategoriesList;
    }

    public void setProductSubcategoriesList(List<ProductSubcategoryEntity> productSubcategoriesList) {
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
