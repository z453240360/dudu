package com.dodo.marcket.base;

import android.app.Activity;
import android.content.Context;


import com.dodo.marcket.http.apiService.ApiModel;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.http.utils.BResponse;
import com.dodo.marcket.http.utils.RxHelper;
import com.dodo.marcket.utils.ActivityLifeCycleEvent;
import com.dodo.marcket.utils.SharedPreferencesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;


/**
 * 创建日期：2017/12/8 on 15:30
 */

public abstract class BasePresenter<T extends BaseView> {
    protected Context mContext;
    protected T mView;
    protected ApiModel apiModel;
    public static String mToken;
    //销毁时退出异步任务
    protected CompositeSubscription mSubscriptions;
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    public void init(T v) {

        apiModel = ApiModel.create();
        if (v instanceof BaseFragment) {
            mContext = ((BaseFragment) v).mContext;
        }
        if (v instanceof Activity) {
            mContext = (Context) v;
        }
        this.mView = v;
        this.onStart();
        mToken = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
    }

    public void onStart() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
    }

    public void onResume() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.RESUME);

    }


    public void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        if (mSubscriptions != null && mSubscriptions.hasSubscriptions()) {
            mSubscriptions.unsubscribe();
        }
    }


    public void onDestroy() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
        if (mSubscriptions != null && mSubscriptions.hasSubscriptions()) {
            mSubscriptions.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        //数据预处理
        Observable.Transformer<BResponse<Object>, Object> result = RxHelper.handleResult(ActivityLifeCycleEvent
                .DESTROY, lifecycleSubject);
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).compose(result)
                .subscribe(subscriber));
    }

    /**
     * 上传图片封装
     *
     * @param key
     * @param fileName
     * @return
     */
    public MultipartBody.Part putFile(String key, String fileName) {
        //构建要上传的文件
        File file = new File(fileName);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }

    public List<MultipartBody.Part> putFiles(String key, List<String> fileName) {
        //构建要上传的文件
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < fileName.size(); i++) {
            File file = new File(fileName.get(i));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData(key, file.getName(), requestFile);
            parts.add(body);
        }

        return parts;
    }

    public void setToken(){
        mToken = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
    }
}
