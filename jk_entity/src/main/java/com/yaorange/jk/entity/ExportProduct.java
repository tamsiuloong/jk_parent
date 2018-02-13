package com.yaorange.jk.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * * 报运货物
 * @author coach tam
 * @date 2018/1/2
 */
public class ExportProduct implements Serializable {
    @JSONField(serialize = false)
    private Export export;
    private String id;
    private String productNo;
    private String packingUnit;
    private Long cnumber;
    private Long boxNum;
    private Long grossWeight;
    private Long netWeight;
    private Long sizeLength;
    private Long sizeWidth;
    private Long sizeHeight;
    private Long exPrice;//sales confirmation 中的价格（手填）
    private Long price;
    private Long tax;    ////收购单价=合同单价
    private Long orderNo;
    @JSONField(serialize = false)
    private Factory factory;//一个货物对应一个厂家
    @JSONField(serialize = false)
    private Set<ExtEproduct> extEproducts= new HashSet<>();//一个报运货物 对应 多个报运货物附件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public Long getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Long boxNum) {
        this.boxNum = boxNum;
    }

    public Long getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Long grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Long getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Long netWeight) {
        this.netWeight = netWeight;
    }

    public Long getSizeLength() {
        return sizeLength;
    }

    public void setSizeLength(Long sizeLength) {
        this.sizeLength = sizeLength;
    }

    public Long getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Long sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public Long getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Long sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public Long getExPrice() {
        return exPrice;
    }

    public void setExPrice(Long exPrice) {
        this.exPrice = exPrice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Set<ExtEproduct> getExtEproducts() {
        return extEproducts;
    }

    public void setExtEproducts(Set<ExtEproduct> extEproducts) {
        this.extEproducts = extEproducts;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
