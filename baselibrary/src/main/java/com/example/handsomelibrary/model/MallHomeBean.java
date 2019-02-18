package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/11/22.
 */
public class MallHomeBean implements Serializable {

    /**
     * classfication : {"cfName":"生活护理","cfPic":"","cfSort":"8","cfTag":"药品","id":46}
     * product : [{"id":33,"isActive":1,"isExchange":"1","isMail":"1","pdBrand":10,"pdCf":46,"pdCode":"pd015","pdCom":"除疤膏25g去疤痕 祛疤膏 烧伤活血消疤 疤痕膏 除疤痕 ","pdName":"除疤膏","pdSign":"药品","pdSubCf":55,"priceCost":100,"priceNow":120,"priceOriginal":140,"stockNum":200},{"id":34,"isActive":1,"isExchange":"1","isMail":"1","pdBigPic":"/group/20181121/bb4ce544a0924b19b35e9d33428b22c7.png","pdBrand":6,"pdCf":46,"pdCode":"pd016","pdCom":"感冒清热颗粒10袋风寒感冒药咳嗽清热颗粒感冒冲剂药 ","pdName":"感冒清热颗粒","pdSign":"药品","pdSmallPic":"/group/20181121/2b0e19dc8a4644aa9acb8ddcddc8a123.png,/group/20181121/3db7f4ccc50c412f8f268e6f167f4867.png,/group/20181121/afe257cd67fe4c46adc258a99f886e9e.png,/group/20181121/868622fd0c2942a09b36d02696719fdc.png","pdSubCf":56,"priceCost":100,"priceNow":120,"priceOriginal":140,"stockNum":200}]
     */

    private ClassficationBean classfication;
    private List<ProductBean> product;

    public ClassficationBean getClassfication() {
        return classfication;
    }

    public void setClassfication(ClassficationBean classfication) {
        this.classfication = classfication;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ClassficationBean implements Serializable {
        /**
         * cfName : 生活护理
         * cfPic :
         * cfSort : 8
         * cfTag : 药品
         * id : 46
         */

        private String cfName;
        private String cfPic;
        private String cfSort;
        private String cfTag;
        private int id;

        public String getCfName() {
            return cfName;
        }

        public void setCfName(String cfName) {
            this.cfName = cfName;
        }

        public String getCfPic() {
            return cfPic;
        }

        public void setCfPic(String cfPic) {
            this.cfPic = cfPic;
        }

        public String getCfSort() {
            return cfSort;
        }

        public void setCfSort(String cfSort) {
            this.cfSort = cfSort;
        }

        public String getCfTag() {
            return cfTag;
        }

        public void setCfTag(String cfTag) {
            this.cfTag = cfTag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class ProductBean implements Serializable {
        /**
         * id : 33
         * isActive : 1
         * isExchange : 1
         * isMail : 1
         * pdBrand : 10
         * pdCf : 46
         * pdCode : pd015
         * pdCom : 除疤膏25g去疤痕 祛疤膏 烧伤活血消疤 疤痕膏 除疤痕
         * pdName : 除疤膏
         * pdSign : 药品
         * pdSubCf : 55
         * priceCost : 100
         * priceNow : 120
         * priceOriginal : 140
         * stockNum : 200
         * pdBigPic : /group/20181121/bb4ce544a0924b19b35e9d33428b22c7.png
         * pdSmallPic : /group/20181121/2b0e19dc8a4644aa9acb8ddcddc8a123.png,/group/20181121/3db7f4ccc50c412f8f268e6f167f4867.png,/group/20181121/afe257cd67fe4c46adc258a99f886e9e.png,/group/20181121/868622fd0c2942a09b36d02696719fdc.png
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
        private int priceNow;
        private int priceOriginal;
        private int priceCost;
        private int stockNum;
        private int isActive;
        private int salesNum;
        private String gmtCreate;
        private String gmtModify;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public String getIsExchange() {
            return isExchange;
        }

        public void setIsExchange(String isExchange) {
            this.isExchange = isExchange;
        }

        public String getIsMail() {
            return isMail;
        }

        public void setIsMail(String isMail) {
            this.isMail = isMail;
        }

        public int getPdBrand() {
            return pdBrand;
        }

        public void setPdBrand(int pdBrand) {
            this.pdBrand = pdBrand;
        }

        public int getPdCf() {
            return pdCf;
        }

        public void setPdCf(int pdCf) {
            this.pdCf = pdCf;
        }

        public String getPdCode() {
            return pdCode;
        }

        public void setPdCode(String pdCode) {
            this.pdCode = pdCode;
        }

        public String getPdCom() {
            return pdCom;
        }

        public void setPdCom(String pdCom) {
            this.pdCom = pdCom;
        }

        public String getPdName() {
            return pdName;
        }

        public void setPdName(String pdName) {
            this.pdName = pdName;
        }

        public String getPdSign() {
            return pdSign;
        }

        public void setPdSign(String pdSign) {
            this.pdSign = pdSign;
        }

        public int getPdSubCf() {
            return pdSubCf;
        }

        public void setPdSubCf(int pdSubCf) {
            this.pdSubCf = pdSubCf;
        }

        public int getPriceCost() {
            return priceCost;
        }

        public void setPriceCost(int priceCost) {
            this.priceCost = priceCost;
        }

        public int getPriceNow() {
            return priceNow;
        }

        public void setPriceNow(int priceNow) {
            this.priceNow = priceNow;
        }

        public int getPriceOriginal() {
            return priceOriginal;
        }

        public void setPriceOriginal(int priceOriginal) {
            this.priceOriginal = priceOriginal;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
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


        public int getSalesNum() {
            return salesNum;
        }

        public void setSalesNum(int salesNum) {
            this.salesNum = salesNum;
        }


        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModify() {
            return gmtModify;
        }

        public void setGmtModify(String gmtModify) {
            this.gmtModify = gmtModify;
        }
    }
}
