package com.dodo.marcket.business.mine.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.iCallback.PermissionsListener;
import com.dodo.marcket.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ZXingActivity extends BaseActivity implements QRCodeView.Delegate,PermissionsListener {

    @BindView(R.id.zxingview)
    ZXingView zxingview;
    @BindView(R.id.mBtn_light)
    Button mBtnLight;
    @BindView(R.id.mBtn_tesst)
    Button mBtnTesst;
    private String id;
    private String orderNum;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//        ButterKnife.bind(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
////        //从Intent 中获取数据
////        Bundle myBundelForGetName=this.getIntent().getExtras();
////        id = myBundelForGetName.getString("id","");
////        orderNum = myBundelForGetName.getString("orderNum","");
//
//        zxingview.startSpot();
//        zxingview.setDelegate(this);
//
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_zxing;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }


        requestPermissions(permissions,this);
        zxingview.startSpot();
        zxingview.setDelegate(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        zxingview.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zxingview.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

//        if (!result.equals(orderNum)) {
//            ToastUtils.show(this, "请扫描正确的二维码");
////            zxingview.startSpot();
//            finish();
//            return;
//        }

        Intent intent = new Intent();
        intent.putExtra("result",result);
        setResult(500, intent);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(ZXingActivity.this, "请打开相机权限", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.mBtn_light, R.id.mBtn_tesst})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBtn_light:
                zxingview.openFlashlight();
                break;
            case R.id.mBtn_tesst:
                zxingview.closeFlashlight();
                break;
        }
    }

    @Override
    public void onGranted() {
        Log.i(TAG, "onGranted: ");
        zxingview.startSpot();
        zxingview.setDelegate(this);
    }

    @Override
    public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
//        Toast.makeText(ZXingActivity.this, "请打开相机权限", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < deniedPermissions.size(); i++) {
            Log.i(TAG, "onDenied: "+"----"+i+"----"+deniedPermissions.get(i));
        }
    }
}
