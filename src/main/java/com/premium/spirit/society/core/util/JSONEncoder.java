package com.premium.spirit.society.core.util;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductSubcategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class JSONEncoder {

    @SuppressWarnings("unchecked")
    public String encodeUser(List<UserDisplayBO> userList, int usersCount) {
        JSONArray userListJSON = new JSONArray();

        for (UserDisplayBO user : userList) {
            if (!user.isCustomer())
                continue;

            JSONArray userInfoJSON = new JSONArray();
            JSONObject userContactInfoJSON = new JSONObject();
            userContactInfoJSON.put("userID", user.getId());
            userContactInfoJSON.put("username", user.getUsername());
            userContactInfoJSON.put("userLastName", user.getContact()
                    .getLastName());
            userContactInfoJSON.put("userFirstName", user.getContact()
                    .getFirstName());
            userContactInfoJSON.put("addressCity", user.getContact()
                    .getAddressCity());
            userContactInfoJSON.put("addressStreet", user.getContact()
                    .getAddressStreet());
            userContactInfoJSON.put("addressHn", user.getContact()
                    .getAddressHn());
            userInfoJSON.add(userContactInfoJSON);

            userListJSON.add(userInfoJSON);
        }


        JSONObject obj = new JSONObject();
        obj.put("userList", userListJSON);
        obj.put("usersCount", usersCount);

        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
        return out.toString();
    }

    @SuppressWarnings("unchecked")
    public String encodeCategory(List<ProductCategoryDisplayBO> userList, int usersCount) {
        JSONArray userListJSON = new JSONArray();

        for (ProductCategoryDisplayBO category : userList) {

            JSONArray userInfoJSON = new JSONArray();
            JSONObject userContactInfoJSON = new JSONObject();
            userContactInfoJSON.put("categoryId", category.getId());
            userContactInfoJSON.put("categoryName", category.getName());
            userContactInfoJSON.put("categoryDescription", category.getDescription());

            userInfoJSON.add(userContactInfoJSON);

            userListJSON.add(userInfoJSON);
        }


        JSONObject obj = new JSONObject();
        obj.put("userList", userListJSON);
        obj.put("usersCount", usersCount);

        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
        return out.toString();
    }
    public String encodeSubcategory(List<ProductSubcategoryDisplayBO> userList, int usersCount) {
        JSONArray userListJSON = new JSONArray();

        for (ProductSubcategoryDisplayBO category : userList) {

            JSONArray userInfoJSON = new JSONArray();
            JSONObject userContactInfoJSON = new JSONObject();
            userContactInfoJSON.put("subcategoryId", category.getId());
            userContactInfoJSON.put("subcategoryName", category.getName());
            userContactInfoJSON.put("subcategoryDescription", category.getDescription());

            userInfoJSON.add(userContactInfoJSON);

            userListJSON.add(userInfoJSON);
        }


        JSONObject obj = new JSONObject();
        obj.put("userList", userListJSON);
        obj.put("usersCount", usersCount);

        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
        return out.toString();
    }
    public String encodeProduct(List<ProductDisplayBO> userList, int usersCount) {
        JSONArray productListJSON = new JSONArray();

        for (ProductDisplayBO product : userList) {

            JSONArray userInfoJSON = new JSONArray();
            JSONObject userContactInfoJSON = new JSONObject();
            userContactInfoJSON.put("productId", product.getId());
            userContactInfoJSON.put("productCatUrl", product.getProductSubcategory().getProductCategory().getUrl());
            userContactInfoJSON.put("productSubcatUrl", product.getProductSubcategory().getUrl());
            userContactInfoJSON.put("productPrice", product.getPrice());
            userContactInfoJSON.put("productName", product.getName());
            userContactInfoJSON.put("productProducer", product.getProducer());
            userContactInfoJSON.put("productDescription", product.getDescription());
            userContactInfoJSON.put("productUrl", product.getUrl());

            userInfoJSON.add(userContactInfoJSON);

            productListJSON.add(userInfoJSON);
        }


        JSONObject obj = new JSONObject();
        obj.put("userList", productListJSON);
        obj.put("usersCount", usersCount);

        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
        return out.toString();
    }
}
