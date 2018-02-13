package com.yaorange.jk.entity;

import java.io.Serializable;
import java.util.Set;

/**货物
 * @author coach tam
 * @date 2017/12/27
 */
public class ContractProduct implements Serializable {
    private String id;
    private String factoryName;
    private String productNo;
    private String productImage;
    private String productDesc;
    private String loadingRate;
    private Long boxNum;
    private String packingUnit;
    private Long cnumber;
    private Long outNumber;
    private Long finished;
    private String productRequest;
    private Long price;
    private Long amount;
    private Long orderNo;

    private Contract contract;//所属购销合同
    private Factory factory;
    private Set<ExtCproduct> extCproducts;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getLoadingRate() {
        return loadingRate;
    }

    public void setLoadingRate(String loadingRate) {
        this.loadingRate = loadingRate;
    }

    public Long getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Long boxNum) {
        this.boxNum = boxNum;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Long getCnumber() {
        return cnumber;
    }

    public void setCnumber(Long cnumber) {
        this.cnumber = cnumber;
    }

    public Long getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Long outNumber) {
        this.outNumber = outNumber;
    }

    public Long getFinished() {
        return finished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }

    public String getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(String productRequest) {
        this.productRequest = productRequest;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Set<ExtCproduct> getExtCproducts() {
        return extCproducts;
    }

    public void setExtCproducts(Set<ExtCproduct> extCproducts) {
        this.extCproducts = extCproducts;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
