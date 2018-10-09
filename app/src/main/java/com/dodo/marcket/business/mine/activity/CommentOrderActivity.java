package com.dodo.marcket.business.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.CommentBean;
import com.dodo.marcket.bean.params.CommentParamsBean;
import com.dodo.marcket.business.mine.adapter.CommentAdapter;
import com.dodo.marcket.business.mine.constrant.CommentOrderContract;
import com.dodo.marcket.business.mine.presenter.CommentOrderPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentOrderActivity extends BaseActivity<CommentOrderPresenter> implements CommentOrderContract.View {

//    @BindView(R.id.ratingbar1)
//    RatingBar ratingbar1;
//    @BindView(R.id.ratingbar2)
//    RatingBar ratingbar2;
    @BindView(R.id.mRv_pingjia)
    RecyclerView mRvPingjia;
    @BindView(R.id.mEd_msg)
    EditText mEdMsg;
    @BindView(R.id.mTxt_pingjia)
    TextView mTxtPingjia;

    private int productScore;//商品分
    private int expressScore;//快递分
    private String comment;//评论
    private List<CommentBean> commentBeans = new ArrayList<>();
    private CommentAdapter adapter;
    private LinearLayoutManager manager;

    private boolean isPost = false;//是否评论配送
    private boolean isShop = false;//是否评论商家
    private boolean isList = false;//是否评价商品
    private boolean isMsg = false;//是否填写评价信息

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_order;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("已完成订单评价");
        Bundle extras = getIntent().getExtras();

        commentBeans = (List<CommentBean>) extras.getSerializable("list");
        initStarView();
        initRv();
    }


    @Override
    public void showLoading(String content) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMsg(String msg, String type) {

    }

    //初始化评分控件
    private void initStarView() {
//        ratingbar1.setOnRatingChangeListener(//配送分
//                new RatingBar.OnRatingChangeListener() {
//                    @Override
//                    public void onRatingChange(int RatingCount) {
//                        expressScore = RatingCount;
//                        isPost = true;
//                        initSubmitView(isPost,isShop,isList,isMsg);
//                    }
//                }
//        );
//
//        ratingbar2.setOnRatingChangeListener(//商家分
//                new RatingBar.OnRatingChangeListener() {
//                    @Override
//                    public void onRatingChange(int RatingCount) {
//                        productScore = RatingCount;
//                        isShop = true;
//                        initSubmitView(isPost,isShop,isList,isMsg);
//                    }
//                }
//        );

        mEdMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.equals("")){
                    isMsg = false;
                }else {
                    isMsg = true;
                }

//                initSubmitView(isPost,isShop,isList,isMsg);
            }
        });
    }

    //初始化列表
    private void initRv() {
        adapter = new CommentAdapter(mContext, commentBeans);
        manager = new LinearLayoutManager(mContext);
        mRvPingjia.setAdapter(adapter);
        mRvPingjia.setLayoutManager(manager);

        adapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {

            }

            @Override
            public void onZanClicked(int pos) {//点赞
                commentBeans.get(pos).setScore(5);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLowClicked(int pos) {//差评
                commentBeans.get(pos).setScore(1);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initSubmitView(boolean isPost,boolean isShop, boolean isList, boolean isMsg){
//        if (!isList){//没有评价商品
//
//        }else if (!isShop){//没有评价商店
//
//        }else if (!isPost){//没有评价配送
//
//        }else if (!isMsg){//没有填写原因
//
//        }else {
//
//        }

        if (!isPost||!isShop){
            mTxtPingjia.setClickable(false);
            mTxtPingjia.setBackgroundResource(R.drawable.shape_lunkuo_paint4);
        }else {
            mTxtPingjia.setClickable(true);
            mTxtPingjia.setBackgroundResource(R.drawable.shape_lunkuo_paint);
        }
    }



    //评价订单
    @Override
    public void discussOrder(int id) {


    }

    //点击事件
    @OnClick(R.id.mTxt_pingjia)
    public void onViewClicked() {//提交评价

        CommentParamsBean commentParamsBean = new CommentParamsBean();

        String s = mEdMsg.getText().toString();//评价信息

//        if (!adapter.getCommetStatus()&&s.equals("")){
//            showErrorToast("请填写差评原因");
//            return;
//        }

        mPresenter.discussOrder(0);
    }



}
