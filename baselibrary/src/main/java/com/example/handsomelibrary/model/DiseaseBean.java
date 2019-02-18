package com.example.handsomelibrary.model;

import java.util.List;

/**
 * Created by QKun on 2018/7/12.
 */

public class DiseaseBean {

    private List<ListCommonDiseaseBean> listCommonDisease;
    private List<ListVisitMembersBean> listVisitMembers;

    public List<ListCommonDiseaseBean> getListCommonDisease() {
        return listCommonDisease;
    }

    public void setListCommonDisease(List<ListCommonDiseaseBean> listCommonDisease) {
        this.listCommonDisease = listCommonDisease;
    }

    public List<ListVisitMembersBean> getListVisitMembers() {
        return listVisitMembers;
    }

    public void setListVisitMembers(List<ListVisitMembersBean> listVisitMembers) {
        this.listVisitMembers = listVisitMembers;
    }

    public static class ListCommonDiseaseBean {
        /**
         * id : 3
         * diseaseName : 中暑
         * diseaseInfo : null
         * gmtCreate : 2018-07-03 11:40:24
         * gmtModified : null
         */

        private int id;
        private String diseaseName;
        private String diseaseInfo;
        private String gmtCreate;
        private String gmtModified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDiseaseName() {
            return diseaseName;
        }

        public void setDiseaseName(String diseaseName) {
            this.diseaseName = diseaseName;
        }

        public String getDiseaseInfo() {
            return diseaseInfo;
        }

        public void setDiseaseInfo(String diseaseInfo) {
            this.diseaseInfo = diseaseInfo;
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

    public static class ListVisitMembersBean {
        /**
         * id : 4
         * cusId : 222
         * memberName : 2
         * idType : null
         * idNumber : null
         * gender : null
         * birthday : null
         * mobile : null
         * email : null
         * relation : null
         * isMarried : null
         * isDefault : 0
         * province : null
         * city : null
         * district : null
         * street : null
         * postcode : null
         * gmtCreate : 2018-07-11 09:24:08
         * gmtModified : null
         */

        private int id;
        private int cusId;
        private String memberName;
        private int idType;
        private String idNumber;
        private int gender;
        private String birthday;
        private String mobile;
        private String email;
        private String relation;
        private String isMarried;
        private int isDefault;
        private String province;
        private String city;
        private String district;
        private String street;
        private String postcode;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getIsMarried() {
            return isMarried;
        }

        public void setIsMarried(String isMarried) {
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

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
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
}
