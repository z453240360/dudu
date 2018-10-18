package com.dodo.marcket.business.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.CommentBean;
import com.dodo.marcket.bean.OrderItemCommentParamsBean;
import com.dodo.marcket.bean.params.CommentParamsBean;
import com.dodo.marcket.business.mine.adapter.CommentAdapter;
import com.dodo.marcket.business.mine.constrant.CommentOrderContract;
import com.dodo.marcket.business.mine.presenter.CommentOrderPresenter;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.wedget.RatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentOrderActivity extends BaseActivity<CommentOrderPresenter> implements CommentOrderContract.View {

    @BindView(R.id.ratingbar1)
    RatingBar ratingbar1;
    @BindView(R.id.ratingbar2)
    RatingBar ratingbar2;
    @BindView(R.id.mRv_pingjia)
    RecyclerView mRvPingjia;
    @BindView(R.id.mEd_msg)
    EditText mEdMsg;
    @BindView(R.id.mTxt_pingjia)
    TextView mTxtPingjia;

    private long orderId;
    private float productScore = -1;//商品分
    private float expressScore = -1;//快递分
    private String comment;//评论
    private List<OrderItemCommentParamsBean> commentBeans = new ArrayList<>();
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
        orderId = extras.getLong("orderId");
        commentBeans = (List<OrderItemCommentParamsBean>) extras.getSerializable("list");
        initStarView();
        initRv();
        mPresenter.getDiscussOrder(orderId);

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
    }

    //初始化评分控件
    private void initStarView() {
        ratingbar1.setOnRatingChangeListener(//配送分
                new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(float RatingCount) {
                        expressScore = RatingCount;
                        isPost = true;
                        initSubmitView(isPost, isShop, isList, isMsg);
                    }
                }
        );

        ratingbar2.setOnRatingChangeListener(//商家分
                new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(float RatingCount) {
                        productScore = RatingCount;
                        isShop = true;
                        initSubmitView(isPost, isShop, isList, isMsg);
                    }
                }
        );

        mEdMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.equals("")) {
                    isMsg = false;
                } else {
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
                commentBeans.get(pos).setSupport(true);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLowClicked(int pos) {//差评
                commentBeans.get(pos).setSupport(false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initSubmitView(boolean isPost, boolean isShop, boolean isList, boolean isMsg) {
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

        if (!isPost || !isShop) {
            mTxtPingjia.setClickable(false);
            mTxtPingjia.setBackgroundResource(R.drawable.shape_lunkuo_paint4);
        } else {
            mTxtPingjia.setClickable(true);
            mTxtPingjia.setBackgroundResource(R.drawable.shape_lunkuo_paint);
        }
    }


    //提交订单评价
    @Override
    public void discussOrder(int id) {
        ToastUtils.show(mContext, "评价成功");
        finish();
//        mPresenter.getDiscussOrder(orderId);
    }

    //获取订单评价详情
    @Override
    public void getDiscussOrder(CommentParamsBean commentParamsBean) {
        if (commentParamsBean == null) {//说明没有订单评价
            ratingbar1.setmClickable(true);
            ratingbar2.setmClickable(true);
            mTxtPingjia.setVisibility(View.VISIBLE);
            mEdMsg.setFocusable(true);
            return;
        }


        List<OrderItemCommentParamsBean> orderItemCommentParams1 = commentParamsBean.getOrderItemComments();
        if (orderItemCommentParams1 == null || orderItemCommentParams1.size() == 0) {
            ratingbar1.setmClickable(true);
            ratingbar2.setmClickable(true);
            mTxtPingjia.setVisibility(View.VISIBLE);
            mEdMsg.setFocusable(true);

            return;
        }

        String comment = commentParamsBean.getComment();//评价信息
        expressScore = commentParamsBean.getExpressScore();
        productScore = commentParamsBean.getProductScore();
        long orderId = commentParamsBean.getOrderId();

        if (comment.equals("")) {
            mEdMsg.setText("暂无评价");
        } else {
            mEdMsg.setText(comment);
        }
        mEdMsg.setFocusable(false);
        ratingbar1.setStar(expressScore);
        ratingbar2.setStar(productScore);
        ratingbar2.setmClickable(false);
        ratingbar1.setmClickable(false);
        mTxtPingjia.setVisibility(View.GONE);

        List<OrderItemCommentParamsBean> orderItemCommentParams = commentParamsBean.getOrderItemCommentParams();

        if (orderItemCommentParams == null || orderItemCommentParams.size() == 0) {
            return;
        }

        for (int i = 0; i < orderItemCommentParams.size(); i++) {
            orderItemCommentParams.get(i).setCanClick(false);
        }
        commentBeans.clear();
        commentBeans.addAll(orderItemCommentParams);
        adapter.notifyDataSetChanged();

    }

    //点击事件
    @OnClick(R.id.mTxt_pingjia)
    public void onViewClicked() {//提交评价

        if (expressScore == -1) {
            showErrorToast("请对配送打分");
            return;
        }

        if (productScore == -1) {
            showErrorToast("请对商品打分");
            return;
        }


        List<OrderItemCommentParamsBean> supportPro = adapter.getSupportPro();
        if (supportPro == null || supportPro.size() == 0) {
            showErrorToast("商品异常");
            return;
        }
        comment = mEdMsg.getText().toString();//评价信息

        if (!adapter.getCommetStatus() && comment.equals("")) {//有差评情况，须填写原因
            showErrorToast("请填写差评原因");
            return;
        }

        CommentParamsBean commentParamsBean = new CommentParamsBean();
        commentParamsBean.setOrderId(orderId);
        commentParamsBean.setOrderItemCommentParams(supportPro);
        commentParamsBean.setExpressScore((int) expressScore);
        commentParamsBean.setProductScore((int) productScore);
        commentParamsBean.setComment(comment);

        mPresenter.discussOrder(commentParamsBean);//提交评价
    }


}
