package com.lightshell.transport.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Tenant {
    private int id;
    private String name;
    private String simpleName;
    private String address;
    private String contactPerson;
    private String phone;
    private String parentId;
    private String uid;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    public Tenant() {
        this.uid = UUID.randomUUID().toString().replaceAll("-", "");
        this.status = "N";
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
    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "address", nullable = true, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "contact_person", nullable = true, length = 45)
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
    @Column(name = "parent_id", nullable = true, length = 45)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "uid", nullable = true, length = 45)
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
        Tenant tenant = (Tenant)o;
        return id == tenant.id || Objects.equals(name, tenant.name) || Objects.equals(uid, tenant.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uid);
    }
}
