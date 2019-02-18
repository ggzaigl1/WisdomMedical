package com.example.handsomelibrary.model;


import java.io.Serializable;

/**
 * Created by Stefan on 2018/7/30.
 * 健康档案 健康史
 */
public class HealthHistoryBean implements Serializable {

    /**
     * id : 2
     * familyHistory : 心脏病
     * ownHistory : 心脏病
     * visitMemberId : 0
     * gmtCreate : null
     * gmtModified : null
     */

    private int id;
    private String familyHistory;
    private String ownHistory;
    private int visitMemberId;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getOwnHistory() {
        return ownHistory;
    }

    public void setOwnHistory(String ownHistory) {
        this.ownHistory = ownHistory;
    }

    public int getVisitMemberId() {
        return visitMemberId;
    }

    public void setVisitMemberId(int visitMemberId) {
        this.visitMemberId = visitMemberId;
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