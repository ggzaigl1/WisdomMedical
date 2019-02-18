package com.example.handsomelibrary.model;

/**
 * Created by Stefan on 2018/7/31.
 */
public class SortBean {
    private String sort;
    private int id;

    public SortBean(String sort, int id) {
        this.sort = sort;
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SortBean{" +
                "sort='" + sort + '\'' +
                ", id=" + id +
                '}';
    }
}
