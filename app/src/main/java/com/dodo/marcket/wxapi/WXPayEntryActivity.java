package com.dodo.marcket.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.zip.Inflater;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    private TextView mTxt_paywx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        mTxt_paywx = findViewById(R.id.mTxt_wxPay);

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("dd", "onReq: " + baseReq.toString());
    }

    @Override
    public void onResp(BaseResp resp) {
        //0 成功 -1信息错误 -2 用户取消
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.d("dd", "onPayFinish,errCode=" + resp.errCode);

            String msg = "";

            View inflate = LayoutInflater.from(this).inflate(R.layout.showpay, null);
            TextView mTxtTitle = inflate.findViewById(R.id.mTxt_title);
            TextView mTxt_msg = inflate.findViewById(R.id.mTxt_msg);
            TextView mTxt_paySure = inflate.findViewById(R.id.mTxt_paySure);

            mTxt_paySure.getLayoutParams().width = ScreenUtil.getScreenWidth(this)/2+ScreenUtil.getScreenWidth(this)/4;

            AlertDialog dialog = new AlertDialog.Builder(this, R.style.dialog).create();//创建对话框
            dialog.setIcon(R.mipmap.ic_launcher);//设置对话框icon
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);

//            mTxt_msg.setText("微信支付结果：" + String.valueOf(resp.errCode));
//            mTxtTitle.setText("提示");
            mTxt_paySure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            dialog.setView(inflate);


            switch (resp.errCode) {
                case 0://支付成功
//                    ToastUtils.show(this,"支付成功");
                    mTxt_msg.setText("支付成功");
                    dialog.show();
                    break;

                case -1://支付失败
                    mTxt_msg.setText("支付失败:"+resp.errStr+" "+resp.errCode);
                    dialog.show();
                    break;

                case -2://取消支付
//                    ToastUtils.show(this,"取消支付");
                    mTxt_msg.setText("取消支付");
                    dialog.show();
                    break;
            }


//            mTxt_paywx.setText(resp.errStr+" 调用支付失败，签名未通过"  +"    错误码："+resp.errCode);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
