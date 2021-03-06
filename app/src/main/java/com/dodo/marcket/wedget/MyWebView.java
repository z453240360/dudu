package com.dodo.marcket.wedget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.UFormat;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dodo.marcket.R;
import com.dodo.marcket.business.clasify.activity.ClasifyActivity;
import com.dodo.marcket.business.homepage.activity.HotActivity;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.homepage.activity.WebActivity;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.NumberUtils;
import com.dodo.marcket.utils.SharedPreferencesUtil;

public class MyWebView extends WebView{
    private Context mContext;
    public MyWebView(Context context) {
        this(context,null);
        this.mContext = context;
    }

    public MyWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        this.mContext = context;
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);

        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        //设置默认字体大小
//        int fontSize = (int) getResources().getDimension(R.dimen.dimen_14sp);
//        webSettings.setDefaultFontSize(fontSize);
    }


    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient=new WebChromeClient(){
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定",null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen","网页标题:"+title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            progressBar.setProgress(newProgress);
        }
    };



    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
//            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen","拦截url:"+url);
            if(url.equals("http://www.google.com/")){
                return true;//表示我已经处理过了
            }

            //跳转分类页面       duoduo://ProductCategory-87
            if (url.startsWith("duoduo://ProductCategory-")){
                String value = url.replace("duoduo://ProductCategory-","");
                if (value == null || value.equals("")) {
                    return true;
                }

                long productId = NumberUtils.string2Long(value);
                if (productId != 0L) {
                    Intent intentProduct = new Intent(mContext, ClasifyActivity.class);
                    intentProduct.putExtra("productId", productId);
                    mContext.startActivity(intentProduct);
                }
                return true;
            }

            //跳转商品详情
            if (url.startsWith("duoduo://Product-")){
                String value = url.replace("duoduo://Product-","");
                if (value == null || value.equals("")) {
                    return true;
                }

                long productId = NumberUtils.string2Long(value);
                if (productId != 0L) {
                    Intent intentProduct = new Intent(mContext, ProductDetailActivity.class);
                    intentProduct.putExtra("productId", productId);
                    mContext.startActivity(intentProduct);
                }
                return true;
            }


            //跳转促销页面
            if (url.startsWith("duoduo://Promotion-")){
                String value = url.replace("duoduo://Promotion-","");
                if (value == null || value.equals("")) {
                    return true;
                }

                long productId = NumberUtils.string2Long(value);
                if (productId != 0L) {
                    Intent intentProduct = new Intent(mContext, HotActivity.class);
                    intentProduct.putExtra("mId", productId);
                    mContext.startActivity(intentProduct);
                }
                return true;
            }

            if (url.contains("{token}")){
                String token = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
                if (token.equals("")){
                    Intent intentProduct = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intentProduct);
                    return true;
                }else {
                    url = url.replace("{token}",token);
                }
            }


            return super.shouldOverrideUrlLoading(view, url);
        }

    };

}
