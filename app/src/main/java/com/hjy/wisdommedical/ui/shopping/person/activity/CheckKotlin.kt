package com.hjy.wisdommedical.ui.shopping.person.activity

import android.os.Bundle
import com.example.handsomelibrary.base.BaseActivity
import com.hjy.wisdommedical.R
import com.hjy.wisdommedical.ui.shopping.person.adapter.LogisticsBean
import kotlinx.android.synthetic.main.activity_check_logistics.*
import kotlinx.android.synthetic.main.base_activity_head.*

/**
 * Created by 初夏小溪
 * data :2019/1/4 0004 11:01
 */
class CheckKotlin : BaseActivity() {


    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "查看物流"
    }

    override fun getContentView(): Int {
        return R.layout.activity_check_logistics
    }


}