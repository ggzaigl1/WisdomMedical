package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪
 * data :2018/12/18 0018 14:58
 * 商品详情
 */
public class ProductInfoBean implements Serializable {

    /**
     * listEvaluation : [{"id":73,"orderId":106,"goodsId":51,"cusId":55,"evaContent":"锦觅红的时候回来啊我也不知道为什么要这样说话的时候回来啊啊啊啊啊啊啊啊啊啊啊啊啊不不不吃吃吃","evaStar":5,"gmtCreate":"2018-12-19 15:45:19","gmtModified":null,"nickname":"bbb","photoUrl":""},{"id":72,"orderId":99,"goodsId":51,"cusId":55,"evaContent":"好","evaStar":4,"gmtCreate":"2018-12-19 15:25:06","gmtModified":null,"nickname":"bbb","photoUrl":""}]
     * product : {"id":51,"pdCode":"pd031","pdName":"温胃舒颗粒","pdCf":46,"pdSubCf":57,"pdCom":"温胃舒颗粒 10g*6袋\n","pdBrand":6,"pdSign":"药品","pdBigPic":"/group/20181122/e560fc0334354bd69b61e481fc82abfc.png,/group/20181122/ec51c2a3444b4c74a748598fe4f9664a.png,/group/20181122/9fb9289bd8d1409198f7ab697b578242.png","pdSmallPic":"/group/20181122/d33c95f6f8544179bcaa5ac3b5623858.jpeg","isMail":"1","isExchange":"1","priceNow":20,"priceOriginal":140,"priceCost":100,"stockNum":200,"salesNum":null,"isActive":1,"gmtCreate":null,"gmtModify":null}
     * isCollect : 0
     */

    private ProductBean product;
    private int isCollect;
    private List<ListEvaluationBean> listEvaluation;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public List<ListEvaluationBean> getListEvaluation() {
        return listEvaluation;
    }

    public void setListEvaluation(List<ListEvaluationBean> listEvaluation) {
        this.listEvaluation = listEvaluation;
    }

    public static class ProductBean implements Serializable {
        /**
         * id : 51
         * pdCode : pd031
         * pdName : 温胃舒颗粒
         * pdCf : 46
         * pdSubCf : 57
         * pdCom : 温胃舒颗粒 10g*6袋
         * <p>
         * pdBrand : 6
         * pdSign : 药品
         * pdBigPic : /group/20181122/e560fc0334354bd69b61e481fc82abfc.png,/group/20181122/ec51c2a3444b4c74a748598fe4f9664a.png,/group/20181122/9fb9289bd8d1409198f7ab697b578242.png
         * pdSmallPic : /group/20181122/d33c95f6f8544179bcaa5ac3b5623858.jpeg
         * isMail : 1
         * isExchange : 1
         * priceNow : 20.0
         * priceOriginal : 140.0
         * priceCost : 100.0
         * stockNum : 200
         * salesNum : null
         * isActive : 1
         * gmtCreate : null
         * gmtModify : null
         */

        private int id;
        private String pdCode;
        private String pdName;
        private int pdCf;
        private int pdSubCf;
        private String pdCom;
        private int pdBrand;
        private String pdSign;
        private String pdBigPic;
        private String pdSmallPic;
        private String isMail;
        private String isExchange;
        private double priceNow;
        private double priceOriginal;
        private double priceCost;
        private int stockNum;
        private Object salesNum;
        private int isActive;
        private Object gmtCreate;
        private Object gmtModify;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPdCode() {
            return pdCode;
        }

        public void setPdCode(String pdCode) {
            this.pdCode = pdCode;
        }

        public String getPdName() {
            return pdName;
        }

        public void setPdName(String pdName) {
            this.pdName = pdName;
        }

        public int getPdCf() {
            return pdCf;
        }

        public void setPdCf(int pdCf) {
            this.pdCf = pdCf;
        }

        public int getPdSubCf() {
            return pdSubCf;
        }

        public void setPdSubCf(int pdSubCf) {
            this.pdSubCf = pdSubCf;
        }

        public String getPdCom() {
            return pdCom;
        }

        public void setPdCom(String pdCom) {
            this.pdCom = pdCom;
        }

        public int getPdBrand() {
            return pdBrand;
        }

        public void setPdBrand(int pdBrand) {
            this.pdBrand = pdBrand;
        }

        public String getPdSign() {
            return pdSign;
        }

        public void setPdSign(String pdSign) {
            this.pdSign = pdSign;
        }

        public String getPdBigPic() {
            return pdBigPic;
        }

        public void setPdBigPic(String pdBigPic) {
            this.pdBigPic = pdBigPic;
        }

        public String getPdSmallPic() {
            return pdSmallPic;
        }

        public void setPdSmallPic(String pdSmallPic) {
            this.pdSmallPic = pdSmallPic;
        }

        public String getIsMail() {
            return isMail;
        }

        public void setIsMail(String isMail) {
            this.isMail = isMail;
        }

        public String getIsExchange() {
            return isExchange;
        }

        public void setIsExchange(String isExchange) {
            this.isExchange = isExchange;
        }

        public double getPriceNow() {
            return priceNow;
        }

        public void setPriceNow(double priceNow) {
            this.priceNow = priceNow;
        }

        public double getPriceOriginal() {
            return priceOriginal;
        }

        public void setPriceOriginal(double priceOriginal) {
            this.priceOriginal = priceOriginal;
        }

        public double getPriceCost() {
            return priceCost;
        }

        public void setPriceCost(double priceCost) {
            this.priceCost = priceCost;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public Object getSalesNum() {
            return salesNum;
        }

        public void setSalesNum(Object salesNum) {
            this.salesNum = salesNum;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public Object getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Object gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModify() {
            return gmtModify;
        }

        public void setGmtModify(Object gmtModify) {
            this.gmtModify = gmtModify;
        }
    }

    public static class ListEvaluationBean implements Serializable {
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
