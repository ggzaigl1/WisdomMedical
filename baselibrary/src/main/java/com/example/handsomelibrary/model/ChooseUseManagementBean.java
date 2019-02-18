package com.example.handsomelibrary.model;

import java.io.Serializable;

/**
 * Created by QKun on 2018/7/12.
 * 选择就诊人
 */

public class ChooseUseManagementBean implements Serializable{

    public ChooseUseManagementBean(boolean isSelected) {
        this.isSelected = isSelected;
    }

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * id : 53
     * cusId : 53
     * memberName : 张静
     * idType : 0
     * idNumber : 420500199802051123
     * gender : 0
     * birthday : 2001-09-05
     * mobile : 18963257415
     * email : null
     * relation : 0
     * isMarried : null
     * isDefault : 1
     * province : 上海市
     * city : 上海市
     * district : 青浦区
     * street : 后天去吧
     * postcode : null
     * gmtCreate : 2018-09-05 17:42:21
     * gmtModified : 2018-09-10 19:32:19
     */

    private int id;
    private int cusId;
    private String memberName;
    private int idType;
    private String idNumber;
    private int gender;
    private String birthday;
    private String mobile;
    private Object email;
    private int relation;
    private int isMarried;
    private int isDefault;
    private String province;
    private String city;
    private String district;
    private String street;
    private Object postcode;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public int getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(int isMarried) {
        this.isMarried = isMarried;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
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

    public Object getPostcode() {
        return postcode;
    }

    public void setPostcode(Object postcode) {
        this.postcode = postcode;
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