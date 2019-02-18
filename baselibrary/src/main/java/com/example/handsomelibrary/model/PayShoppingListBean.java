package com.example.handsomelibrary.model;

import java.io.Serializable;

/**
 * 商城配送地址列表
 * Created by
 */
public class PayShoppingListBean implements Serializable {

    /**
     * id : 93
     * cusId : 55
     * consigneeName : 傻大个
     * mobile : 15369988745
     * country : 中国
     * province : 陕西
     * city : 宝鸡市
     * district : 陈仓区磻溪镇
     * street : 通过哈哈v
     * postcode : 433699
     * isDefault : null
     * gmtCreate : 2018-12-03 14:13:52
     * gmtModified : null
     */

    private int id;
    private int cusId;
    private String consigneeName;
    private String mobile;
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;
    private int postcode;
    private Object isDefault;
    private String gmtCreate;
    private Object gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public Object getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Object isDefault) {
        this.isDefault = isDefault;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Object getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Object gmtModified) {
        this.gmtModified = gmtModified;
    }
}