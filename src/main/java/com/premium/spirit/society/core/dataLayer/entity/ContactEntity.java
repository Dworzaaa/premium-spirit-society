package com.premium.spirit.society.core.dataLayer.entity;

import javax.persistence.*;

/**
 * This class is an Entity class which holds the definition of a Contact.
 */
@Entity
@Table(name = "CONTACT")
public class ContactEntity {

    /**
     * The id.
     */
    @Id
    @Column(name = "ID", precision = 6, scale = 0, nullable = false)
    @GeneratedValue
    private int id;

    /**
     * The first name.
     */
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    /**
     * The last name.
     */
    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    /**
     * The address street.
     */
    @Column(name = "ADDRESS_STREET", length = 100, nullable = false)
    private String addressStreet;

    /**
     * The address hn.
     */
    @Column(name = "ADDRESS_HN", length = 20, nullable = false)
    private String addressHn;

    /**
     * The address city.
     */
    @Column(name = "ADDRESS_CITY", length = 100, nullable = false)
    private String addressCity;

    /**
     * The address postalcode.
     */
    @Column(name = "ADDRESS_POSTALCODE", length = 10, nullable = false)
    private String addressPostalcode;

    /**
     * The address country.
     */
    @Column(name = "ADDRESS_COUNTRY", length = 20, nullable = false)
    private String addressCountry;

    /**
     * The phone number.
     */

    @Column(name = "PHONE_NUMBER", length = 20, nullable = true)
    private String phoneNumber;

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

    /**
     * The email.
     */
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;


    @Column(name = "SHIPPING_ADDRESS_FIRST_NAME", length = 100, nullable = true)
    private String shippingFirstName;

    @Column(name = "SHIPPING_ADDRESS_LAST_NAME", length = 100, nullable = true)
    private String shippingLastName;

    @Column(name = "SHIPPING_ADDRESS_HN", length = 100, nullable = true)
    private String shippingAddressHn;

    @Column(name = "SHIPPING_ADDRESS_STREET", length = 100, nullable = true)
    private String shippingAddressStreet;

    @Column(name = "SHIPPING_ADDRESS_CITY", length = 100, nullable = true)
    private String shippingAddressCity;

    @Column(name = "SHIPPING_ADDRESS_POSTALCODE", length = 100, nullable = true)
    private String shippingAddressPostalcode;

    @Column(name = "SHIPPING_ADDRESS_COUNTRY", length = 100, nullable = true)
    private String shippingAddressCountry;

    @Column(name = "COMPANY_NAME", length = 100, nullable = true)
    private String companyName;

    @Column(name = "COMPANY_ID", length = 100, nullable = true)
    private String companyId;

    @Column(name = "VAT_ID", length = 100, nullable = true)
    private String vatId;

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the address street.
     *
     * @return the address street
     */
    public String getAddressStreet() {
        return addressStreet;
    }

    /**
     * Sets the address street.
     *
     * @param addressStreet the new address street
     */
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    /**
     * Gets the address hn.
     *
     * @return the address hn
     */
    public String getAddressHn() {
        return addressHn;
    }

    /**
     * Sets the address hn.
     *
     * @param addressHn the new address hn
     */
    public void setAddressHn(String addressHn) {
        this.addressHn = addressHn;
    }

    /**
     * Gets the address city.
     *
     * @return the address city
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets the address city.
     *
     * @param addressCity the new address city
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * Gets the address postalcode.
     *
     * @return the address postalcode
     */
    public String getAddressPostalcode() {
        return addressPostalcode;
    }

    /**
     * Sets the address postalcode.
     *
     * @param addressPostalcode the new address postalcode
     */
    public void setAddressPostalcode(String addressPostalcode) {
        this.addressPostalcode = addressPostalcode;
    }

    /**
     * Gets the address country.
     *
     * @return the address country
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     * Sets the address country.
     *
     * @param addressCountry the new address country
     */
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }

    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }
}
