package com.hjy.wisdommedical.ui.shopping.person.adapter;

/**
 * Created by Stefan on 2018/11/7.
 */
public class LogisticsBean {
    private String time;
    private String place;

    public LogisticsBean(String time, String place) {
        this.time = time;
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
