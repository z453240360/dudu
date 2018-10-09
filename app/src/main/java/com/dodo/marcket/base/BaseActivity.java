package com.dodo.marcket.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bigkoo.pickerview.view.TimePickerView;
import com.dodo.marcket.R;
import com.dodo.marcket.bean.basebean.UserInfoBean;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.iCallback.PermissionsListener;
import com.dodo.marcket.utils.ColorState;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.TUtil;
import com.dodo.marcket.utils.TitleUtil;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;
import com.dodo.marcket.wedget.toasty.Toasty;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T mPresenter;
    public Context mContext;
    public BaseActivity mActivity;
    protected TitleUtil mTitle;
    private PermissionsListener mListener;
    public static String TAG = "dd";
    public static UserInfoBean userInfoBean;
    public static double latitude;
    public static double longitude;
    public static String locationString;
    public boolean showId=true;
    public String dumpcartId;
    public String disctCode;
    public static boolean hastoken = false;
    public String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };

    public String token;
    private TimePickerView pvTime;
    public boolean isCheckToken = true;
    private String needToken;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏

        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        mContext = this;
        mActivity = this;
        try {
            mPresenter = TUtil.getT(this, 0);
        } catch (Exception e) {

        }
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }


        needToken = (String) SharedPreferencesUtil.get(this, Constant.token, "");

        if (needToken.equals("")){
            hastoken = false;
        }else {
            hastoken = true;
        }

        if (userInfoBean == null) {
            userInfoBean = new UserInfoBean();
        }
        mTitle = new TitleUtil(this, getWindow().getDecorView());
        initStatusBar();
//        if (showId){
//            token = (String) SharedPreferencesUtil.get(mActivity,  Constant.token, "");
//            dumpcartId = (String) SharedPreferencesUtil.get(mActivity, Constant.dumpcartId, "");
//            disctCode = (String) SharedPreferencesUtil.get(mActivity, Constant.disctCode, "");
//            if (TextUtils.isEmpty(token)||TextUtils.isEmpty(dumpcartId)||TextUtils.isEmpty(disctCode)){
//                startActivity(new Intent(mActivity,LoginActivity.class));
//                finish();
//            }
//        }

        initPresenter();
        loadData();
    }


    public double[] getLocation(){
        double[] location = new double[]{latitude,longitude};
        return location;
    }

    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //加载、设置数据
    public abstract void loadData();


    public void initStatusBar() {
        //层垫式状态栏
        StatusBarUtils.setColor(this, getResources().getColor(R.color.white), 0);
        ColorState.StatusBarLightMode(mActivity, false);
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
    }
    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    IWindowFocus iFocus;

    public void setOnIWindowFocus(IWindowFocus windowFocus) {
        iFocus = windowFocus;
    }

    public interface IWindowFocus {
        void focused();
    }

    @Override
    protected void onResume() {
        super.onResume();

        needToken = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
        if (needToken.equals("")){
            hastoken = false;
        }else {
            hastoken = true;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    long exitTime = 0;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        AppManager.getInstance().finishActivity(this);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 请求权限封装
     *
     * @param permissions
     * @param listener
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions, PermissionsListener listener) {
        mListener = listener;
        List<String> requestPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions.add(permission);
            }
        }
        if (requestPermissions.isEmpty()) {
            //已经全部授权
            mListener.onGranted();
        } else {
            //申请授权
            requestPermissions(requestPermissions.toArray(new String[requestPermissions.size()]), 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                List<String> deniedPermissions = new ArrayList<>();
                //当所有拒绝的权限都勾选不再询问，这个值为true,这个时候可以引导用户手动去授权。
                boolean isNeverAsk = true;
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    String permission = permissions[i];
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        deniedPermissions.add(permissions[i]);
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) { // 点击拒绝但没有勾选不再询问
                            isNeverAsk = false;
                        }
                    }
                }

                if (deniedPermissions.isEmpty()) {
                    try {
                        mListener.onGranted();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        mListener.onDenied(Arrays.asList(permissions), true);
                    }
                } else {
                    mListener.onDenied(deniedPermissions, isNeverAsk);
                }
                break;
            default:
                break;
        }
    }


    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }
    public String getTime(Date date,boolean showString) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }

    public void showErrorToast(APIException e){
        Toasty.error(mContext,e.getMessage(),3,false).show();
        if (TextUtils.equals(e.getCode(),"")){
//            startActivity(LoginActivity.class);
            finish();
        }
    }

    public void showErrorToast(String e){
        Toasty.error(mContext,e,3,false).show();
    }

    public void goToLogin(){
        startActivity(LoginActivity.class);
    }
}



