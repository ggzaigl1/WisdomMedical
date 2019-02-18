package com.example.handsomelibrary.model;


import java.io.Serializable;

/**
 * Created by Stefan on 2018/7/30.
 * 去评价（获取评价标签列表）
 */
public class ListNoPageBean implements Serializable {

    /**
     * gmtCreate : 2018-07-05 11:57:21
     * id : 1
     * tagName : 医术高明
     */

    private String gmtCreate;
    private int id;
    private String tagName;
    private boolean isSelector;

    public boolean isIselector() {
        return isSelector;
    }

    public void setIselector(boolean isSelector) {
        this.isSelector = isSelector;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}