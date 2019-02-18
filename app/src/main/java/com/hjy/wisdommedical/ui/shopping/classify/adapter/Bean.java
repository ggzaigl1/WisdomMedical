package com.hjy.wisdommedical.ui.shopping.classify.adapter;

/**
 * Created by Stefan on 2018/10/25.
 */
public class Bean {


    public String getRightData() {
        return rightData;
    }

    public void setRightData(String rightData) {
        this.rightData = rightData;
    }

    private String rightData;
    private int position;

    public Bean(String rightData) {
        this.rightData = rightData;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
