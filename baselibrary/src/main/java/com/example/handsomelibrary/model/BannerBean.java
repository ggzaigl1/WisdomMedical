package com.example.handsomelibrary.model;

/**
 * Created by QKun on 2018/11/20.
 */
public class BannerBean {

    /**
     * id : 13
     * imgUrl : /banner/5e68a0b7837949bb9d05e6267fe25892.jpeg
     * bannerName : 商城4
     * stopTime : null
     * targetUrl : null
     * type : 3
     * isUsing : null
     * gmtCreate : null
     * gmtModified : null
     */

    private int id;
    private String imgUrl;
    private String bannerName;
    private String stopTime;
    private String targetUrl;
    private int type;
    private Object isUsing;
    private Object gmtCreate;
    private Object gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Object isUsing) {
        this.isUsing = isUsing;
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
}
