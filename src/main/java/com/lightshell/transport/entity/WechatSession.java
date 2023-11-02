package com.lightshell.transport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "wechat_session")
public class WechatSession  implements Serializable {
    private int id;
    private String openId;
    private String sessionKey;
    private String unionId;
    private String appId;
    private String sessionId;
    private String checkCode;
    private Integer expiresIn;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    public WechatSession() {

    }

    public WechatSession(String openId, String sessionKey, String unionId, String sessionId) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.unionId = unionId;
        this.sessionId = sessionId;
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
    @Column(name = "session_key", nullable = false, length = 45)
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
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
    @Column(name = "app_id", nullable = true, length = 45)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "session_id", nullable = false, length = 45)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "check_code", nullable = true, length = 10)
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Basic
    @Column(name = "expires_in", nullable = true)
    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
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
        WechatSession that = (WechatSession) o;
        return id == that.id && Objects.equals(openId, that.openId) && Objects.equals(sessionKey, that.sessionKey) && Objects.equals(unionId, that.unionId) && Objects.equals(appId, that.appId) && Objects.equals(sessionId, that.sessionId) && Objects.equals(checkCode, that.checkCode) && Objects.equals(expiresIn, that.expiresIn) && Objects.equals(status, that.status) && Objects.equals(creator, that.creator) && Objects.equals(credate, that.credate) && Objects.equals(optuser, that.optuser) && Objects.equals(optdate, that.optdate) && Objects.equals(cfmuser, that.cfmuser) && Objects.equals(cfmdate, that.cfmdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openId, sessionKey, unionId, appId, sessionId, checkCode, expiresIn, status, creator, credate, optuser, optdate, cfmuser, cfmdate);
    }

    public void setCreatorToSystem() {
        this.creator = "system";
    }

}
