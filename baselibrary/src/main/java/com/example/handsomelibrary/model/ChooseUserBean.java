package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/7/12.
 * 个人资料 就诊人管理
 */

public class ChooseUserBean implements Serializable {

    public ChooseUserBean(boolean isSelected) {
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
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":58,"cusId":55,"memberName":"黄尚坤","idType":2,"idNumber":"420501199208151256","gender":1,"birthday":"2004-09-19","mobile":"15685236555","email":null,"relation":1,"isMarried":null,"isDefault":0,"province":"北京市","city":"北京市","district":"东城区","street":"洪山区","postcode":null,"gmtCreate":"2018-09-19 10:51:34","gmtModified":"2018-09-25 18:43:06"},{"id":54,"cusId":55,"memberName":"张静","idType":0,"idNumber":"412512555258412566","gender":0,"birthday":"1999-04-24","mobile":"15236525866","email":null,"relation":1,"isMarried":null,"isDefault":1,"province":"北京市","city":"北京市","district":"东城区","street":"图图","postcode":null,"gmtCreate":"2018-09-05 19:08:45","gmtModified":"2018-09-25 18:43:29"}]
     * total : 2
     * totalPages : 1
     * first : 1
     */

    private int offset;
    private int limit;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private int first;
    private List<RowsBean> rows;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 58
         * cusId : 55
         * memberName : 黄尚坤
         * idType : 2
         * idNumber : 420501199208151256
         * gender : 1
         * birthday : 2004-09-19
         * mobile : 15685236555
         * email : null
         * relation : 1
         * isMarried : null
         * isDefault : 0
         * province : 北京市
         * city : 北京市
         * district : 东城区
         * street : 洪山区
         * postcode : null
         * gmtCreate : 2018-09-19 10:51:34
         * gmtModified : 2018-09-25 18:43:06
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
        private String isMarried;
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
}