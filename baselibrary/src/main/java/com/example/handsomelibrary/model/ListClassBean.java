package com.example.handsomelibrary.model;

import java.util.List;

/**
 * Created by Stefan on 2018/12/21 11:11
 */
public class ListClassBean {

    private List<ListPtClassBean> listPtClass;

    public List<ListPtClassBean> getListPtClass() {
        return listPtClass;
    }

    public void setListPtClass(List<ListPtClassBean> listPtClass) {
        this.listPtClass = listPtClass;
    }

    public static class ListPtClassBean {
        /**
         * id : 46
         * cfName : 生活护理
         * parentCf : null
         * parentCfName : null
         * cfSort : 8
         * cfTag : 药品
         * cfPic :
         * gmtCreate : null
         * gmtModify : null
         */

        private int id;
        private String cfName;
        private Object parentCf;
        private Object parentCfName;
        private String cfSort;
        private String cfTag;
        private String cfPic;
        private Object gmtCreate;
        private Object gmtModify;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCfName() {
            return cfName;
        }

        public void setCfName(String cfName) {
            this.cfName = cfName;
        }

        public Object getParentCf() {
            return parentCf;
        }

        public void setParentCf(Object parentCf) {
            this.parentCf = parentCf;
        }

        public Object getParentCfName() {
            return parentCfName;
        }

        public void setParentCfName(Object parentCfName) {
            this.parentCfName = parentCfName;
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

        public String getCfPic() {
            return cfPic;
        }

        public void setCfPic(String cfPic) {
            this.cfPic = cfPic;
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
