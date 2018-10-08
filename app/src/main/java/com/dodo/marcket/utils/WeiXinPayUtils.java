package com.dodo.marcket.utils;

import android.app.Activity;
import android.content.Context;

import com.dodo.marcket.http.constant.Constant;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class WeiXinPayUtils {


    public void weiXinPay(Context context){


        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wxd930ea5d5a258f4f");

        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId= "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr= "1101000000140429eb40476f8896f4c9";
        request.timeStamp= "1398746574";
        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        msgApi.sendReq(request);
    }


//    /**
//     * 微信支付
//     */
//    public void startWeChatPay(Activity activity, PayReq payReponse) {
//        if (activity == null || payReponse == null)
//            return;
////        if (!Constant.APP_ID.equals(payReponse.appId()))
////            return;
//
//        IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, Constant.APP_ID, true);
//        // 将该app注册到微信
//        wxapi.registerApp(Constant.APP_ID);
//        if (!wxapi.isWXAppInstalled()) {
////            HongshiUtil.showToast("你没有安装微信");
//            return;
//        }
//        //我们把请求到的参数全部给微信
//        PayReq req = new PayReq(); //调起微信APP的对象
//        req.appId = WxPayConfig.APP_ID;
//        req.partnerId = payReponse.getPartnerid();
//        req.prepayId = payReponse.getPrepayid();
//        req.nonceStr = payReponse.getNoncestr();
//        req.timeStamp = payReponse.getTimestamp();
//        req.packageValue = payReponse.getPackageX(); //Sign=WXPay
//        req.sign = payReponse.getSign();
//
//        wxapi.sendReq(req); //发送调起微信的请求
//    }


}
