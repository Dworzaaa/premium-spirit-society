package com.premium.spirit.society.core.businessLayer.BO.display;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Martin on 11. 1. 2015.
 */
public class OrderDisplayBO {

    private int id;

    @Size(max = 1000)
    private String note;

    private boolean hidden;

    private int state;

    private Date date;

    private String shippingType;

    private String paymentMethod;

    private String invoice;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
