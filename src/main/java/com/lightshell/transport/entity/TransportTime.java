package com.lightshell.transport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "transport_time")
public class TransportTime implements Serializable {

    private TransportInfo parent;
    private int id;
    private String openId;
    private String parentId;
    private int seq;
    private String kind;
    private String plateNumber;
    private String carrier;
    private String customer;
    private String address;
    private String tenantId;
    private Date plannedArrivalDate;
    private Date plannedArrivalTime;
    private Date plannedDepartureDate;
    private Date plannedDepartureTime;
    private Date actualArrivalDate;
    private Date actualDepartureDate;
    private String uid;
    private String status;

    public TransportTime() {
        this.uid = UUID.randomUUID().toString().replaceAll("-", "");
        this.status = "N";
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "uid", insertable = false, updatable = false)
    public TransportInfo getParent() {
        return parent;
    }

    public void setParent(TransportInfo parent) {
        this.parent = parent;
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
    @Column(name = "parent_id", nullable = false, length = 45)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "seq", nullable = false)
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Basic
    @Column(name = "kind", nullable = false, length = 10)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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
    @Column(name = "carrier", nullable = false, length = 45)
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Basic
    @Column(name = "customer", nullable = false, length = 100)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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
    @Column(name = "tenant_id", nullable = true, length = 45)
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "planned_arrival_date", nullable = false)
    public Date getPlannedArrivalDate() {
        return plannedArrivalDate;
    }

    public void setPlannedArrivalDate(Date plannedArrivalDate) {
        this.plannedArrivalDate = plannedArrivalDate;
    }

    @Basic
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    @Column(name = "planned_arrival_time", nullable = false)
    public Date getPlannedArrivalTime() {
        return plannedArrivalTime;
    }

    public void setPlannedArrivalTime(Date plannedArrivalTime) {
        this.plannedArrivalTime = plannedArrivalTime;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "planned_departure_date", nullable = true)
    public Date getPlannedDepartureDate() {
        return plannedDepartureDate;
    }

    public void setPlannedDepartureDate(Date plannedDepartureDate) {
        this.plannedDepartureDate = plannedDepartureDate;
    }

    @Basic
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    @Column(name = "planned_departure_time", nullable = true)
    public Date getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    public void setPlannedDepartureTime(Date plannedDepartureTime) {
        this.plannedDepartureTime = plannedDepartureTime;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "actual_arrival_date", nullable = true)
    public Date getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(Date actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "actual_departure_date", nullable = true)
    public Date getActualDepartureDate() {
        return actualDepartureDate;
    }

    public void setActualDepartureDate(Date actualDepartureDate) {
        this.actualDepartureDate = actualDepartureDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TransportTime that = (TransportTime)o;
        return id == that.id || Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, uid, status);
    }
}
