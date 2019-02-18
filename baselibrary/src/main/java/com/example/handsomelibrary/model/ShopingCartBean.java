package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * describe： 购物车 实体类
 * Created by fangs on 2018/11/23 11:58.
 */
public class ShopingCartBean implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":141,"cusId":55,"productId":43,"amount":1,"gmtCreate":null,"gmtModified":null,"product":{"id":43,"pdCode":"pd023","pdName":"止咳糖浆","pdCf":null,"pdSubCf":null,"pdCom":null,"pdBrand":null,"pdSign":null,"pdBigPic":null,"pdSmallPic":"/group/20181122/6bb84685f1014336a62ebd3f10238b97.png","isMail":null,"isExchange":null,"priceNow":120,"priceOriginal":140,"priceCost":null,"stockNum":null,"salesNum":null,"isActive":null,"gmtCreate":null,"gmtModify":null}},{"id":142,"cusId":55,"productId":29,"amount":1,"gmtCreate":null,"gmtModified":null,"product":{"id":29,"pdCode":"pd009","pdName":"维生素E","pdCf":null,"pdSubCf":null,"pdCom":null,"pdBrand":null,"pdSign":null,"pdBigPic":null,"pdSmallPic":"/group/20181121/69da7272885543dab06564359b238de2.png,/group/20181121/75c825821c44480a80c8cb46e76a9567.png","isMail":null,"isExchange":null,"priceNow":120,"priceOriginal":140,"priceCost":null,"stockNum":null,"salesNum":null,"isActive":null,"gmtCreate":null,"gmtModify":null}},{"id":91,"cusId":55,"productId":27,"amount":9,"gmtCreate":null,"gmtModified":null,"product":{"id":27,"pdCode":"pd007","pdName":"牛黄上清丸","pdCf":null,"pdSubCf":null,"pdCom":null,"pdBrand":null,"pdSign":null,"pdBigPic":null,"pdSmallPic":"/group/20181121/e1b06610c047443186a53c4e410b3235.jpeg","isMail":null,"isExchange":null,"priceNow":120,"priceOriginal":140,"priceCost":null,"stockNum":null,"salesNum":null,"isActive":null,"gmtCreate":null,"gmtModify":null}},{"id":113,"cusId":55,"productId":21,"amount":1,"gmtCreate":null,"gmtModified":null,"product":{"id":21,"pdCode":"pd003","pdName":"阿胶补血颗粒","pdCf":null,"pdSubCf":null,"pdCom":null,"pdBrand":null,"pdSign":null,"pdBigPic":null,"pdSmallPic":"/group/20181121/323b20831de946ea89faeb748e2a059c.png","isMail":null,"isExchange":null,"priceNow":120,"priceOriginal":140,"priceCost":null,"stockNum":null,"salesNum":null,"isActive":null,"gmtCreate":null,"gmtModify":null}}]
     * total : 4
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
         * id : 141
         * cusId : 55
         * productId : 43
         * amount : 1
         * gmtCreate : null
         * gmtModified : null
         * product : {"id":43,"pdCode":"pd023","pdName":"止咳糖浆","pdCf":null,"pdSubCf":null,"pdCom":null,"pdBrand":null,"pdSign":null,"pdBigPic":null,"pdSmallPic":"/group/20181122/6bb84685f1014336a62ebd3f10238b97.png","isMail":null,"isExchange":null,"priceNow":120,"priceOriginal":140,"priceCost":null,"stockNum":null,"salesNum":null,"isActive":null,"gmtCreate":null,"gmtModify":null}
         */

        private int id;
        private int cusId;
        private int productId;
        private int amount;
        private Object gmtCreate;
        private Object gmtModified;
        private ProductBean product;

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

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public Object getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Object gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Object gmtModified) {
            this.gmtModified = gmtModified;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean implements Serializable {
            /**
             * id : 43
             * pdCode : pd023
             * pdName : 止咳糖浆
             * pdCf : null
             * pdSubCf : null
             * pdCom : null
             * pdBrand : null
             * pdSign : null
             * pdBigPic : null
             * pdSmallPic : /group/20181122/6bb84685f1014336a62ebd3f10238b97.png
             * isMail : null
             * isExchange : null
             * priceNow : 120.0
             * priceOriginal : 140.0
             * priceCost : null
             * stockNum : null
             * salesNum : null
             * isActive : null
             * gmtCreate : null
             * gmtModify : null
             */

            private int id;
            private String pdCode;
            private String pdName;
            private Object pdCf;
            private Object pdSubCf;
            private String pdCom;
            private Object pdBrand;
            private Object pdSign;
            private Object pdBigPic;
            private String pdSmallPic;
            private Object isMail;
            private Object isExchange;
            private int priceNow;
            private double priceOriginal;
            private Object priceCost;
            private Object stockNum;
            private Object salesNum;
            private Object isActive;
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

            public Object getPdCf() {
                return pdCf;
            }

            public void setPdCf(Object pdCf) {
                this.pdCf = pdCf;
            }

            public Object getPdSubCf() {
                return pdSubCf;
            }

            public void setPdSubCf(Object pdSubCf) {
                this.pdSubCf = pdSubCf;
            }

            public String getPdCom() {
                return pdCom;
            }

            public void setPdCom(String pdCom) {
                this.pdCom = pdCom;
            }

            public Object getPdBrand() {
                return pdBrand;
            }

            public void setPdBrand(Object pdBrand) {
                this.pdBrand = pdBrand;
            }

            public Object getPdSign() {
                return pdSign;
            }

            public void setPdSign(Object pdSign) {
                this.pdSign = pdSign;
            }

            public Object getPdBigPic() {
                return pdBigPic;
            }

            public void setPdBigPic(Object pdBigPic) {
                this.pdBigPic = pdBigPic;
            }

            public String getPdSmallPic() {
                return pdSmallPic;
            }

            public void setPdSmallPic(String pdSmallPic) {
                this.pdSmallPic = pdSmallPic;
            }

            public Object getIsMail() {
                return isMail;
            }

            public void setIsMail(Object isMail) {
                this.isMail = isMail;
            }

            public Object getIsExchange() {
                return isExchange;
            }

            public void setIsExchange(Object isExchange) {
                this.isExchange = isExchange;
            }

            public int getPriceNow() {
                return priceNow;
            }

            public void setPriceNow(int priceNow) {
                this.priceNow = priceNow;
            }

            public double getPriceOriginal() {
                return priceOriginal;
            }

            public void setPriceOriginal(double priceOriginal) {
                this.priceOriginal = priceOriginal;
            }

            public Object getPriceCost() {
                return priceCost;
            }

            public void setPriceCost(Object priceCost) {
                this.priceCost = priceCost;
            }

            public Object getStockNum() {
                return stockNum;
            }

            public void setStockNum(Object stockNum) {
                this.stockNum = stockNum;
            }

            public Object getSalesNum() {
                return salesNum;
            }

            public void setSalesNum(Object salesNum) {
                this.salesNum = salesNum;
            }

            public Object getIsActive() {
                return isActive;
            }

            public void setIsActive(Object isActive) {
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
    }
}
