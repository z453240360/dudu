package com.dodo.marcket.business.homepage.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.KeFuBean;
import com.dodo.marcket.business.homepage.adapter.KeFuAdapter;
import com.dodo.marcket.business.mine.constrant.KeFuContract;
import com.dodo.marcket.business.mine.presenter.KeFuPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeFuActivity extends BaseActivity<KeFuPresenter> implements KeFuContract.View, View.OnLayoutChangeListener {


    @BindView(R.id.mEd_keFuMsg)
    EditText mEdKeFuMsg;
    @BindView(R.id.mTxt_keFuSend)
    TextView mTxtKeFuSend;
    @BindView(R.id.mLL_send)
    LinearLayout mLLSend;
    @BindView(R.id.mRv_keFu)
    XRecyclerView mRvKeFu;
    private int page = 1;
    private int pageSize = 100;

    private KeFuAdapter keFuAdapter;
    private List<KeFuBean.ListBean> keFuList;
    private LinearLayoutManager manager;

    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_ke_fu;
    }

    @Override
    public void initPresenter() {
        //防止标题栏被软键盘顶出屏幕
        mActivity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        );
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("哆哆客服");
        initRv();
        mPresenter.getKeFuList(page, pageSize);


    }


    @Override
    public void showLoading(String content) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMsg(String msg, String type) {
        showErrorToast(msg);
        mRvKeFu.refreshComplete();
    }


    private void initRv() {
        keFuList = new ArrayList<>();
        keFuAdapter = new KeFuAdapter(mContext, keFuList);
        manager = new LinearLayoutManager(mContext);

        mRvKeFu.setLayoutManager(manager);
        mRvKeFu.setAdapter(keFuAdapter);
        mRvKeFu.setLoadingMoreEnabled(false);
        mRvKeFu.addOnLayoutChangeListener(this);
        mRvKeFu.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page++;
                mPresenter.getKeFuList(page, pageSize);
            }

            @Override
            public void onLoadMore() {

            }
        });

    }


    /**
     * 客服列表
     */
    @Override
    public void getKeFuList(KeFuBean keFuBean) {
        mRvKeFu.refreshComplete();
        if (keFuBean == null) {
            return;
        }

        List<KeFuBean.ListBean> list = keFuBean.getList();
        if (list == null || list.size() == 0) {
            return;
        }
        if (page == 1) {
            keFuList.clear();
        }

        keFuList.addAll(list);
        keFuAdapter.notifyDataSetChanged();
        mRvKeFu.scrollToPosition(keFuAdapter.getItemCount());
    }

    /**
     * 发送一条信息
     */
    @Override
    public void sendResult() {
        page = 1;
        mPresenter.getKeFuList(page, pageSize);
    }


    @OnClick(R.id.mTxt_keFuSend)
    public void onViewClicked() {
        String s = mEdKeFuMsg.getText().toString();
        if (s.equals("")) {
            showErrorToast("消息不能为空");
            return;
        }
        mEdKeFuMsg.setText("");
        mPresenter.sendKeFuMsg(s);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != -1 && oldBottom > bottom) {
            mRvKeFu.requestLayout();
            mRvKeFu.post(new Runnable() {
                @Override
                public void run() {
                    mRvKeFu.scrollToPosition(keFuAdapter.getItemCount());
                }
            });
        }
    }
}
