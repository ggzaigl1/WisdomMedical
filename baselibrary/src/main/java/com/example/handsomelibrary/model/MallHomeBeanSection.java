package com.example.handsomelibrary.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by QKun on 2018/11/22.
 */
public class MallHomeBeanSection extends SectionEntity<MallHomeBean.ProductBean> {

    public MallHomeBeanSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MallHomeBeanSection(MallHomeBean.ProductBean productBean) {
        super(productBean);
    }
}
