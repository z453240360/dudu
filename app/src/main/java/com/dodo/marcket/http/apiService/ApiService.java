package com.dodo.marcket.http.apiService;


import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.ChildAddressBean;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.HomePageActivityBean;
import com.dodo.marcket.bean.HotBean;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.bean.MakeOrderBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.bean.PayMethodBean;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.SalesMan;
import com.dodo.marcket.bean.SelectPostTimeBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.basebean.UserInfoBean;
import com.dodo.marcket.http.utils.BResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public interface ApiService {

    //测试
    @POST()
    Call<ResponseBody> getCode(@Body RequestBody verifyCode);

    //首页--筛选1
    @POST(Api.url)
    Observable<BResponse<List<String>>> getSelectselect(@Body RequestBody verifyCode);

    //测试
    @POST(Api.url)
    Call<ResponseBody> test(@Body RequestBody verifyCode);


    //登陆--获取验证码
    @POST(Api.url)
    Observable<BResponse<List<String>>> getVerCode(@Body RequestBody verifyCode);

    //登陆--获取验证码
    @POST(Api.url)
    Observable<BResponse<LoginBean>> getLogin(@Body RequestBody verifyCode);

    //分类---获取一级列表
    @POST(Api.url)
    Observable<BResponse<List<FirstClassfyBean>>> getFirstClassfyList(@Body RequestBody verifyCode);

    //分类---商品列表
    @POST(Api.url)
    Observable<BResponse<List<ProductBean>>> getProductByCategoryId(@Body RequestBody verifyCode);

    //首页---获取热销商品
    @POST(Api.url)
    Observable<BResponse<List<ProducHeadBean>>> getHotProductList(@Body RequestBody verifyCode);

    //首页---获取购物车数量
    @POST(Api.url)
    Observable<BResponse<Integer>> getCarNum(@Body RequestBody verifyCode);

    //首页---首页轮播
    @POST(Api.url)
    Observable<BResponse<List<BannerBean>>> getBanner(@Body RequestBody verifyCode);

    //首页---首页活动模块
    @POST(Api.url)
    Observable<BResponse<List<HomePageActivityBean>>> getAllPromotion(@Body RequestBody verifyCode);



    //个人中心---获取优惠券
    @POST(Api.url)
    Observable<BResponse<List<DisCountBean>>> getDisCount(@Body RequestBody verifyCode);

    //个人中心---获取个人信息
    @POST(Api.url)
    Observable<BResponse<UserInfoBean>> getUserMsg(@Body RequestBody verifyCode);

    //个人中心---新增个人地址信息
    @POST(Api.url)
    Observable<BResponse<Boolean>> addNewAddress(@Body RequestBody verifyCode);


    //个人中心---获取个人地址
    @POST(Api.url)
    Observable<BResponse<List<MyAddressBean>>> getMyAddress(@Body RequestBody verifyCode);

    //个人中心---删除个人地址
    @POST(Api.url)
    Observable<BResponse<Boolean>> deleteReceiver(@Body RequestBody verifyCode);

    //个人中心---更新个人地址
    @POST(Api.url)
    Observable<BResponse<List<MyAddressBean>>> updateReceiver(@Body RequestBody verifyCode);

    //个人中心---根据id获取该地区的子区域,上海的id=792
    @POST(Api.url)
    Observable<BResponse<List<ChildAddressBean>>> getListChildArea(@Body RequestBody verifyCode);


    //个人中心---申请退筐
    @POST(Api.url)
    Observable<BResponse<List<BackBoxBean>>> getBackBox(@Body RequestBody verifyCode);

    //个人中心---我的退框单
    @POST(Api.url)
    Observable<BResponse<List<MyBackBoxBean>>> getMyBackBoxList(@Body RequestBody verifyCode);


    //个人中心---生成退框单
    @POST(Api.url)
    Observable<BResponse<List<BackBoxBean>>> getBackOrder(@Body RequestBody verifyCode);

    //个人中心---绑定业务员
    @POST(Api.url)
    Observable<BResponse<Boolean>> bindSalesMan(@Body RequestBody verifyCode);

    //个人中心---解除业务员
    @POST(Api.url)
    Observable<BResponse<Boolean>> cancleSalesMan(@Body RequestBody verifyCode);

    //个人中心---业务员信息
    @POST(Api.url)
    Observable<BResponse<SalesMan>> getSalesMan(@Body RequestBody verifyCode);

    //购物车---添加商品
    @POST(Api.url)
    Observable<BResponse<Boolean>> addProduct(@Body RequestBody verifyCode);

    //购物车---清空购物车
    @POST(Api.url)
    Observable<BResponse<Boolean>> clearShoppingCar(@Body RequestBody verifyCode);

    //购物车---购物车删除商品
    @POST(Api.url)
    Observable<BResponse<Boolean>> delProduct(@Body RequestBody verifyCode);

    //购物车---获取购物车商品
    @POST(Api.url)
    Observable<BResponse<ShoppingCarBean>> getProducts(@Body RequestBody verifyCode);

    //购物车---获取支付账单信息
    @POST(Api.url)
    Observable<BResponse<GoToPayBean>> payProducts(@Body RequestBody verifyCode);

    //购物车---商品详情
    @POST(Api.url)
    Observable<BResponse<ProductBean>> getProductDetailById(@Body RequestBody verifyCode);

    //购物车---修改数量
    @POST(Api.url)
    Observable<BResponse<ProductBean>> mergeQty(@Body RequestBody verifyCode);

    //分类---搜索商品
    @POST(Api.url)
    Observable<BResponse<List<ProductBean>>> searchGoods(@Body RequestBody verifyCode);


    //商品详情---根据规格值获取商品信息
    @POST(Api.url)
    Observable<BResponse<ProductBean>> getProductDetailByProductSpec(@Body RequestBody verifyCode);

    //热销商品活动
    @POST(Api.url)
    Observable<BResponse<HotBean>> getPromotionDetail(@Body RequestBody verifyCode);

    //结算----列出送达时间
    @POST(Api.url)
    Observable<BResponse<List<SelectPostTimeBean>>> deliverytimes(@Body RequestBody verifyCode);

    //结算----列出支付方式
    @POST(Api.url)
    Observable<BResponse<List<PayMethodBean>>> payfunctions(@Body RequestBody verifyCode);

    //结算----生成订单编号
    @POST(Api.url)
    Observable<BResponse<MakeOrderBean>> makeOrderId(@Body RequestBody verifyCode);

    //结算----吊起支付信息
    @POST(Api.url)
    Observable<BResponse<AliPayBean>> payOrder(@Body RequestBody verifyCode);

}
