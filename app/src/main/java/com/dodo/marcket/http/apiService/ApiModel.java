package com.dodo.marcket.http.apiService;


import com.dodo.marcket.http.retrofit.RetroUtils;
import com.dodo.marcket.http.utils.BResponse;
import com.dodo.marcket.http.utils.FlatProvider;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * author：fmd on 16/9/12
 * use:
 */
public class ApiModel {

    private static final String TAG = "ApiModel";

    private static volatile ApiModel apiModel;

    public static ApiModel create() {
        if (apiModel == null) {
            synchronized (ApiModel.class) {
                if (apiModel == null) {
                    apiModel = new ApiModel();
                }
            }
        }
        return apiModel;
    }

    private ApiModel() {

    }


//    获取验证码
    public Observable<BResponse> getVerCode(RequestBody body) {
        return RetroUtils.getApi().getVerCode(body).flatMap(FlatProvider.<BResponse>flat());
    }

    // 登陆
    public Observable<BResponse> getLogin(RequestBody body) {
        return RetroUtils.getApi().getLogin(body).flatMap(FlatProvider.<BResponse>flat());
    }


    //分类----获取一级列表
    public Observable<BResponse> getFirstClassfyList(RequestBody body) {
        return RetroUtils.getApi().getFirstClassfyList(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //首页----获取热销商品
    public Observable<BResponse> getHotProductList(RequestBody body) {
        return RetroUtils.getApi().getHotProductList(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //首页----获取购物车数量
    public Observable<BResponse> getCarNum(RequestBody body) {
        return RetroUtils.getApi().getCarNum(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //首页----获取首页轮播
    public Observable<BResponse> getBanner(RequestBody body) {
        return RetroUtils.getApi().getBanner(body).flatMap(FlatProvider.<BResponse>flat());
    }
    //首页----获取首页活动模块
    public Observable getAllPromotion(RequestBody body) {
        return RetroUtils.getApi().getAllPromotion(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----获取优惠券
    public Observable<BResponse> getDisCount(RequestBody body) {
        return RetroUtils.getApi().getDisCount(body).flatMap(FlatProvider.<BResponse>flat());
    }


    //个人中心----获取用户信息
    public Observable<BResponse> getUserMsg(RequestBody body) {
        return RetroUtils.getApi().getUserMsg(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----获取用户地址
    public Observable<BResponse> getMyAddress(RequestBody body) {
        return RetroUtils.getApi().getMyAddress(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----新增会员地址
    public Observable<BResponse> addNewAddress(RequestBody body) {
        return RetroUtils.getApi().addNewAddress(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----删除会员地址
    public Observable deleteReceiver(RequestBody body) {
        return RetroUtils.getApi().deleteReceiver(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----更新会员地址
    public Observable updateReceiver(RequestBody body) {
        return RetroUtils.getApi().updateReceiver(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----根据id获取该地区的子区域,上海的id=792
    public Observable getListChildArea(RequestBody body) {
        return RetroUtils.getApi().getListChildArea(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----申请退框
    public Observable getBackBox(RequestBody body) {
        return RetroUtils.getApi().getBackBox(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----我的退框单
    public Observable getMyBackBoxList(RequestBody body) {
        return RetroUtils.getApi().getMyBackBoxList(body).flatMap(FlatProvider.<BResponse>flat());
    }


    //个人中心----生成退框单
    public Observable getBackOrder(RequestBody body) {
        return RetroUtils.getApi().getBackOrder(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----绑定业务员
    public Observable bindSalesMan(RequestBody body) {
        return RetroUtils.getApi().bindSalesMan(body).flatMap(FlatProvider.<BResponse>flat());
    }


    //个人中心----解除业务员
    public Observable cancleSalesMan(RequestBody body) {
        return RetroUtils.getApi().cancleSalesMan(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //个人中心----业务员信息
    public Observable getSalesMan(RequestBody body) {
        return RetroUtils.getApi().getSalesMan(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //分类----获取商品列表
    public Observable<BResponse> getProductByCategoryId(RequestBody body) {
        return RetroUtils.getApi().getProductByCategoryId(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //分类----搜索商品
    public Observable<BResponse> searchGoods(RequestBody body) {
        return RetroUtils.getApi().searchGoods(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //购物车----添加商品
    public Observable<BResponse> addProduct(RequestBody body) {
        return RetroUtils.getApi().addProduct(body).flatMap(FlatProvider.<BResponse>flat());
    }
    //购物车----清空购物车
    public Observable<BResponse> clearShoppingCar(RequestBody body) {
        return RetroUtils.getApi().clearShoppingCar(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //购物车----购物车删除商品
    public Observable<BResponse> delProduct(RequestBody body) {
        return RetroUtils.getApi().delProduct(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //购物车----获取购物车商品
    public Observable<BResponse> getProducts(RequestBody body) {
        return RetroUtils.getApi().getProducts(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //购物车----结算商品
    public Observable<BResponse> payProducts(RequestBody body) {
        return RetroUtils.getApi().payProducts(body).flatMap(FlatProvider.<BResponse>flat());
    }


    //购物车----商品详情
    public Observable<BResponse> getProductDetailById(RequestBody body) {
        return RetroUtils.getApi().getProductDetailById(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //购物车----修改数量
    public Observable<BResponse> mergeQty(RequestBody body) {
        return RetroUtils.getApi().mergeQty(body).flatMap(FlatProvider.<BResponse>flat());
    }
    //商品详情----根据规格值获取商品
    public Observable<BResponse> getProductDetailByProductSpec(RequestBody body) {
        return RetroUtils.getApi().getProductDetailByProductSpec(body).flatMap(FlatProvider.<BResponse>flat());
    }

    //热销商品活动
    public Observable<BResponse> getPromotionDetail(RequestBody body) {
        return RetroUtils.getApi().getPromotionDetail(body).flatMap(FlatProvider.<BResponse>flat());
    }
}
