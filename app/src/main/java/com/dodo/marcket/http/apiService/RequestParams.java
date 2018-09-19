package com.dodo.marcket.http.apiService;


import com.dodo.marcket.base.App;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.AppUtils;
import com.dodo.marcket.utils.EncoderUtils;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by fangmingdong on 2017/6/5.
 */

public class RequestParams {

    private Map<String, Object> map = new HashMap<>();

    private long timestamp;

    public static RequestParams buildParams() {
        return new RequestParams();
    }

    public static RequestParams buildParams(boolean appendToken) {
        return new RequestParams(appendToken);
    }

    private boolean appendToken = true;

    public RequestParams(boolean appendToken) {
        timestamp = System.currentTimeMillis();
        this.appendToken = appendToken;
    }

    public RequestParams() {
    }

    public RequestParams append(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public RequestParams appendMap(Map<String, Object> kvMap) {
        for (Map.Entry<String, Object> entry : kvMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Map getParams() {
        if (appendToken) {
            map.put("noce", AppUtils.getUUID());
            map.put("timestamp", timestamp);
            map.put("token", SharedPreferencesUtil.get(App.getContext(), Constant.token, ""));
            String sign = buildSign(map, Constant.sign);
            map.put("sign",sign);
        }
        return map;
    }

    //不带token
    public static RequestBody getParams(PhoneBean phoneBean, String name) {
        HashMap<String, Object> param = getPublicParams(name, new Gson().toJson(phoneBean));
        param.put("sign", buildSign(param, Constant.sign));
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(param));
        return body;
    }

    //公共参数，不带token
    private static HashMap<String, Object> getPublicParams(String name, String data) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("data", data);
        param.put("name", name);
        param.put("version", "1.0");
        param.put("format", "json");
        param.put("app_key", "app_id_2");
        param.put("timestamp", System.currentTimeMillis());
        param.put("nonce", AppUtils.getUUID());
        return param;
    }

    //按字母排序后加密，返回sign
    public static String buildSign(Map<String, ?> paramsMap, String secret) {
        Set<String> keySet = paramsMap.keySet();
        List<String> paramNames = new ArrayList<String>(keySet);
        Collections.sort(paramNames);
        List<String> list = new ArrayList<>();
        for (String paramName : paramNames) {
            String key = paramName;
            String value = paramsMap.get(paramName).toString();
            if (!key.equals("") && !value.equals("")) {
                list.add(key + "=" + (value != null ? value : ""));
            }
        }
        String source = join(list) + secret;
        return EncoderUtils.encoder(source);
    }

    private static String join(List<String> array) {
        if (array.size() == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.size(); i++) {
            if (i == array.size() - 1) {
                buffer.append(array.get(i));
            } else {
                buffer.append(array.get(i) + "&");
            }
        }
        return buffer.toString();
    }
}
