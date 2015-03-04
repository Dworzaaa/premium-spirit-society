package com.premium.spirit.society.core.businessLayer.BO.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactFormBO {

    private int id;

    /**
     * The first name.
     */
    @Size(min = 1, max = 100)
    private String firstName;

    /**
     * The last name.
     */
    @Size(min = 1, max = 100)
    private String lastName;

    /**
     * The address street.
     */
    @Size(max = 100)
    private String addressStreet;

    /**
     * The address hn.
     */
    @Pattern(regexp = "[0-9]*")
    @Size(max = 10)
    private String addressHn;

    /**
     * The address city.
     */
    @Size(max = 100)
    private String addressCity;

    /**
     * The address postalcode.
     */
    // add blank option   @Pattern(regexp = "[1-9]+[0-9]*")
    @Size(max = 20)
    private String addressPostalcode;

    /**
     * The address country.
     */
    @Size(max = 100)
    private String addressCountry;

    /**
     * The phone number.
     */
    // add blank option   @Pattern(regexp = "[0-9]+[0-9]*")
    @Size(max = 20)
    private String phoneNumber;

    /**
     * The email.
     */
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String shippingAddressStreet;

    @Size(max = 10)
    private String shippingAddressHn;

    @Size(max = 100)
    private String shippingAddressCity;

    @Size(max = 100)
    private String shippingAddressPostalCode;

    @Size(max = 100)
    private String shippingAddressCountry;

    @Size(max = 100)
    private String companyName;

    @Size(max = 100)
    private String companyId;

    @Size(max = 100)
    private String vatId;
    /* getters and setters */

    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }

    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }

    public String getShippingAddressHn() {
        return shippingAddressHn;
    }

    public void setShippingAddressHn(String shippingAddressHn) {
        this.shippingAddressHn = shippingAddressHn;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressPostalCode() {
        return shippingAddressPostalCode;
    }

    public void setShippingAddressPostalCode(String shippingAddressPostalCode) {
        this.shippingAddressPostalCode = shippingAddressPostalCode;
    }

    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getVatId() {
        return vatId;
    }

    public void setVatId(String vatId) {
        this.vatId = vatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressHn() {
        return addressHn;
    }

    public void setAddressHn(String addressHn) {
        this.addressHn = addressHn;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressPostalcode() {
        return addressPostalcode;
    }

    public void setAddressPostalcode(String addressPostalcode) {
        this.addressPostalcode = addressPostalcode;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
