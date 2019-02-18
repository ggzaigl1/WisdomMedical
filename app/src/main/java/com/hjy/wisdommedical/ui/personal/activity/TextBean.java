package com.hjy.wisdommedical.ui.personal.activity;

/**
 * Created by Stefan on 2018/9/8.
 */
public class TextBean {

    public TextBean(String name, String type, String date, String info, boolean flag) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.info = info;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String name;
    private String type;
    private String date;
    private String info;
    private boolean flag;
}
