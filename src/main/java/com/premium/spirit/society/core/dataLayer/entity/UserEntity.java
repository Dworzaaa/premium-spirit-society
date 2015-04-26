package com.premium.spirit.society.core.dataLayer.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The Class UserEntity.
 */
@Entity
@Table(name = "USER")
public class UserEntity {

    /**
     * The id.
     */
    @Id
    @Column(name = "ID", precision = 6, scale = 0, nullable = false)
    @GeneratedValue
    private int id;

    /**
     * The username.
     */
    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    /**
     * The password.
     */
    @Column(name = "PASSWORD", precision = 128)
    private String password;

   /* @OneToMany(fetch = FetchType.LAZY)
    private Set<PatientEntity> patients;*/

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "HIDDEN")
    private boolean hidden;

    @Column(name="NEWSLETTER")
    private boolean newsletter;
    /**
     * The contact.
     */
    @OneToOne
    @Cascade({CascadeType.ALL})
    private ContactEntity contact;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({CascadeType.ALL})
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_ID", nullable = false, updatable = false)})
    private List<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade({CascadeType.ALL})
    private Set<OrderEntity> orders;

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> productSubcategoriesList) {
        this.orders = productSubcategoriesList;
    }

    /* getters and setters */


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

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the contact.
     *
     * @return the contact
     */
    public ContactEntity getContact() {
        return contact;
    }

    /**
     * Sets the contact.
     *
     * @param contact the new contact
     */
    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    /*public List<PatientEntity> getPatients() {
        CollectionConverter<PatientEntity> converter = new CollectionConverter<>();
        return converter.toList(this.patients);
    }

    public void setPatients(List<PatientEntity> patients) {
        CollectionConverter<PatientEntity> converter = new CollectionConverter<>();
        this.patients = converter.toSet(patients);
    }

    public void setPatients(Set<PatientEntity> patients) {
        this.patients = patients;
    }
*/
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
