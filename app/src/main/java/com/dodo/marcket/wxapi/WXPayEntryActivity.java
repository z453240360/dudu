package com.dodo.marcket.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.http.constant.Constant;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    private TextView mTxt_paywx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        mTxt_paywx = findViewById(R.id.mTxt_wxPay);

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID,false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("dd", "onReq: "+baseReq.toString());
    }

    @Override
    public void onResp(BaseResp resp) {
        //0 成功 -1信息错误 -2 用户取消
        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            Log.d("dd","onPayFinish,errCode="+resp.errCode);
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("微信支付结果："+ String.valueOf(resp.errCode));
            builder.show();

            mTxt_paywx.setText(resp.errStr+"    错误码："+resp.errCode);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
