package com.dodo.marcket.utils;

import android.app.Activity;
import android.content.Context;

import com.dodo.marcket.http.constant.Constant;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class WeiXinPayUtils {


    public PayReq weiXinPay(Context context) {


        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wxd930ea5d5a258f4f");

        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId = "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";
        request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";


        msgApi.sendReq(request);
        return request;
    }



    //获取键值对集合
    public static  Map<String,String> getMap( PayReq request){

//        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId = "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";

        Map<String,String> map = new HashMap<>();
        map.put("appId",request.appId);
        map.put("partnerId",request.partnerId);
        map.put("prepayId",request.prepayId);
        map.put("packageValue",request.packageValue);
        map.put("nonceStr",request.nonceStr);
        map.put("timeStamp",request.timeStamp);
        return map;
    }



    /**
     * 生成签名
     */
    public static String getSign(Map<String, String> map) {

        map = getMap(new PayReq());

        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）

            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append(key + ":" + val + ":");
                    }
                }
            }
            //			sb.append(PropertyManager.getProperty("SIGNKEY"));
            result = sb.toString();

            //进行MD5加密
            result = EncoderUtils.encoder(result).toUpperCase();
        } catch (Exception e) {
            return null;
        }
        return result;
    }


}
