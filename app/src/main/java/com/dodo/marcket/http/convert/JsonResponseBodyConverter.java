package com.dodo.marcket.http.convert;

import android.content.Intent;
import android.text.TextUtils;

import com.dodo.marcket.base.App;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.http.utils.BResponse;
import com.dodo.marcket.http.utils.JSONUtils;
import com.dodo.marcket.utils.LogUtils;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author：fmd on 16/8/22 16
 * use:
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private TypeAdapter<T> mAdapter;
    private static final String TAG = "JsonResponseBodyConverter";
    public JsonResponseBodyConverter(TypeAdapter<T> adapter){
        this.mAdapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseContent = value.string();
        LogUtils.d(TAG , "ResponseBody ====> " + responseContent);
        try {
            String data="";
            JSONObject jsonObject = new JSONObject(responseContent);

            try{
                data = jsonObject.getString("data");
            }catch (Exception e){
                data="";
            }

            if(!TextUtils.isEmpty(data)){
                String decryptData = data;//DES3Utils.decryptMode(data);
                //打印解密后的data字符串
                LogUtils.d(TAG , "data ====> " + decryptData);
                jsonObject.put("data" , buildData(decryptData));
                responseContent = jsonObject.toString();
            }else{
                buildDefault(responseContent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            buildDefault(responseContent);
        }
        if(mAdapter == null){
            return (T) responseContent;
        }else{

            JsonReader jsonReader = new JsonReader(new StringReader(responseContent));
            try {
                return mAdapter.read(jsonReader);
            } catch(Exception e){
                //用gson判断对于空数据需要做单独判断
                return buildDefault(responseContent);
            }finally {
                value.close();
            }
        }
    }

    private T buildDefault(String responseStr){
        try {
            JSONObject jsonObject = new JSONObject(responseStr);
            String returnCode = JSONUtils.getString(jsonObject , "code");
            String errorMsg = JSONUtils.getString(jsonObject , "msg");
            BResponse response = new BResponse();
            response.code = returnCode;
            response.msg = errorMsg;
            if (TextUtils.equals(returnCode,"41")){
                SharedPreferencesUtil.put(App.getContext(), Constant.token,"");
                App.getContext().startActivity(new Intent(App.getContext(), LoginActivity.class));
            }
            return (T)response;
        } catch (JSONException e1) {
            e1.printStackTrace();
            return (T)new BResponse();
        }
    }

    private Object buildData(String data) throws JSONException {
        if(TextUtils.isEmpty(data)){
            return "";
        }

        if(data.startsWith("{") && data.endsWith("}")){
            return new JSONObject(data);
        }

        if(data.startsWith("[") && data.endsWith("]")){
            return new JSONArray(data);
        }

        return data;
    }
}
