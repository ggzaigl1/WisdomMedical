package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * 配送地址列表
 * Created by
 */
public class AddressToListBean implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":23,"cusId":2,"consigneeName":null,"mobile":"123","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 15:05:06","gmtModified":null},{"id":22,"cusId":2,"consigneeName":null,"mobile":"123","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 15:02:08","gmtModified":null},{"id":21,"cusId":2,"consigneeName":null,"mobile":"123","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 15:01:24","gmtModified":null},{"id":19,"cusId":2,"consigneeName":null,"mobile":"123","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 14:59:33","gmtModified":null},{"id":18,"cusId":2,"consigneeName":null,"mobile":"123","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 14:59:17","gmtModified":null},{"id":17,"cusId":2,"consigneeName":"123456","mobile":"15271512622","country":null,"province":"123456","city":"123456","district":"123456","street":"123456","postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 13:45:09","gmtModified":null},{"id":16,"cusId":2,"consigneeName":"123456","mobile":"15271512622","country":null,"province":"123456","city":"123456","district":"123456","street":"123456","postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 13:45:03","gmtModified":null},{"id":15,"cusId":2,"consigneeName":"123456","mobile":"15271512622","country":null,"province":"123456","city":"123456","district":"123456","street":"123456","postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 12:01:49","gmtModified":null},{"id":12,"cusId":2,"consigneeName":null,"mobile":"18671166153","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 10:23:04","gmtModified":null},{"id":11,"cusId":2,"consigneeName":null,"mobile":"18671166153","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 10:19:09","gmtModified":null},{"id":10,"cusId":2,"consigneeName":null,"mobile":"18671166153","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 10:06:12","gmtModified":null},{"id":9,"cusId":2,"consigneeName":null,"mobile":"18671166153","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 10:05:39","gmtModified":null},{"id":8,"cusId":2,"consigneeName":null,"mobile":"18671166153","country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 10:05:23","gmtModified":null},{"id":7,"cusId":2,"consigneeName":null,"mobile":null,"country":null,"province":null,"city":null,"district":null,"street":null,"postcode":null,"isDefault":null,"gmtCreate":"2018-07-17 10:02:07","gmtModified":null},{"id":1,"cusId":2,"consigneeName":"王凯","mobile":"13100000000","country":"中国","province":"湖北省","city":"武汉市","district":"硚口区","street":"紫润明园北区22栋2单元2208号","postcode":420300,"isDefault":0,"gmtCreate":"2018-05-03 15:09:20","gmtModified":null}]
     * total : 15
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
         * id : 23
         * cusId : 2
         * consigneeName : null
         * mobile : 123
         * country : null
         * province : null
         * city : null
         * district : null
         * street : null
         * postcode : null
         * isDefault : null
         * gmtCreate : 2018-07-17 15:05:06
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
}