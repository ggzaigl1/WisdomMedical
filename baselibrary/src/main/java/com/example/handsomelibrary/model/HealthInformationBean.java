package com.example.handsomelibrary.model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Stefan on 2018/7/30.
 * 健康资讯
 */
public class HealthInformationBean implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":3,"readingQuantity":null,"newsUrl":"https://www.toutiao.com/a6556068980509901320/","imageUrl":null,"category":"生活习惯","title":"健康的生活习惯，成就健康体魄","body":null,"helpfulCount":null,"gmtCreate":null,"gmtModified":null},{"id":2,"readingQuantity":null,"newsUrl":"https://www.toutiao.com/a6580851732543177220/","imageUrl":null,"category":"健康美食","title":"肠道健康 身体更健康 更长寿","body":null,"helpfulCount":null,"gmtCreate":null,"gmtModified":null},{"id":1,"readingQuantity":null,"newsUrl":"https://www.toutiao.com/a6576203882622353927/","imageUrl":"","category":"健康美食","title":"十大健康的食物，维护健康，必不可少！","body":"","helpfulCount":null,"gmtCreate":null,"gmtModified":null}]
     * total : 3
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

    public static class RowsBean implements Serializable{
        /**
         * id : 3
         * readingQuantity : null
         * newsUrl : https://www.toutiao.com/a6556068980509901320/
         * imageUrl : null
         * category : 生活习惯
         * title : 健康的生活习惯，成就健康体魄
         * body : null
         * helpfulCount : null
         * gmtCreate : null
         * gmtModified : null
         */

        private int id;
        private String readingQuantity;
        private String newsUrl;
        private String imageUrl;
        private String category;
        private String title;
        private Object body;
        private Object helpfulCount;
        private String gmtCreate;
        private String gmtModified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReadingQuantity() {
            return readingQuantity;
        }

        public void setReadingQuantity(String readingQuantity) {
            this.readingQuantity = readingQuantity;
        }

        public String getNewsUrl() {
            return newsUrl;
        }

        public void setNewsUrl(String newsUrl) {
            this.newsUrl = newsUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getBody() {
            return body;
        }

        public void setBody(Object body) {
            this.body = body;
        }

        public Object getHelpfulCount() {
            return helpfulCount;
        }

        public void setHelpfulCount(Object helpfulCount) {
            this.helpfulCount = helpfulCount;
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