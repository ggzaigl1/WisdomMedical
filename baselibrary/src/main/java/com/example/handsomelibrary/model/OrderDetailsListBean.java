package com.example.handsomelibrary.model;

import java.util.List;

/**
 * Created by 初夏小溪
 * data :2018/12/3 0003 17:32
 */
public class OrderDetailsListBean {

    /**
     * orderDetails : [{"id":81,"orderNo":"15438249178AG74E","goodsId":27,"goodsPrice":120,"goodsCount":1,"gmtCreate":"2018-12-03 16:15:19","gmtModified":null,"pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg","pdName":null}]
     * order : {"id":69,"orderNo":"15438249178AG74E","orderStatus":1,"orderPay":120,"goodsPay":120,"logisticsPay":0,"receiptName":"傻大个","receiptPhone":"15369988745","receiptAddress":"陕西宝鸡市陈仓区磻溪镇通过哈哈v","payStyle":null,"payNo":null,"cusId":55,"gmtCreate":"2018-12-03 16:15:19","gmtModified":null,"cartId":null,"goodsId":null,"goodsCount":null,"orderType":null,"goodsPrice":null,"listOrderDetails":null}
     */

    private OrderBean order;
    private List<OrderDetailsBean> orderDetails;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<OrderDetailsBean> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsBean> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static class OrderBean {
        /**
         * id : 69
         * orderNo : 15438249178AG74E
         * orderStatus : 1
         * orderPay : 120.0
         * goodsPay : 120.0
         * logisticsPay : 0.0
         * receiptName : 傻大个
         * receiptPhone : 15369988745
         * receiptAddress : 陕西宝鸡市陈仓区磻溪镇通过哈哈v
         * payStyle : null
         * payNo : null
         * cusId : 55
         * gmtCreate : 2018-12-03 16:15:19
         * gmtModified : null
         * cartId : null
         * goodsId : null
         * goodsCount : null
         * orderType : null
         * goodsPrice : null
         * listOrderDetails : null
         */

        private int id;
        private String orderNo;
        private int orderStatus;
        private double orderPay;
        private double goodsPay;
        private double logisticsPay;
        private String receiptName;
        private String receiptPhone;
        private String receiptAddress;
        private Object payStyle;
        private Object payNo;
        private int cusId;
        private String gmtCreate;
        private Object gmtModified;
        private Object cartId;
        private Object goodsId;
        private Object goodsCount;
        private Object orderType;
        private Object goodsPrice;
        private Object listOrderDetails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public double getOrderPay() {
            return orderPay;
        }

        public void setOrderPay(double orderPay) {
            this.orderPay = orderPay;
        }

        public double getGoodsPay() {
            return goodsPay;
        }

        public void setGoodsPay(double goodsPay) {
            this.goodsPay = goodsPay;
        }

        public double getLogisticsPay() {
            return logisticsPay;
        }

        public void setLogisticsPay(double logisticsPay) {
            this.logisticsPay = logisticsPay;
        }

        public String getReceiptName() {
            return receiptName;
        }

        public void setReceiptName(String receiptName) {
            this.receiptName = receiptName;
        }

        public String getReceiptPhone() {
            return receiptPhone;
        }

        public void setReceiptPhone(String receiptPhone) {
            this.receiptPhone = receiptPhone;
        }

        public String getReceiptAddress() {
            return receiptAddress;
        }

        public void setReceiptAddress(String receiptAddress) {
            this.receiptAddress = receiptAddress;
        }

        public Object getPayStyle() {
            return payStyle;
        }

        public void setPayStyle(Object payStyle) {
            this.payStyle = payStyle;
        }

        public Object getPayNo() {
            return payNo;
        }

        public void setPayNo(Object payNo) {
            this.payNo = payNo;
        }

        public int getCusId() {
            return cusId;
        }

        public void setCusId(int cusId) {
            this.cusId = cusId;
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

        public Object getCartId() {
            return cartId;
        }

        public void setCartId(Object cartId) {
            this.cartId = cartId;
        }

        public Object getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Object goodsId) {
            this.goodsId = goodsId;
        }

        public Object getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(Object goodsCount) {
            this.goodsCount = goodsCount;
        }

        public Object getOrderType() {
            return orderType;
        }

        public void setOrderType(Object orderType) {
            this.orderType = orderType;
        }

        public Object getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(Object goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public Object getListOrderDetails() {
            return listOrderDetails;
        }

        public void setListOrderDetails(Object listOrderDetails) {
            this.listOrderDetails = listOrderDetails;
        }
    }

    public static class OrderDetailsBean {
        /**
         * id : 81
         * orderNo : 15438249178AG74E
         * goodsId : 27
         * goodsPrice : 120.0
         * goodsCount : 1
         * gmtCreate : 2018-12-03 16:15:19
         * gmtModified : null
         * pdSmallPic : /group/20181121/e1b06610c047443186a53c4e410b3235.jpeg
         * pdName : null
         */

        private int id;
        private String orderNo;
        private int goodsId;
        private double goodsPrice;
        private int goodsCount;
        private String gmtCreate;
        private Object gmtModified;
        private String pdSmallPic;
        private String pdName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
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

        public String getPdSmallPic() {
            return pdSmallPic;
        }

        public void setPdSmallPic(String pdSmallPic) {
            this.pdSmallPic = pdSmallPic;
        }

        public String getPdName() {
            return pdName;
        }

        public void setPdName(String pdName) {
            this.pdName = pdName;
        }
    }
}
