package com.hjy.wisdommedical.ui.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.personal.adapter.EleMedRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 电子病历
 * Created by Stefan on 2018/9/7 15:49.
 */
public class EleMedRecordActivity extends BaseActivity {
    @BindView(R.id.rv_mList)
    RecyclerView rv_mList;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.bt_delete)
    Button bt_delete;

    EleMedRecordAdapter adapter;
    private View inflate;

    @Override
    protected int getContentView() {
        return R.layout.activity_ele_med_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.add_record);
        tv_right.setText(R.string.added);
        rv_mList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EleMedRecordAdapter(new ArrayList<>());
        rv_mList.setAdapter(adapter);
        setDummy();
        inflate = getLayoutInflater().inflate(R.layout.activity_empty, null, false);
        if(adapter.getData().size()==0){
            adapter.setEmptyView(inflate);
        }
        adapter.setOnItemLongClickListener((mAdapter, view, position) -> {
//           TextBean bean= (TextBean) mAdapter.getData().get(position);
//          CheckBox ckBox= view.findViewById(R.id.ckBox);
//           if(ckBox.isChecked()){
//               bean.setFlag(true);
//           }else {
//               bean.setFlag(false);
//           }
            bt_delete.setVisibility(View.VISIBLE);
            adapter.mark=true;
            adapter.notifyDataSetChanged();
            return false;
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter mAdapter, View view, int position) {
              TextBean  bean = adapter.getData().get(position);
              if (R.id.ckBox==view.getId()){
                  boolean flag = bean.isFlag();
                  if (flag) {
                      bean.setFlag(false);
                  } else {
                      bean.setFlag(true);
                  }
                  adapter.notifyItemChanged(position);
              }
            }
        });


    }

    @OnClick({R.id.iv_back, R.id.tv_right,R.id.bt_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.tv_right:
                JumpUtils.jump(mContext, EditMedRecordActivity.class, null);
                break;
            case R.id.bt_delete:
                List<TextBean> data = adapter.getData();

                List<TextBean> list = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    if(data.get(i).isFlag()){
                        list.add(data.get(i));
                    }
                }
                for (TextBean textBean : list) {
                    if (data.contains(textBean)) {
                        data.remove(textBean);
                    }
                }
                T.showShort("删除成功！");

                adapter.setNewData(data);
                adapter.notifyDataSetChanged();
                if(adapter.getData().size()==0){
                    adapter.setEmptyView(inflate);
                    bt_delete.setVisibility(View.GONE);
                }
                break;
        }
    }

    List<TextBean> listBean;

    private void setDummy() {
        listBean = new ArrayList();
        listBean.add(new TextBean("王五", "医嘱单", "2018-07-28", "感冒发烧", false));
        listBean.add(new TextBean("王五", "医嘱单", "2018-07-28", "感冒发烧", false));
        listBean.add(new TextBean("王五", "医嘱单", "2018-07-28", "感冒发烧", false));

        adapter.setNewData(listBean);
    }
}
