package com.lightshell.transport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "wechat_user")
public class WechatUser implements Serializable {
    private int id;
    private String name;
    private String nickname;
    private String sex;
    private String born;
    private String phone;
    private String language;
    private String city;
    private String province;
    private String country;
    private boolean subscribe;
    private Integer subscribeTime;
    private String openId;
    private String unionId;
    private String role;
    private String remark;
    private String appId;
    private boolean authorized;
    private String employeeId;
    private String employeeName;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    public WechatUser() {

    }

    public WechatUser(String name, String role, String phone, String openId, String unionId, boolean authorized) {
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.openId = openId;
        this.unionId = unionId;
        this.authorized = authorized;
        this.status = "V";
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
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 10)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "born", nullable = true, length = 45)
    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
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
    @Column(name = "language", nullable = true, length = 20)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
    @Column(name = "subscribe", nullable = true)
    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    @Basic
    @Column(name = "subscribe_time", nullable = true)
    public Integer getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Integer subscribeTime) {
        this.subscribeTime = subscribeTime;
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
    @Column(name = "union_id", nullable = true, length = 45)
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 45)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "app_id", nullable = true, length = 45)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "authorized", nullable = false)
    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Basic
    @Column(name = "employee_id", nullable = true, length = 45)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "employee_name", nullable = true, length = 45)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WechatUser that = (WechatUser) o;
        return id == that.id || Objects.equals(openId, that.openId) || Objects.equals(unionId, that.unionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openId, unionId);
    }

}
