package com.premium.spirit.society.core.dataLayer.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Entity
@Table(name = "SHOPPING_ORDER")
public class OrderEntity {
    @Id
    @Column(name = "ID", precision = 6, scale = 0, unique = true, nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "hidden")
    private boolean hidden;

    @Column(name = "state")
    private int state;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "note")
    private String note;

    @Column(name = "SHIPPING_ADDRESS_HN", length = 100, nullable = false)
    private String shippingAddressHn;

    @Column(name = "SHIPPING_ADDRESS_STREET", length = 100, nullable = false)
    private String shippingAddressStreet;

    @Column(name = "SHIPPING_ADDRESS_CITY", length = 100, nullable = false)
    private String shippingAddressCity;

    @Column(name = "SHIPPING_ADDRESS_POSTALCODE", length = 100, nullable = false)
    private String shippingAddressPostalcode;

    @Column(name = "SHIPPING_ADDRESS_COUNTRY", length = 100, nullable = false)
    private String shippingAddressCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "user_id", nullable = false)
    private int userID;

    @Column(name = "invoice", nullable = true)
    private String invoice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shopping_order_has_product",
            joinColumns = {@JoinColumn(name = "shopping_order_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)})
    private List<ProductEntity> products;

    @Column(name = "order_number", length = 100, nullable = false)
    private String orderNumber;


    @Column(name = "shippingType", nullable = false)
    private String shippingType;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderId) {
        this.orderNumber = orderId;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
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

    public void setUserID(int productCategoryID) {
        this.userID = productCategoryID;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
