package com.premium.spirit.society.core.businessLayer.BO.form;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

public class UserFormBO {

    private int id;

    /**
     * The username.
     */
    @Size(min = 1, max = 20)
    private String username;

    /**
     * The password.
     */
    @Size(min = 8, max = 128)
    private String password;

    private boolean hidden;

    private boolean customer;

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * The contact.
     */
    @Valid
    private ContactFormBO contact;

    private List<RoleFormBO> roles;
    private List<OrderFormBO> orders;

    /* getters and setters */

    public List<OrderFormBO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderFormBO> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<RoleFormBO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleFormBO> roles) {
        this.roles = roles;
    }

    public ContactFormBO getContact() {
        return contact;
    }

    public void setContact(ContactFormBO contact) {
        this.contact = contact;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }



}
