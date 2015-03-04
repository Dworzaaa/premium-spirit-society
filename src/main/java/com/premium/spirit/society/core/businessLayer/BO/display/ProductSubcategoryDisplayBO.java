package com.premium.spirit.society.core.businessLayer.BO.display;

import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.util.CollectionConverter;
import com.premium.spirit.society.core.util.Sorter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 5. 1. 2015.
 */
public class ProductSubcategoryDisplayBO {

    private int id;

    @Size(max = 100)
    private String name;

    @Size(max = 1000)
    private String description;

    private boolean hidden;
    @Size(max=100)
    private String url;

    private ProductCategoryEntity category;

    private int categoryId;

    private Set<ProductEntity> productsList;

    public List<ProductEntity> getProductsList() {
        CollectionConverter<ProductEntity> converter = new CollectionConverter<>();
        Sorter<ProductEntity> sorter = new Sorter<>();
        return sorter.sortByDate(converter.toList(this.productsList));
    }

    public void setProductsList(List<ProductEntity> productsList) {
        CollectionConverter<ProductEntity> converter = new CollectionConverter<>();
        this.productsList = converter.toSet(productsList);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public ProductCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryEntity category) {
        this.category = category;
    }
}
