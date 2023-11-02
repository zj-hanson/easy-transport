package com.lightshell.transport.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "system_user")
public class SystemUser {
    private int id;
    private String userid;
    private String username;
    private String password;
    private String phone;
    private String tel;
    private String email;
    private Byte superuser;
    private Integer failure;
    private Byte locked;
    private Date lastLogin;
    private Date newestLogin;
    private String uid;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = false, length = 20)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 20)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "superuser", nullable = true)
    public Byte getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Byte superuser) {
        this.superuser = superuser;
    }

    @Basic
    @Column(name = "failure", nullable = true)
    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    @Basic
    @Column(name = "locked", nullable = true)
    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "lastlogin", nullable = true)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "newestlogin", nullable = true)
    public Date getNewestLogin() {
        return newestLogin;
    }

    public void setNewestLogin(Date newestLogin) {
        this.newestLogin = newestLogin;
    }

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
    @Temporal(value = TemporalType.TIMESTAMP)
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
    @Temporal(value = TemporalType.TIMESTAMP)
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
    @Temporal(value = TemporalType.TIMESTAMP)
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
        SystemUser that = (SystemUser)o;
        return id == that.id && Objects.equals(userid, that.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, username);
    }
}
