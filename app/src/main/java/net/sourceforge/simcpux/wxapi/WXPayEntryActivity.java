package net.sourceforge.simcpux.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private TextView mTxt_paywx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        mTxt_paywx = findViewById(R.id.mTxt_wxPay);
    }

    @Override
    public void onReq(BaseReq baseReq) {

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
}
