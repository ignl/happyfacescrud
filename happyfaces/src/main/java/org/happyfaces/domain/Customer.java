package org.happyfaces.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.happyfaces.domain.base.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Customer domain model.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {

    /** */
    private static final long serialVersionUID = 1L;

    /** */
    private static final int MAX_PHONE_NUMBER_LENGTH = 10;

    /** */
    private static final int MIN_PHONE_NUMBER_LENGTH = 7;

    /** Customer name. */
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String name;

    /** Customer's accounts. */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    /** Customer city. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    private City city;

    /** Customer address. */
    @Column(name = "ADDRESS")
    private String address;

    /** Customer email. */
    @Email
    @Column(name = "EMAIL")
    private String email;

    /** Customer phone number. */
    @Length(min = MIN_PHONE_NUMBER_LENGTH, max = MAX_PHONE_NUMBER_LENGTH)
    @Column(name = "PHONE")
    private String phone;

    /** Customer age. */
    @Column(name = "AGE")
    private Integer age;

    /** Customer perks. */
    @ManyToMany
    @JoinTable(name = "CUSTOMER_CUSTOMERPERK", joinColumns = @JoinColumn(name = "CUSTOMER_ID"), inverseJoinColumns = @JoinColumn(name = "PERK_ID"))
    private List<CustomerPerk> perks;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<CustomerPerk> getPerks() {
        return perks;
    }

    public void setPerks(List<CustomerPerk> perks) {
        this.perks = perks;
    }

}