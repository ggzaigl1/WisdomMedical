package com.hjy.wisdommedical.util.sticky;

import com.example.handsomelibrary.model.StickyBean;
import com.hjy.wisdommedical.util.Constant;

/**
 *
 * Created by fangs on 2018/9/20 16:43.
 */
public class MentalHealthStickyBean extends StickyBean {
    //标题 id  type
    private int id;
    private int type;

    //答案 id 分数
    private int smalId;
    private int score;
    public boolean isSelect = false;//子标题是否选择

    private String name = "";


    public MentalHealthStickyBean() {}

    //标题 构造方法
    public MentalHealthStickyBean(int id, int type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;

        setItemType(Constant.StickyType);
    }

    //答案 构造方法
    public MentalHealthStickyBean(int id, int smalId, String name, int score, boolean isSelect) {
        this.id = id;
        this.smalId = smalId;
        this.score = score;
        this.name = name;
        this.isSelect = isSelect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSmalId() {
        return smalId;
    }

    public void setSmalId(int smalId) {
        this.smalId = smalId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
