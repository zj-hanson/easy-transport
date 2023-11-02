package com.lightshell.transport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "vehicle_info")
public class VehicleInfo  implements Serializable {
    private int id;
    private String openId;
    private String category;
    private String plateNumber;
    private String brand;
    private String model;
    private BigDecimal maximumLoad;
    private String dimension;
    private BigDecimal limitedLength;
    private BigDecimal limitedWidth;
    private BigDecimal limitedHeight;
    private String owner;
    private String remark;
    private String uid;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    public VehicleInfo() {
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
    @Column(name = "open_id", nullable = false, length = 45)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "category", nullable = false, length = 20)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "plate_number", nullable = false, length = 20)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Basic
    @Column(name = "brand", nullable = true, length = 45)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 45)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "maximum_load", nullable = false, precision = 4)
    public BigDecimal getMaximumLoad() {
        return maximumLoad;
    }

    public void setMaximumLoad(BigDecimal load) {
        this.maximumLoad = load;
    }

    @Basic
    @Column(name = "dimension", nullable = true, length = 45)
    public String getDimension() {
        return dimension;
    }

    public void setDimension(String size) {
        this.dimension = size;
    }

    @Basic
    @Column(name = "limited_length", nullable = true, precision = 4)
    public BigDecimal getLimitedLength() {
        return limitedLength;
    }

    public void setLimitedLength(BigDecimal limitedLength) {
        this.limitedLength = limitedLength;
    }

    @Basic
    @Column(name = "limited_width", nullable = true, precision = 4)
    public BigDecimal getLimitedWidth() {
        return limitedWidth;
    }

    public void setLimitedWidth(BigDecimal limitedWidth) {
        this.limitedWidth = limitedWidth;
    }

    @Basic
    @Column(name = "limited_height", nullable = true, precision = 4)
    public BigDecimal getLimitedHeight() {
        return limitedHeight;
    }

    public void setLimitedHeight(BigDecimal limitedHeight) {
        this.limitedHeight = limitedHeight;
    }

    @Basic
    @Column(name = "owner", nullable = true, length = 45)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleInfo that = (VehicleInfo) o;
        return id == that.id || Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid);
    }
}
