package com.hjy.wisdommedical.ui.inquiry.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.DeptListBean;
import com.example.handsomelibrary.model.ListToAppBean;
import com.example.handsomelibrary.model.SortBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.view.divider.DividerParams;
import com.example.handsomelibrary.view.popupwindow.CommonPopupWindow;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.RvCommonAdapter;
import com.hjy.wisdommedical.adapter.ViewHolder;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.date.MakeAppointmentActivity;
import com.hjy.wisdommedical.ui.inquiry.activity.DoctorInfoActivity;
import com.hjy.wisdommedical.ui.inquiry.activity.ImageTextActivity;
import com.hjy.wisdommedical.ui.inquiry.adapter.InquiryAdapter;
import com.hjy.wisdommedical.util.KeyBoardUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 在线问诊 Fragment
 * Created by Stefan on 2018/6/29 10:15.
 */
public class InquiryFragment extends BaseFragment {
    @BindView(R.id.rv_doctor)
    RecyclerView rv_doctor;

    InquiryAdapter mInquiryAdapter;
    PopupWindow popupWindow;
    RvCommonAdapter popupadapter;
    RvCommonAdapter popupadapter_s;

    private static final String INQUIRY_FRAGMENT = "inquiry";
    private List<SortBean> list;
    private TextView tv_dept;
    private TextView tv_sort;
    private EditText edit_search;
    private SortBean bean;
    private int deptId;

    public static InquiryFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(INQUIRY_FRAGMENT, params);
        InquiryFragment fragment = new InquiryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_inquiry;
    }

    @Override
    public void onResume() {
        super.onResume();
        deptId = 100;
        setListToApp("", 100, 100);//默认列表
        setDepartment();

        setDummy();
        setPopupSort();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.inquiry_header, null);
        LinearLayout ll_tv_dept = inflate.findViewById(R.id.ll_tv_dept);
        LinearLayout ll_tv_sort = inflate.findViewById(R.id.ll_tv_sort);
        tv_dept = inflate.findViewById(R.id.tv_dept);
        tv_sort = inflate.findViewById(R.id.tv_sort);
        edit_search = inflate.findViewById(R.id.edit_search);
        rv_doctor.setLayoutManager(new LinearLayoutManager(mContext));
        mInquiryAdapter = new InquiryAdapter(new ArrayList<>());
        rv_doctor.setAdapter(mInquiryAdapter);
        mInquiryAdapter.addHeaderView(inflate);

        mInquiryAdapter.setOnItemClickListener((adapter, view, position) -> {
            ListToAppBean.RowsBean rowsBean = mInquiryAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            SpfUtils.saveIntToSpf(Constant.docId, rowsBean.getId()); // 医生id
            SpfUtils.saveStrToSpf(Constant.docNo, rowsBean.getDocNo());// 医生编号
            JumpUtils.jump(mContext, DoctorInfoActivity.class, bundle);
        });
        mInquiryAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_it:
                    JumpUtils.jump(mContext, ImageTextActivity.class, null);
                    break;
                case R.id.tv_vd:
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.VIDEO, 1);
                    JumpUtils.jump(mContext, MakeAppointmentActivity.class, bundle);
                    break;
            }
        });
        //点击科室
        ll_tv_dept.setOnClickListener(v -> GetScreen());
        //点击排序
        ll_tv_sort.setOnClickListener(v -> GetSortScreen());

        //搜索
        edit_search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                InquiryFragment.this.setListToApp(edit_search.getText().toString(), 100, 100);
                mKProgressHUD.dismiss();
                KeyBoardUtils.closeKeyBoard(mContext);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void initData() {

    }

    private void setPopupDept(List<DeptListBean> deptListBeans) {
        popupadapter = new RvCommonAdapter<DeptListBean>(mContext,
                R.layout.item_popu, deptListBeans) {

            @Override
            public void convert(ViewHolder holder, DeptListBean deptListBean, int position) {
                TextView tv_sort = holder.getView(R.id.tv_sort);
                TextView tv_dept = holder.getView(R.id.tv_dept);
                tv_sort.setVisibility(View.INVISIBLE);
                tv_dept.setVisibility(View.VISIBLE);
                tv_dept.setText(deptListBean.getDepartmentName());
            }
        };

        popupadapter.setItemClickListner(view -> {
            popupWindow.dismiss();
            DeptListBean bean = (DeptListBean) view.getTag();
            deptId = bean.getId();
            setListToApp(edit_search.getText().toString(), deptId, 100);
        });
    }

    private void setPopupSort() {
        popupadapter_s = new RvCommonAdapter<SortBean>(mContext,
                R.layout.item_popu, list) {

            @Override
            public void convert(ViewHolder holder, SortBean bean, int position) {
                TextView tv_sort = holder.getView(R.id.tv_sort);
                TextView tv_dept = holder.getView(R.id.tv_dept);
                tv_sort.setVisibility(View.VISIBLE);
                tv_dept.setVisibility(View.INVISIBLE);
                tv_sort.setText(bean.getSort());
            }
        };
        popupadapter_s.setItemClickListner(view -> {
            popupWindow.dismiss();
            SortBean bean = (SortBean) view.getTag();
            setListToApp(edit_search.getText().toString(), deptId, bean.getId());
        });
    }

    private void setDummy() {
        list = new ArrayList();
        list.add(new SortBean("综合排序", 0));
        list.add(new SortBean("评分排序", 1));
        list.add(new SortBean("问诊量排序", 2));
        list.add(new SortBean("服务价格排序", 3));
    }

    private void GetScreen() {
        setDepartment();
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.rv_popu_list)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimTop)
                .setViewOnclickListener((view, layoutResId) -> {
                    RecyclerView recyclerView = view.findViewById(R.id.rv_Pop);
                    GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
                    recyclerView.setLayoutManager(gManager);
                    recyclerView.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
                    recyclerView.setAdapter(popupadapter);
                }).create();

        //得到button的左上角坐标
        int[] positions = new int[2];
        tv_dept.getLocationOnScreen(positions);
        popupWindow.showAtLocation(mContext.findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + tv_dept.getHeight());
    }

    private void GetSortScreen() {
        setPopupSort();
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.rv_popu_list)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimTop)
                .setViewOnclickListener((view, layoutResId) -> {
                    RecyclerView recyclerView = view.findViewById(R.id.rv_Pop);
                    GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
                    recyclerView.setLayoutManager(gManager);
                    recyclerView.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
                    recyclerView.setAdapter(popupadapter_s);
                }).create();

        //得到button的左上角坐标
        int[] positions = new int[2];
        tv_dept.getLocationOnScreen(positions);
        popupWindow.showAtLocation(mContext.findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + tv_dept.getHeight());
    }

    /**
     * 科室接口
     */
    private void setDepartment() {
        RxHttpUtils.createApi(ApiService.class)
                .getDepartmentList()
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<DeptListBean>>() {
                    @Override
                    protected void onSuccess(List<DeptListBean> deptListBeans) {
                        setPopupDept(deptListBeans);
                    }

                    @Override
                    protected void onError(String errorMsg) {
//                        mKProgressHUD.dismiss();
                    }
                });

    }

    /**
     * 医生条目接口
     *
     * @param keyword
     * @param departmentId
     * @param sortId
     */
    private void setListToApp(String keyword, int departmentId, int sortId) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        Map<String, Object> prams = new HashMap<>();
        // prams.put("pageNumber", 1);
        // prams.put("pageSize", 10);
        if (departmentId != 100) {
            prams.put("departmentId", departmentId);
        }
        if (sortId != 100 || departmentId != 100) {
            prams.put("sort", sortId);
        }
        prams.put("keyword", keyword);
        RxHttpUtils.createApi(ApiService.class)
                .getListToApp(prams)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<ListToAppBean>() {
                    @Override
                    protected void onSuccess(ListToAppBean listToAppBean) {
                        if (null != listToAppBean || listToAppBean.getRows().size() != 0) {
                            mKProgressHUD.dismiss();
                            mInquiryAdapter.setNewData(listToAppBean.getRows());
                        }

                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        L.e(errorMsg);
                    }
                });
    }
}
