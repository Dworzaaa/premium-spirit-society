package com.premium.spirit.society.core.businessLayer.BO.form;

import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.util.CollectionConverter;
import com.premium.spirit.society.core.util.Sorter;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 2. 1. 2015.
 */
public class ProductCategoryFormBO {

    private int id;

    @Size(max = 100)
    private String name;

    @Size(max = 1000)
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
