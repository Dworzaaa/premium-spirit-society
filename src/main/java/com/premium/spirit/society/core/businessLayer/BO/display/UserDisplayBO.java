package com.premium.spirit.society.core.businessLayer.BO.display;

import com.premium.spirit.society.core.businessLayer.BO.form.RoleFormBO;

import java.util.List;

public class UserDisplayBO {

    private int id;

    private String username;

    private ContactDisplayBO contact;

    private List<RoleFormBO> roles;

    private boolean customer;

    private boolean enabled;

    private boolean hidden;

    private boolean newsletter;

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

    public ContactDisplayBO getContact() {
        return contact;
    }

    public void setContact(ContactDisplayBO contact) {
        this.contact = contact;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public List<RoleFormBO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleFormBO> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }
}
