package com.example.handsomelibrary.model;

/**
 * Created by 初夏小溪 on 2018/9/1 0001.
 * 更新配送地址实体类
 */
public class GetAddressInfoBean {

    /**
     * id : 69
     * cusId : 25
     * consigneeName : 刘德华
     * mobile : 18671166185
     * country :
     * province : 广西省
     * city : 贵港市
     * district : 覃塘区
     * street : nullnull山北乡回来
     * postcode : null
     * isDefault : 1
     * gmtCreate : 2018-08-31 17:03:22
     * gmtModified : 2018-09-01 11:08:48
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
    private String postcode;
    private int isDefault;
    private String gmtCreate;
    private String gmtModified;

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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
