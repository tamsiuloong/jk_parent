package com.yaorange.jk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import oracle.sql.DATE;

import java.util.Date;

/**
 * @author coach tam
 * @date 2017/12/21
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","manager","user"})
public class UserInfo extends BaseEntity {

//    USER_INFO_ID         VARCHAR2(40)                    not null,
//    NAME                 VARCHAR2(20),
//    MANAGER_ID           VARCHAR2(40),
//    JOIN_DATE            TIMESTAMP,
//    SALARY               NUMERIC(8, 2),
//    BIRTHDAY             TIMESTAMP,
//    GENDER               CHAR(1),
//    STATION              VARCHAR2(20),
//    TELEPHONE            VARCHAR2(100),
//    DEGREE               INT,
//    REMARK               VARCHAR2(600),
//    ORDER_NO             INT,

    private String id;
    private String name;
    private User manager;//直属领导
    private Date joinDate;//入职日期
    private Double salary;
    private Date birthday;
    private String gender;
    private String station;//岗位
    private String telephone;//电话
    private Integer degree;//等级
    private String remark;
    private Integer orderNo;
    private String email;

    private User user;//所属用户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
