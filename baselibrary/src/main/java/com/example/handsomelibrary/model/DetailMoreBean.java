package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪
 * data :2018/12/18 0018 14:58
 * 商品详情
 */
public class DetailMoreBean implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":73,"orderId":106,"goodsId":51,"cusId":55,"evaContent":"锦觅红的时候回来啊我也不知道为什么要这样说话的时候回来啊啊啊啊啊啊啊啊啊啊啊啊啊不不不吃吃吃","evaStar":5,"gmtCreate":"2018-12-19 15:45:19","gmtModified":null,"nickname":"bbb","photoUrl":""},{"id":72,"orderId":99,"goodsId":51,"cusId":55,"evaContent":"好","evaStar":4,"gmtCreate":"2018-12-19 15:25:06","gmtModified":null,"nickname":"bbb","photoUrl":""}]
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

    public static class RowsBean {
        /**
         * id : 73
         * orderId : 106
         * goodsId : 51
         * cusId : 55
         * evaContent : 锦觅红的时候回来啊我也不知道为什么要这样说话的时候回来啊啊啊啊啊啊啊啊啊啊啊啊啊不不不吃吃吃
         * evaStar : 5
         * gmtCreate : 2018-12-19 15:45:19
         * gmtModified : null
         * nickname : bbb
         * photoUrl :
         */

        private int id;
        private int orderId;
        private int goodsId;
        private int cusId;
        private String evaContent;
        private int evaStar;
        private String gmtCreate;
        private Object gmtModified;
        private String nickname;
        private String photoUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getCusId() {
            return cusId;
        }

        public void setCusId(int cusId) {
            this.cusId = cusId;
        }

        public String getEvaContent() {
            return evaContent;
        }

        public void setEvaContent(String evaContent) {
            this.evaContent = evaContent;
        }

        public int getEvaStar() {
            return evaStar;
        }

        public void setEvaStar(int evaStar) {
            this.evaStar = evaStar;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }
    }
}
