package com.yaorange.jk.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**购销合同
 * @author coach tam
 * @date 2017/12/27
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","contractProducts"})
public class Contract extends BaseEntity {
    private String id;
    private String offeror;
    private String contractNo;
    private Date signingDate;
    private String inputBy;
    private String checkBy;
    private String inspector;
    private Long totalAmount;
    private String crequest;
    private String customName;
    private Date shipTime;
    private Long importNum;
    private Date deliveryPeriod;//交货日期
    private Long oldState;
    private Long outState;
    private String tradeTerms;
    private String printStyle;
    private String remark;
    private Long state;//0草稿 1已上报待报运  2已保运


    private Set<ContractProduct> contractProducts;


    //vo 只用于view展示
    private Long pNum;//货物数量
    private Long extCNum;//附件数量
    private String nums;//货物/附件 数量


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCrequest() {
        return crequest;
    }

    public void setCrequest(String crequest) {
        this.crequest = crequest;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Long getImportNum() {
        return importNum;
    }

    public void setImportNum(Long importNum) {
        this.importNum = importNum;
    }

    public Date getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(Date deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public Long getOldState() {
        return oldState;
    }

    public void setOldState(Long oldState) {
        this.oldState = oldState;
    }

    public Long getOutState() {
        return outState;
    }

    public void setOutState(Long outState) {
        this.outState = outState;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(String printStyle) {
        this.printStyle = printStyle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Set<ContractProduct> getContractProducts() {
        return contractProducts;
    }

    public void setContractProducts(Set<ContractProduct> contractProducts) {
        this.contractProducts = contractProducts;
    }

    public Long getpNum() {
        pNum=0l;
        if(contractProducts!=null)
        {
            pNum = new Long(this.contractProducts.size());
        }

        return pNum;
    }

    public Long getExtCNum() {
        extCNum = 0L;
        if(contractProducts!=null)
        {
            contractProducts.forEach(cp -> {cp.getExtCproducts().forEach(extCproduct -> {extCNum+=extCproduct.getCnumber();});});
        }

        return extCNum;
    }

    public String getNums() {
        return getpNum()+"/"+getExtCNum();
    }

}
