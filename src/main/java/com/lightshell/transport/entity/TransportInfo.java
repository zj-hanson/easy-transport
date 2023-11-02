package com.lightshell.transport.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "transport_info")
public class TransportInfo implements Serializable {

    private Set<TransportTime> timeList = new HashSet<>();

    private int id;
    private String openId;
    private String formId;
    private String plateNumber;
    private BigDecimal maximumLoad;
    private String summary;
    private String content;
    private BigDecimal weight;
    private BigDecimal weightUnitPrice;
    private BigDecimal weightFreight;
    private BigDecimal volume;
    private BigDecimal volumeUnitPrice;
    private BigDecimal volumeFreight;
    private BigDecimal freight;
    private String consignor;
    private String consignorPhone;
    private String consignorOpenId;
    private String consignorCompany;
    private String consignorCompanyId;
    private String consignorAddress;
    private String consignorTel;
    private String consignorFax;
    private String consignorDemand;
    private String consignorComment;
    private String consignee;
    private String consigneePhone;
    private String consigneeOpenId;
    private String consigneeCompany;
    private String consigneeCompanyId;
    private String consigneeAddress;
    private String consigneeTel;
    private String consigneeFax;
    private String consigneeDemand;
    private String consigneeComment;
    private String carrier;
    private String carrierPhone;
    private String carrierOpenId;
    private String carrierCompany;
    private String carrierCompanyId;
    private String uid;
    private String status;
    private String creator;
    private Date credate;
    private String optuser;
    private Date optdate;
    private String cfmuser;
    private Date cfmdate;

    public TransportInfo() {
        this.weight = BigDecimal.ZERO;
        this.weightUnitPrice = BigDecimal.ZERO;
        this.weightFreight = BigDecimal.ZERO;
        this.volume = BigDecimal.ZERO;
        this.volumeUnitPrice = BigDecimal.ZERO;
        this.volumeFreight = BigDecimal.ZERO;
        this.freight = BigDecimal.ZERO;
        this.uid = UUID.randomUUID().toString().replaceAll("-", "");
        this.status = "N";
    }

    @OneToMany(targetEntity = TransportTime.class, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true,
        fetch = FetchType.EAGER)
    @OrderBy(value = "seq")
    public Set<TransportTime> getTimeList() {
        return timeList;
    }

    public void setTimeList(Set<TransportTime> timeList) {
        this.timeList = timeList;
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
    @Column(name = "form_id", nullable = true, length = 45)
    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
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
    @Column(name = "maximum_load", nullable = false, precision = 4)
    public BigDecimal getMaximumLoad() {
        return maximumLoad;
    }

    public void setMaximumLoad(BigDecimal maximumLoad) {
        this.maximumLoad = maximumLoad;
    }

    @Basic
    @Column(name = "summary", nullable = true, length = 45)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "weight", nullable = false, precision = 2)
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "weight_unit_price", nullable = false, precision = 4)
    public BigDecimal getWeightUnitPrice() {
        return weightUnitPrice;
    }

    public void setWeightUnitPrice(BigDecimal weightUnitPrice) {
        this.weightUnitPrice = weightUnitPrice;
    }

    @Basic
    @Column(name = "weight_freight", nullable = false, precision = 2)
    public BigDecimal getWeightFreight() {
        return weightFreight;
    }

    public void setWeightFreight(BigDecimal weightFreight) {
        this.weightFreight = weightFreight;
    }

    @Basic
    @Column(name = "volume", nullable = false, precision = 2)
    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Basic
    @Column(name = "volume_unit_price", nullable = false, precision = 2)
    public BigDecimal getVolumeUnitPrice() {
        return volumeUnitPrice;
    }

    public void setVolumeUnitPrice(BigDecimal volumeUnitPrice) {
        this.volumeUnitPrice = volumeUnitPrice;
    }

    @Basic
    @Column(name = "volume_freight", nullable = false, precision = 2)
    public BigDecimal getVolumeFreight() {
        return volumeFreight;
    }

    public void setVolumeFreight(BigDecimal volumeFreight) {
        this.volumeFreight = volumeFreight;
    }

    @Basic
    @Column(name = "freight", nullable = false, precision = 2)
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    @Basic
    @Column(name = "consignor", nullable = true, length = 45)
    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    @Basic
    @Column(name = "consignor_phone", nullable = true, length = 20)
    public String getConsignorPhone() {
        return consignorPhone;
    }

    public void setConsignorPhone(String consignorPhone) {
        this.consignorPhone = consignorPhone;
    }

    @Basic
    @Column(name = "consignor_open_id", nullable = true, length = 45)
    public String getConsignorOpenId() {
        return consignorOpenId;
    }

    public void setConsignorOpenId(String consignorOpenId) {
        this.consignorOpenId = consignorOpenId;
    }

    @Basic
    @Column(name = "consignor_company", nullable = true, length = 100)
    public String getConsignorCompany() {
        return consignorCompany;
    }

    public void setConsignorCompany(String consignorCompany) {
        this.consignorCompany = consignorCompany;
    }

    @Basic
    @Column(name = "consignor_company_id", nullable = true, length = 45)
    public String getConsignorCompanyId() {
        return consignorCompanyId;
    }

    public void setConsignorCompanyId(String consignorCompanyId) {
        this.consignorCompanyId = consignorCompanyId;
    }

    @Basic
    @Column(name = "consignor_address", nullable = true, length = 200)
    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress;
    }

    @Basic
    @Column(name = "consignor_tel", nullable = true, length = 45)
    public String getConsignorTel() {
        return consignorTel;
    }

    public void setConsignorTel(String consignorTel) {
        this.consignorTel = consignorTel;
    }

    @Basic
    @Column(name = "consignor_fax", nullable = true, length = 45)
    public String getConsignorFax() {
        return consignorFax;
    }

    public void setConsignorFax(String consignorFax) {
        this.consignorFax = consignorFax;
    }

    @Basic
    @Column(name = "consignor_demand", nullable = true, length = 200)
    public String getConsignorDemand() {
        return consignorDemand;
    }

    public void setConsignorDemand(String consignorDemand) {
        this.consignorDemand = consignorDemand;
    }

    @Basic
    @Column(name = "consignor_comment", nullable = true, length = 100)
    public String getConsignorComment() {
        return consignorComment;
    }

    public void setConsignorComment(String consignorComment) {
        this.consignorComment = consignorComment;
    }

    @Basic
    @Column(name = "consignee", nullable = true, length = 45)
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Basic
    @Column(name = "consignee_phone", nullable = true, length = 20)
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    @Basic
    @Column(name = "consignee_open_id", nullable = true, length = 45)
    public String getConsigneeOpenId() {
        return consigneeOpenId;
    }

    public void setConsigneeOpenId(String consigneeOpenId) {
        this.consigneeOpenId = consigneeOpenId;
    }

    @Basic
    @Column(name = "consignee_company", nullable = true, length = 100)
    public String getConsigneeCompany() {
        return consigneeCompany;
    }

    public void setConsigneeCompany(String consigneeCompany) {
        this.consigneeCompany = consigneeCompany;
    }

    @Basic
    @Column(name = "consignee_company_id", nullable = true, length = 45)
    public String getConsigneeCompanyId() {
        return consigneeCompanyId;
    }

    public void setConsigneeCompanyId(String consigneeCompanyId) {
        this.consigneeCompanyId = consigneeCompanyId;
    }

    @Basic
    @Column(name = "consignee_address", nullable = true, length = 200)
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Basic
    @Column(name = "consignee_tel", nullable = true, length = 45)
    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    @Basic
    @Column(name = "consignee_fax", nullable = true, length = 45)
    public String getConsigneeFax() {
        return consigneeFax;
    }

    public void setConsigneeFax(String consigneeFax) {
        this.consigneeFax = consigneeFax;
    }

    @Basic
    @Column(name = "consignee_demand", nullable = true, length = 200)
    public String getConsigneeDemand() {
        return consigneeDemand;
    }

    public void setConsigneeDemand(String consigneeDemand) {
        this.consigneeDemand = consigneeDemand;
    }

    @Basic
    @Column(name = "consignee_comment", nullable = true, length = 100)
    public String getConsigneeComment() {
        return consigneeComment;
    }

    public void setConsigneeComment(String consigneeComment) {
        this.consigneeComment = consigneeComment;
    }

    @Basic
    @Column(name = "carrier", nullable = true, length = 45)
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Basic
    @Column(name = "carrier_phone", nullable = true, length = 20)
    public String getCarrierPhone() {
        return carrierPhone;
    }

    public void setCarrierPhone(String carrierPhone) {
        this.carrierPhone = carrierPhone;
    }

    @Basic
    @Column(name = "carrier_open_id", nullable = true, length = 45)
    public String getCarrierOpenId() {
        return carrierOpenId;
    }

    public void setCarrierOpenId(String carrierOpenId) {
        this.carrierOpenId = carrierOpenId;
    }

    @Basic
    @Column(name = "carrier_company", nullable = true, length = 100)
    public String getCarrierCompany() {
        return carrierCompany;
    }

    public void setCarrierCompany(String carrierCompany) {
        this.carrierCompany = carrierCompany;
    }

    @Basic
    @Column(name = "carrier_company_id", nullable = true, length = 45)
    public String getCarrierCompanyId() {
        return carrierCompanyId;
    }

    public void setCarrierCompanyId(String carrierCompanyId) {
        this.carrierCompanyId = carrierCompanyId;
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
        TransportInfo that = (TransportInfo)o;
        return id == that.id || Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formId, plateNumber, maximumLoad, summary, content, weight, weightUnitPrice,
            weightFreight, volume, volumeUnitPrice, volumeFreight, freight, consignor, consignorPhone, consignorOpenId,
            consignorCompany, consignorCompanyId, consignorAddress, consignorTel, consignorFax, consignorDemand,
            consignorComment, consignee, consigneePhone, consigneeOpenId, consigneeCompany, consigneeCompanyId,
            consigneeAddress, consigneeTel, consigneeFax, consigneeDemand, consigneeComment, carrier, carrierPhone,
            carrierOpenId, carrierCompany, carrierCompanyId, uid, status, creator, credate, optuser, optdate, cfmuser,
            cfmdate);
    }
}
