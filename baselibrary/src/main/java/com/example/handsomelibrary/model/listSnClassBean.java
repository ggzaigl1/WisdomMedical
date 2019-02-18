package com.example.handsomelibrary.model;

/**
 * Created by Stefan on 2018/12/21 11:39
 */
public class listSnClassBean {

    /**
     * id : 58
     * cfName : 呼吸系统常备药品
     * parentCf : 46
     * parentCfName : null
     * cfSort : 4
     * cfTag : 药品
     * cfPic :
     * gmtCreate : null
     * gmtModify : null
     */

    private int id;
    private String cfName;
    private int parentCf;
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

    public int getParentCf() {
        return parentCf;
    }

    public void setParentCf(int parentCf) {
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
