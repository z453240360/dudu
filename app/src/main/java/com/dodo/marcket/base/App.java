package com.dodo.marcket.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.dodo.marcket.R;
import com.dodo.marcket.http.constant.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.youth.banner.WeakHandler;

import butterknife.ButterKnife;


public class App extends MultiDexApplication {
    public static final String MAIN_PROCESS = "com.tzj.ljc";
    public static App mApp;
    public static boolean isFirstLoginActivity;
    public static WeakHandler mainHandler;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.basicColor, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (MAIN_PROCESS.equals(getCurrentProcessName())) {
            mApp = this;
            mainHandler = new WeakHandler(Looper.getMainLooper());
            isFirstLoginActivity = true;
            ButterKnife.setDebug(Constant.showDebug);
        }


    }

    //获取当前进程名称
    private String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    public static App getmApp() {
        return mApp;
    }

    public static boolean getFirstLoginActivity() {
        return isFirstLoginActivity;
    }

    public static void setIsFirstLoginActivity(boolean isFirstLoginActivity) {
        App.isFirstLoginActivity = isFirstLoginActivity;
    }


    public static Context getContext() {
        return mApp.getApplicationContext();
    }

    public static String getAPPName() {
        return getmApp().getResources().getString(R.string.app_name);
    }

    public static WeakHandler getMainHandler() {
        return mainHandler;
    }

}
