package com.example.handsomelibrary.model;

/**
 * 预约时间 实体类
 * Created by fangs on 2018/7/3.
 */
public class MakeBean {

    /**
     * date : 2018-09-01
     * time : 1
     * status : 0
     */

    private String date;//日期
    private int time;   //时间段
    private int status; //0：空白 1：可选 2：已经选过的 3：用户勾选 对应 可选

    public MakeBean() {
    }

    public MakeBean(String date) {
        this.date = date;
    }

    public MakeBean(String date, int time, int status) {
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
