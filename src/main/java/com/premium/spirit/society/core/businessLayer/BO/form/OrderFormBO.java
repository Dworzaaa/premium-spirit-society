package com.premium.spirit.society.core.businessLayer.BO.form;

import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */

@Component
@Scope("session")
public class OrderFormBO implements Serializable{

    private int id;

    @Size(max = 1000)
    private String note;

    private boolean hidden;

    private int state;

    private Date date;

    private List<ProductFormBO> products;

    private String shippingAddressHn;

     private String shippingAddressStreet;

     private String shippingAddressCity;

     private String shippingAddressPostalcode;

     private String shippingAddressCountry;

    private UserEntity user;

    private int userID;

    private String orderNumber;

    private String shippingType;

    private String paymentMethod;

    private String invoice;

    private int shippingPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ProductFormBO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFormBO> products) {
        this.products = products;
    }

    public String getShippingAddressHn() {
        return shippingAddressHn;
    }

    public void setShippingAddressHn(String shippingAddressHn) {
        this.shippingAddressHn = shippingAddressHn;
    }

    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }

    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressPostalcode() {
        return shippingAddressPostalcode;
    }

    public void setShippingAddressPostalcode(String shippingAddressPostalcode) {
        this.shippingAddressPostalcode = shippingAddressPostalcode;
    }

    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(int shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
}