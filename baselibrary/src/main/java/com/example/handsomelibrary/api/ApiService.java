package com.example.handsomelibrary.api;


import android.util.ArrayMap;

import com.example.handsomelibrary.model.AddressToListBean;
import com.example.handsomelibrary.model.BannerBean;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.BaseListBean;
import com.example.handsomelibrary.model.ChooseUseManagementBean;
import com.example.handsomelibrary.model.ChooseUserBean;
import com.example.handsomelibrary.model.DeptListBean;
import com.example.handsomelibrary.model.DetailMoreBean;
import com.example.handsomelibrary.model.DiseaseBean;
import com.example.handsomelibrary.model.DoctorInfoBean;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.model.EvaluationDoctorBean;
import com.example.handsomelibrary.model.FollowDocBean;
import com.example.handsomelibrary.model.GetAddressInfoBean;
import com.example.handsomelibrary.model.HealthHistoryBean;
import com.example.handsomelibrary.model.HealthInformationBean;
import com.example.handsomelibrary.model.HomeItemBean;
import com.example.handsomelibrary.model.InfoExaminationBean;
import com.example.handsomelibrary.model.ListConsultBean;
import com.example.handsomelibrary.model.ListConsultDocBean;
import com.example.handsomelibrary.model.ListFollowDocBean;
import com.example.handsomelibrary.model.ListNoPageBean;
import com.example.handsomelibrary.model.ListClassBean;
import com.example.handsomelibrary.model.ListToAppBean;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.model.MakeBean;
import com.example.handsomelibrary.model.MallHomeBean;
import com.example.handsomelibrary.model.MentalHealthBean;
import com.example.handsomelibrary.model.OrderDetailsListBean;
import com.example.handsomelibrary.model.PayShoppingListBean;
import com.example.handsomelibrary.model.PdEvaluationEntity;
import com.example.handsomelibrary.model.ProductInfoBean;
import com.example.handsomelibrary.model.RegisterBean;
import com.example.handsomelibrary.model.ShopingCartBean;
import com.example.handsomelibrary.model.TokenBean;
import com.example.handsomelibrary.model.UserInfo;
import com.example.handsomelibrary.model.healthManagementInfoBean;
import com.example.handsomelibrary.model.listSnClassBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 接口 API
 * Created by Stefan on 2018/4/23.
 */

public interface ApiService {

    //    String BASE_URL = "http://116.196.95.169/";
//    String BASE_URL = "http://192.168.100.251:8089/";
    String BASE_URL = "http://192.168.100.12:8089/";
    //    String BASE_URL = "http://192.168.100.133:8089/";
    //    String BASE_PIC_URL = "http://116.196.95.169/file/getImages?imageurl=";
    String BASE_PIC_URL = "http://192.168.100.12:8089/file/getImages?imageurl=";

    /**
     * banner 轮播图
     *
     * @param type type= 1 ：患者端首页
     *             type= 2 ：医生端首页
     *             type= 3 ：商城首页
     * @return
     */
    @GET("app/banner/listToApp")
    Observable<BaseBean<List<BannerBean>>> getBanner(@Query("type") int type);

    /**
     * 1.1.搜索框【首页/在线问诊 】
     */
    @GET("app/doc/listToApp")
    Observable<BaseBean<ListToAppBean>> getListToApp(@QueryMap Map<String, Object> options);

    /**
     * 1.2.健康资讯
     */
    @GET("app/healthNews/listToApp")
    Observable<BaseBean<HealthInformationBean>> healthNews(@QueryMap ArrayMap<String, Object> options);

    /**
     * 1.3.热门医生
     */
    @GET("app/doc/hotDoc")
    Observable<BaseBean<HomeItemBean>> getHotDoc(@QueryMap ArrayMap<String, Integer> params);

    /**
     * 2.在线问诊 2.1.科室列表（不分页）
     */
    @GET("app/department/listToApp")
    Observable<BaseBean<List<DeptListBean>>> getDepartmentList();

    /**
     * 2.2.医生详情
     */
    @GET("app/doc/infoToApp")
    Observable<BaseBean<DoctorInfoBean>> getInfoToAppDoc(@QueryMap Map<String, Object> options);

    /**
     * 2.3.关注医生
     */
    @GET("customer/followDoc")
    Observable<FollowDocBean> getFollowDoc(@QueryMap Map<String, Object> options);

    /**
     * 2.4.预购买服务（图文、语音、视频）
     *
     * @param cusId
     * @return
     */
    @GET("customer/toBuyService")
    Observable<BaseBean<DiseaseBean>> getDiseaseBean(@Query("cusId") int cusId);

    /**
     * 2.5.购买服务（图文、语音、视频）
     */
    @POST("customer/buyService")
    Observable<BaseBean<String>> buyService(@Body ArrayMap<String, Object> options);

    /**
     * 2.6.获取就诊人信息列表
     */
    @GET("app/healthManagement/listVisitMembers")
    Observable<BaseBean<List<ChooseUseManagementBean>>> listVisitMembers(@QueryMap ArrayMap<String, Object> options);

    /**
     * 3.4.1.家庭成员列表
     */
    @GET("app/healthManagement/listToApp")
    Observable<BaseBean<ChooseUserBean>> HealthManagementListToApp(@QueryMap ArrayMap<String, Object> options);

    /**
     * 2.7.立即支付
     */
//    @GET("app/healthManagement/listVisitMembers")
//    Observable<BaseBean<List<ChooseUseManagementBean>>> listVisitMembers(@Query("cusId") int cusId);

    /**
     * 2.8.取消服务订单
     */
    @GET("customer/cancleOrder")
    Observable<BaseBean<Object>> cancleOrder(@Query("id") int cusId);

    /**
     * 2.9.删除服务订单
     */
    @GET("customer/removeOrder")
    Observable<BaseBean<Object>> removeOrder(@Query("id") int id);

    /**
     * 2.10.医生可预约时间段（患者端）
     */
    @GET("app/schedule/listToApp")
    Observable<BaseBean<List<MakeBean>>> getScheduleListToApp(@QueryMap ArrayMap<String, Object> options);

    /**
     * 2.11.评价列表（分页）
     */
    @GET("app/evaluation/listEvaluationToApp")
    Observable<BaseBean<EvaluationDoctorBean>> listByDocIdToApp(@QueryMap ArrayMap<String, Object> options);

    /**
     * 2.12.取消关注
     */
    @GET("customer/unfollowDoc")
    Observable<FollowDocBean> getUnFollowDoc(@QueryMap Map<String, Object> options);

    /**
     * 3.个人中心
     * 3.1.查看个人资料
     *
     * @param id
     * @return
     */
    @GET("customer/info")
    Observable<BaseBean<UserInfo>> getUserInfo(@Query("id") int id);

    /**
     * 3.2.修改个人资料
     *
     * @param options
     * @return
     */
    @POST("customer/updateToApp")
    Observable<BaseBean<String>> updateToApp(@Body ArrayMap<String, Object> options);

    /**
     * 3.3.我的医生
     * 3.3.1.咨询医生列表
     *
     * @param cusId
     * @return
     */
    @GET("customer/listConsultDoc")
    Observable<BaseBean<List<ListConsultDocBean>>> getListConsultDoc(@Query("cusId") int cusId);

    /**
     * 3.3.2.关注医生列表
     *
     * @param cusId
     * @return
     */
    @GET("customer/listFollowDoc")
    Observable<BaseBean<List<ListFollowDocBean>>> getListFollowDoc(@Query("cusId") int cusId);


    /**
     * 3.4.2.新增家庭成员
     *
     * @param options
     * @return
     */
    @POST("app/healthManagement/save")
    Observable<BaseBean<Object>> healthManagementSave(@Body ArrayMap<String, Object> options);

    /**
     * 3.4.3.编辑家庭成员
     * 3.4.3.1.回显数据
     */
    @GET("app/healthManagement/info")
    Observable<BaseBean<healthManagementInfoBean>> healthManagementInfo(@Query("id") int id);

    /**
     * 3.4.3.2.更新
     */
    @POST("app/healthManagement/update")
    Observable<BaseBean<Object>> healthManagementUpdate(@Body ArrayMap<String, Object> options);

    /**
     * 3.4.4.删除家庭成员
     *
     * @param id
     * @param cusId
     * @return
     */
    @GET("app/healthManagement/remove")
    Observable<BaseBean<Object>> removeManagementSave(@Query("id") int id, @Query("cusId") int cusId);

    /**
     * 3.4.5.设置默认
     */
    @POST("app/healthManagement/setDefault")
    Observable<BaseBean<Object>> SethealthManagementDefault(@Body ArrayMap<String, Object> options);

    /**
     * 3.5.我的咨询（图文、语音、视频）
     *
     * @param options
     * @return
     */
    @GET("customer/listConsult")
    Observable<BaseBean<List<ListConsultBean>>> getListConsult(@QueryMap ArrayMap<String, Object> options);

    /**
     * 3.5.1 修改订单状态
     *
     * @param options
     * @return
     */
    @GET("customer/updateStatus")
    Observable<BaseBean<String>> GetUpdateStatus(@QueryMap ArrayMap<String, Object> options);

    /**
     * 3.6.配送地址管理
     * 3.6.1.配送地址列表
     */
    @GET("app/address/listToApp")
    Observable<BaseBean<AddressToListBean>> listToApp(@QueryMap ArrayMap<String, Object> options);

    /**
     * 3.6.2.新增配送地址
     */
    @POST("app/address/save")
    Observable<BaseBean<Object>> address(@Body ArrayMap<String, Object> options);

    /**
     * 3.6.3.编辑配送地址
     * 3.6.3.1.回显数据
     */
    @GET("app/address/info")
    Observable<BaseBean<GetAddressInfoBean>> addressInfo(@Query("id") int id);

    /**
     * 3.6.3.2.编辑配送地址更新
     */
    @POST("app/address/update")
    Observable<BaseBean<Object>> addressUpdate(@Body ArrayMap<String, Object> options);

    /**
     * 3.6.4.删除配送地址列表删除
     */
    @GET("app/address/remove")
    Observable<BaseBean<Object>> getRemove(@QueryMap ArrayMap<String, Object> options);

    /**
     * 3.6.5.设置默认配送地址
     */
    @POST("app/address/setDefault")
    Observable<BaseBean<Object>> setDefault(@Body ArrayMap<String, Object> options);

    /**
     * 3.7.去评价（获取评价标签列表）
     */
    @GET("app/tag/listNoPage")
    Observable<BaseBean<List<ListNoPageBean>>> listNoPage();

    /**
     * 3.8.评价医生服务
     */
    @POST("app/evaluation/saveToApp")
    Observable<BaseBean<String>> saveToApp(@Body ArrayMap<String, Object> options);

    /**
     * 4.1.注册（邮箱注册）
     *
     * @return
     */
    @POST("app/user/registerToApp")
    Observable<RegisterBean> register(@Body ArrayMap<String, String> parame);


    /**
     * 4.2.重新发送激活邮件
     *
     * @param username
     * @return
     */
    @GET("app/user/sendEmailToAlive")
    Observable<BaseBean<Object>> sendEmailToAlive(@Query("username") String username);

    /**
     * 4.3.修改密码 设置--修改密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/user/updatePwd")
    Observable<BaseBean<Object>> updatePwd(@Field("username") String username, @Field("oldPwd") String oldPwd, @Field("newPwd") String password);

    /**
     * 4.4.通过邮箱找回密码
     * 4.4.1.发送邮件（验证码)
     *
     * @param username
     * @return
     */
    @GET("app/user/findPwdByEmail")
    Observable<RegisterBean> findPwdByEmail(@Query("username") String username);

    /**
     * 4.4.2.重新设置密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/user/resetPwdByEmail")
    Observable<RegisterBean> resetPwdByEmail(@Field("username") String username
            , @Field("newPwd") String newPwd
            , @Field("code") String code);

    /**
     * 4.5.登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/loginToApp")
    Observable<LoginBean> login(@Field("username") String username
            , @Field("password") String password
            , @Field("regSource") int regSource);

    /**
     * 4.6.检查token
     *
     * @param token
     * @return
     */
    @POST("cus/checktoken")
    @FormUrlEncoded
    Observable<TokenBean> checkToken(@Field("token") String token);


    /**
     * 4.7.意见反馈
     */
    @POST("app/feedback/save")
    Observable<BaseBean<String>> feedback(@Body ArrayMap<String, Object> options);

    /**
     * 4.8.获取图片地址
     */
    @GET("/file/getImages")
    Observable<BaseBean<String>> getImages(@Query("imageurl") String cusId);

    /**
     * 5.1.1 健康史 修改
     */
    @FormUrlEncoded
    @POST("app/health/updateHistory")
    Observable<BaseBean<HealthHistoryBean>> updateHistory(@Field("visitMemberId") int visitMemberId, @Field("familyHistory") String familyHistory, @Field("ownHistory") String ownHistory);

    /**
     * 5.1.2 健康史 详情
     */
    @GET("app/health/infoHistory")
    Observable<BaseBean<HealthHistoryBean>> infoHistory(@Query("visitMemberId") long visitMemberId);


    /**
     * 5.2.1 体检数据 详情（改为列表需修改）
     */
    @GET("app/health/infoExamination")
    Observable<BaseBean<InfoExaminationBean>> infoExamination(@Query("visitMemberId") long visitMemberId);

    /**
     * 5.2.2 体检数据 修改
     */
    @FormUrlEncoded
    @POST("app/health/updateExamination")
    Observable<Object> updateExamination(@FieldMap ArrayMap<String, Object> options);

    /**
     * 5.4.3.心理健康 获取测试题
     * type说明 1：心理健康
     */
    @GET("app/health/result/getTopic")
    Observable<BaseBean<MentalHealthBean>> GetTopic(@Query("type") Integer type, @Query("visitMemberId") int visitMemberId);

    /**
     * 5.4.3.保存测试结果
     */
    @FormUrlEncoded
    @POST("app/health/result/saveResult")
    Observable<BaseBean<Object>> saveResult(@FieldMap ArrayMap<String, Object> options);

    /* 商城 start*/

    /**
     * 商城首页
     *
     * @return
     */
    @GET("malls/index")
    Observable<BaseBean<List<MallHomeBean>>> getMallHomeList();

    /**
     * 添加商品到购物车
     *
     * @param cusId     会员ID
     * @param productId 商品ID
     * @return
     */
    @FormUrlEncoded
    @POST("malls/addCart")
    Observable<BaseBean<Object>> addCart(@Field("cusId") long cusId, @Field("productId") long productId);

    /**
     * 查看购物车
     * @param options
     * @return
     */
    @GET("malls/listCart")
    Observable<BaseBean<ShopingCartBean>> listCart(@QueryMap ArrayMap<String, Object> options);
    /**
     * 修改购物车商品数量
     *
     * @param cartId 购物车id
     * @return
     */
    @GET("malls/updateAmount")
    Observable<BaseBean<Object>> updateAmount(@Query("cartId") long cartId, @Query("amount") Integer amount);

    /**
     * 删除购物车商品
     *
     * @param cartId 购物车id
     * @return
     */
    @GET("malls/deleteCartGoods")
    Observable<BaseBean<Object>> deleteCartGoods(@Query("cartId") List<Long> cartId);

    /**
     * 加载默认的收货地址（确认订单页面）
     *
     * @param cusId
     * @return
     */
    @GET("malls/confirmOrder")
    Observable<BaseBean<PayShoppingListBean>> getConfirmOrder(@Query("cusId") long cusId);

    /**
     * 1.从购物车下单
     * 2.立即购买下单
     */

    @FormUrlEncoded
    @POST("malls/order")
    Observable<Object> getOrder(@FieldMap ArrayMap<String, Object> options);

    /**
     * 订单详情
     *
     * @param orderId
     * @return
     */
    @GET("malls/orderInfo")
    Observable<BaseBean<OrderDetailsListBean>> getOrderInfo(@Query("orderId") long orderId);

    /**
     * 确认收货
     *
     * @param orderId
     * @return
     */
    @GET("malls/confirmReceipt")
    Observable<BaseBean<Object>> getConfirmReceipt(@Query("orderId") long orderId);

    /**
     * 我的订单（全部、待付款、待发货、待收货、待评论）
     * 订单状态 inputStatus （入参）
     * 0;全部
     * 1;待付款
     * 2;待发货
     * 3;待收货
     * 4;待评论
     *
     * @return
     */
    @GET("malls/listOrder")
    Observable<BaseBean<DrugOrderBean>> getListOrder(@QueryMap ArrayMap<String, Object> options);

    /**
     * 商品列表（关键字搜索、销量降序、价格升序/降序）
     *
     * @param options
     * @return
     */
    @GET("/malls/listProduct")
    Observable<BaseBean<BaseListBean>> listProduct(@QueryMap ArrayMap<String, Object> options);

    /**
     * 商品详情
     */
    @GET("malls/productInfo")
    Observable<BaseBean<ProductInfoBean>> getProductInfo(@Query("productId") long productId, @Query("cusId") long cartId);

    /**
     * 商品评价列表 更多评价
     */
    @GET("malls/listComment")
    Observable<BaseBean<DetailMoreBean>> getListComment(@QueryMap ArrayMap<String, Object> options);


    /**
     * 商品评价列表
     */
    @POST("malls/comment")
    Observable<BaseBean<String>> postComment(@Body List<PdEvaluationEntity> list);

    /**
     * 分类精选 父类
     */
    @GET("malls/listClass")
    Observable<BaseBean<ListClassBean>> getListClass();

    /**
     * 分类精选 子类
     */
    @GET("malls/listSnClass")
    Observable<BaseBean<List<listSnClassBean>>> getListSnClass(@Query("pdCf") String pdCf);

    /**
     * 收藏商品
     */
    @FormUrlEncoded
    @POST("malls/collect")
    Observable<BaseBean<Object>> getCollect(@Field("cusId") long cusId, @Field("productId") long productId);

    /**
     * 取消收藏
     */
    @GET("malls/cancleCollect")
    Observable<BaseBean<Object>> getCancleCollect(@Query("cusId") long cusId, @Query("productId") long productId);

    /**
     * 我的收藏
     */
    @GET("malls/listCollect")
    Observable<BaseBean<BaseListBean>> getListCollect(@QueryMap ArrayMap<String, Object> options);

    /*商城 end*/


    /**
     * 多图片上传
     *
     * @param type
     * @param files
     * @return
     */
    @Multipart
    @POST("file/uploadImages")
    Observable<BaseBean<String>> uploadPostFile(@Part("type") RequestBody type,
                                                @Part List<MultipartBody.Part> files);


}

