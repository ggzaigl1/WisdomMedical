package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪
 * data :2018/12/3 0003 16:33
 * 商城 药品订单
 */
public class DrugOrderBean implements Serializable {

    /**
     * first : 1
     * limit : 2147483647
     * offset : 0
     * pageNo : 1
     * pageSize : 20
     * rows : [{"goodsPay":840,"id":81,"listOrderDetails":[{"goodsCount":3,"goodsId":27,"goodsPrice":120,"id":93,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"15439166482G5YZ1","orderPay":840,"orderStatus":1},{"goodsPay":20,"id":80,"listOrderDetails":[{"goodsCount":1,"goodsId":51,"goodsPrice":20,"id":92,"pdName":"温胃舒颗粒","pdSmallPic":"/group/20181122/d33c95f6f8544179bcaa5ac3b5623858.jpeg"}],"logisticsPay":0,"orderNo":"15439148778TU9ZD","orderPay":20,"orderStatus":1},{"goodsPay":120,"id":79,"listOrderDetails":[{"goodsCount":1,"goodsId":27,"goodsPrice":120,"id":91,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"15439148510L7W95","orderPay":120,"orderStatus":1},{"goodsPay":240,"id":78,"listOrderDetails":[{"goodsCount":1,"goodsId":43,"goodsPrice":120,"id":89,"pdName":"止咳糖浆","pdSmallPic":"/group/20181122/6bb84685f1014336a62ebd3f10238b97.png"},{"goodsCount":1,"goodsId":28,"goodsPrice":120,"id":90,"pdName":"穿心莲片","pdSmallPic":"/group/20181121/976be36a5daa43bc88a734143be23f0c.png,/group/20181121/f9950be68cdb44b3ad7bd45df943868c.png"}],"logisticsPay":0,"orderNo":"1543911677ERQ01H","orderPay":240,"orderStatus":1},{"goodsPay":120,"id":77,"listOrderDetails":[{"goodsCount":1,"goodsId":27,"goodsPrice":120,"id":88,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"1543911664L0DHC0","orderPay":120,"orderStatus":1},{"goodsPay":120,"id":76,"listOrderDetails":[{"goodsCount":1,"goodsId":27,"goodsPrice":120,"id":87,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"15438874133HGQ12","orderPay":120,"orderStatus":1},{"goodsPay":1200,"id":75,"listOrderDetails":[{"goodsCount":10,"goodsId":27,"goodsPrice":120,"id":86,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"1543886814AY0LVK","orderPay":1200,"orderStatus":1},{"goodsPay":1200,"id":74,"listOrderDetails":[{"goodsCount":10,"goodsId":27,"goodsPrice":120,"id":85,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"1543886813BZEZTK","orderPay":1200,"orderStatus":1},{"goodsPay":1200,"id":73,"listOrderDetails":[{"goodsCount":10,"goodsId":27,"goodsPrice":120,"id":84,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"1543886812QAD6AZ","orderPay":1200,"orderStatus":1},{"goodsPay":1200,"id":72,"listOrderDetails":[{"goodsCount":10,"goodsId":27,"goodsPrice":120,"id":83,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"15438868113KETVU","orderPay":1200,"orderStatus":1},{"goodsPay":1200,"id":71,"listOrderDetails":[{"goodsCount":10,"goodsId":27,"goodsPrice":120,"id":82,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"1543830543R6Q6JX","orderPay":1200,"orderStatus":1},{"goodsPay":120,"id":69,"listOrderDetails":[{"goodsCount":1,"goodsId":27,"goodsPrice":120,"id":81,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"15438249178AG74E","orderPay":120,"orderStatus":1},{"goodsPay":120,"id":68,"listOrderDetails":[{"goodsCount":1,"goodsId":23,"goodsPrice":120,"id":80,"pdName":"蛋白质粉","pdSmallPic":"/group/20181121/81905ed9a5de49d2be2c4ad54b255088.png"}],"logisticsPay":0,"orderNo":"1543824507M6T041","orderPay":120,"orderStatus":1},{"goodsPay":120,"id":67,"listOrderDetails":[{"goodsCount":1,"goodsId":27,"goodsPrice":120,"id":79,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}],"logisticsPay":0,"orderNo":"1543821926UA42AX","orderPay":120,"orderStatus":1}]
     * total : 15
     * totalPages : 1
     */

    private int first;
    private int limit;
    private int offset;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private List<RowsBean> rows;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * goodsPay : 840
         * id : 81
         * listOrderDetails : [{"goodsCount":3,"goodsId":27,"goodsPrice":120,"id":93,"pdName":"牛黄上清丸","pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg"}]
         * logisticsPay : 0
         * orderNo : 15439166482G5YZ1
         * orderPay : 840
         * orderStatus : 1
         */

        private int goodsPay;
        private int id;
        private int logisticsPay;
        private String orderNo;
        private int orderPay;
        private int orderStatus;
        private List<ListOrderDetailsBean> listOrderDetails;

        public int getGoodsPay() {
            return goodsPay;
        }

        public void setGoodsPay(int goodsPay) {
            this.goodsPay = goodsPay;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLogisticsPay() {
            return logisticsPay;
        }

        public void setLogisticsPay(int logisticsPay) {
            this.logisticsPay = logisticsPay;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrderPay() {
            return orderPay;
        }

        public void setOrderPay(int orderPay) {
            this.orderPay = orderPay;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public List<ListOrderDetailsBean> getListOrderDetails() {
            return listOrderDetails;
        }

        public void setListOrderDetails(List<ListOrderDetailsBean> listOrderDetails) {
            this.listOrderDetails = listOrderDetails;
        }

        public static class ListOrderDetailsBean implements Serializable {
            /**
             * goodsCount : 3
             * goodsId : 27
             * goodsPrice : 120
             * id : 93
             * pdName : 牛黄上清丸
             * pdSmallPic : /group/20181121/e1b06610c047443186a53c4e410b3235.jpeg
             */

            private int goodsCount;
            private int goodsId;
            private int goodsPrice;
            private int id;
            private String pdName;
            private String pdSmallPic;

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(int goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPdName() {
                return pdName;
            }

            public void setPdName(String pdName) {
                this.pdName = pdName;
            }

            public String getPdSmallPic() {
                return pdSmallPic;
            }

            public void setPdSmallPic(String pdSmallPic) {
                this.pdSmallPic = pdSmallPic;
            }
        }
    }
}
