package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪
 * data :2018/12/19 0019 11:20
 * 公共数据基类
 */
public class BaseListBean implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":19,"pdCode":"pd001","pdName":"小太阳","pdCf":17,"pdSubCf":18,"pdCom":"123123","pdBrand":2,"pdSign":"器材","pdBigPic":"/group/20181120/1d34f6202b224d1cb62fb415f16ce55b.jpeg","pdSmallPic":"/group/20181120/2e8f8e3c80d64e8b97cfc3c55ab62c90.jpeg,/group/20181120/bc06aacf72d24e8f861002cbfec36ed1.jpeg","isMail":"0","isExchange":"0","priceNow":100,"priceOriginal":111,"priceCost":90,"stockNum":100,},{"id":24,"pdCode":"pd005","pdName":"龟鹿补肾丸","pdCf":20,"pdSubCf":26,"pdCom":"龟鹿补肾丸12袋补肾壮阳性欲减退失眠精神疲乏\n","pdBrand":7,"gmtModify":null}]
     * total : 32
     * totalPages : 2
     * first : 1
     */

    private int offset;
    private int limit;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private int first;
    private List<BaseListBean.RowsBean> rows;

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

    public List<BaseListBean.RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<BaseListBean.RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 19
         * pdCode : pd001
         * pdName : 小太阳
         * pdCf : 17
         * pdSubCf : 18
         * pdCom : 123123
         * pdBrand : 2
         * pdSign : 器材
         * pdBigPic : /group/20181120/1d34f6202b224d1cb62fb415f16ce55b.jpeg
         * pdSmallPic : /group/20181120/2e8f8e3c80d64e8b97cfc3c55ab62c90.jpeg,/group/20181120/bc06aacf72d24e8f861002cbfec36ed1.jpeg
         * isMail : 0
         * isExchange : 0
         * priceNow : 100
         * priceOriginal : 111
         * priceCost : 90
         * stockNum : 100
         * salesNum : 0
         * isActive : 1
         * gmtCreate : 2018-11-20 10:05:52
         * gmtModify : 2018-11-20 10:05:52
         */


        private int id;
        private int productId;
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
        private int salesNum;
        private int isActive;
        private String gmtCreate;
        private String gmtModify;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
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

        public int getPriceCost() {
            return priceCost;
        }

        public void setPriceCost(int priceCost) {
            this.priceCost = priceCost;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public int getSalesNum() {
            return salesNum;
        }

        public void setSalesNum(int salesNum) {
            this.salesNum = salesNum;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
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
