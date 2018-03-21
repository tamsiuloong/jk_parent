package com.yaorange.jk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author coach tam
 * @date 2018/1/2
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","exportProducts"})
public class Export extends BaseEntity{
    private String id;
    private Date inputDate;				//制单日期
    private String contractIds;			//打断字段，报运相关的多个合同的ID集合串
    private String customerContract;	//合同及确认书号
    private String lcno;				//信用证号
    private String consignee;			//收货人及地址
    private String marks;				//唛头
    private String shipmentPort;		//装船港
    private String destinationPort;		//目的港
    private String transportMode;		//船运SEA/空运AIR 运输方式
    private String priceCondition;		//FBO/CIF价格条件
    private String remark;				//备注
    private Integer boxNums;			//冗余，为委托服务，一个报运的总箱数
    private Double grossWeights;		//冗余，为委托服务，一个报运的总毛重
    private Double measurements;		//冗余，为委托服务，一个报运的总体积

    private Integer state;				//0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务

    private Set<ExportProduct> exportProducts = new HashSet<>();

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

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getContractIds() {
        return contractIds;
    }

    public void setContractIds(String contractIds) {
        this.contractIds = contractIds;
    }

    public String getCustomerContract() {
        return customerContract;
    }

    public void setCustomerContract(String customerContract) {
        this.customerContract = customerContract;
    }

    public String getLcno() {
        return lcno;
    }

    public void setLcno(String lcno) {
        this.lcno = lcno;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getPriceCondition() {
        return priceCondition;
    }

    public void setPriceCondition(String priceCondition) {
        this.priceCondition = priceCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBoxNums() {
        return boxNums;
    }

    public void setBoxNums(Integer boxNums) {
        this.boxNums = boxNums;
    }

    public Double getGrossWeights() {
        return grossWeights;
    }

    public void setGrossWeights(Double grossWeights) {
        this.grossWeights = grossWeights;
    }

    public Double getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Double measurements) {
        this.measurements = measurements;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<ExportProduct> getExportProducts() {
        return exportProducts;
    }

    public void setExportProducts(Set<ExportProduct> exportProducts) {
        this.exportProducts = exportProducts;
    }

    public Long getpNum() {
        pNum=0l;
        if(exportProducts!=null)
        {
            pNum = new Long(this.exportProducts.size());
        }

        return pNum;
    }

    public Long getExtCNum() {
        extCNum = 0L;
        if(exportProducts!=null)
        {
            exportProducts.forEach(cp -> {cp.getExtEproducts().forEach(extCproduct -> {extCNum+=extCproduct.getCnumber();});});
        }

        return extCNum;
    }

    public String getNums() {
        return getpNum()+"/"+getExtCNum();
    }
}
