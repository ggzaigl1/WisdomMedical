package com.example.handsomelibrary.model;

/**
 * Created by Stefan on 2018/7/30.
 */
public class DeptListBean {

    /**
     * id : 8
     * departmentNo : 008
     * departmentName : 眼科
     * departmentInfo : null
     * gmtCreate : null
     * gmtModified : null
     */

    private int id;
    private String departmentNo;
    private String departmentName;
    private Object departmentInfo;
    private Object gmtCreate;
    private Object gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Object getDepartmentInfo() {
        return departmentInfo;
    }

    public void setDepartmentInfo(Object departmentInfo) {
        this.departmentInfo = departmentInfo;
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
