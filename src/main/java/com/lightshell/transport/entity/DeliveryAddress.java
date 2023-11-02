package com.lightshell.transport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "delivery_address")
public class DeliveryAddress implements Serializable {
    private int id;
    private String openId;
    private String company;
    private String simpleName;
    private String contactPerson;
    private String phone;
    private String address;
    private String city;
    private String province;
    private String country;
    private String tenantId;
    private String uid;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    public DeliveryAddress() {
        this.uid = UUID.randomUUID().toString().replace("-", "");
        this.status = "0";
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "open_id", nullable = false, length = 45)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 200)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "simple_name", nullable = true, length = 45)
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @Basic
    @Column(name = "contact_person", nullable = false, length = 45)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "province", nullable = true, length = 45)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 45)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "tenant_id", nullable = true, length = 45)
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String parentId) {
        this.tenantId = parentId;
    }

    @Basic
    @Column(name = "uid", nullable = false, length = 45)
    public String getUID() {
        return uid;
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 20)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "credate", nullable = true)
    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    @Basic
    @Column(name = "optuser", nullable = true, length = 20)
    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    @Basic
    @Column(name = "optdate", nullable = true)
    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    @Basic
    @Column(name = "cfmuser", nullable = true, length = 20)
    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    @Basic
    @Column(name = "cfmdate", nullable = true)
    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DeliveryAddress that = (DeliveryAddress)o;
        return id == that.id && Objects.equals(company, that.company) && Objects.equals(simpleName, that.simpleName)
            && Objects.equals(contactPerson, that.contactPerson) && Objects.equals(phone, that.phone)
            && Objects.equals(address, that.address) && Objects.equals(city, that.city)
            && Objects.equals(province, that.province) && Objects.equals(country, that.country)
            && Objects.equals(uid, that.uid) && Objects.equals(status, that.status)
            && Objects.equals(creator, that.creator) && Objects.equals(credate, that.credate)
            && Objects.equals(optuser, that.optuser) && Objects.equals(optdate, that.optdate)
            && Objects.equals(cfmuser, that.cfmuser) && Objects.equals(cfmdate, that.cfmdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, simpleName, contactPerson, phone, address, city, province, country, uid,
            status, creator, credate, optuser, optdate, cfmuser, cfmdate);
    }
}
