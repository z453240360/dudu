package com.dodo.marcket.business.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.business.clasify.adapter.Product2Adapter;
import com.dodo.marcket.business.clasify.adapter.ProductAdapter;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class HomeHotAdapter extends RecyclerView.Adapter<HomeHotAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ProducHeadBean> mDatas = new ArrayList<>();

    public HomeHotAdapter(Context context, List<ProducHeadBean> datas) {
        this.mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_homehot, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ProducHeadBean producHeadBeans = mDatas.get(position);
        String name = producHeadBeans.getName();
        String showType = producHeadBeans.getShowType();
        List<ProductBean> products = producHeadBeans.getProducts();
        if (products == null || products.size() == 0) {
            holder.mRvHomehot.setVisibility(View.GONE);
            return;
        } else {
            holder.mRvHomehot.setVisibility(View.VISIBLE);
        }

        holder.mTxtTitleHot.setText(name);

        if (showType.equals("1")) {//横排显示
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            ProductAdapter adapter = new ProductAdapter(mContext, products);
            holder.mRvHomehot.setLayoutManager(manager);
            holder.mRvHomehot.setAdapter(adapter);

            adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int parentPos) {

                    if (mListener != null) {
                        mListener.onItemClick(position, parentPos);
                    }
                }

                @Override
                public void onSelectedClick(int pos, boolean isSelectAll) {
                    if (mListener != null) {
                        mListener.onSelectedClick(position, pos, isSelectAll);
                    }
                }

                @Override
                public void onJianClicked(int pos) {
                    if (mListener != null) {
                        mListener.onJianClicked(position, pos);
                    }
                }

                @Override
                public void onJiaClicked(int pos) {
                    if (mListener != null) {
                        mListener.onJiaClicked(position, pos);
                    }
                }

                @Override
                public void onAddClicked(int pos) {//点击了添加购物车按钮（单一规格）

                    if (mListener != null) {
                        mListener.onAddClicked(position, pos);
                    }
                }

                @Override
                public void onMutiSizeClicked(int pos) {//点击了添加购物车按钮（多重规格）
                    if (mListener != null) {
                        mListener.onMutiSizeClicked(position, pos);
                    }

                }
            });

        } else {//竖排显示
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            Product2Adapter adapter = new Product2Adapter(mContext, products);
            holder.mRvHomehot.setLayoutManager(manager);
            holder.mRvHomehot.setAdapter(adapter);
            adapter.setOnItemClickListener(new Product2Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(int parentPos) {

                    if (mListener != null) {
                        mListener.onItemClick(position, parentPos);
                    }
                }

                @Override
                public void onSelectedClick(int pos, boolean isSelectAll) {
                    if (mListener != null) {
                        mListener.onSelectedClick(position, pos, isSelectAll);
                    }
                }

                @Override
                public void onJianClicked(int pos) {
                    if (mListener != null) {
                        mListener.onJianClicked(position, pos);
                    }
                }

                @Override
                public void onJiaClicked(int pos) {
                    if (mListener != null) {
                        mListener.onJiaClicked(position, pos);
                    }
                }

                @Override
                public void onAddClicked(int pos) {//点击了添加购物车按钮（单一规格）

                    if (mListener != null) {
                        mListener.onAddClicked(position, pos);
                    }
                }

                @Override
                public void onMutiSizeClicked(int pos) {//点击了添加购物车按钮（多重规格）
                    if (mListener != null) {
                        mListener.onMutiSizeClicked(position, pos);
                    }

                }
            });
        }

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mRv_homehot)
        RecyclerView mRvHomehot;
        @BindView(R.id.mTxt_title_hot)
        TextView mTxtTitleHot;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos, int childPos);

        void onSelectedClick(int parentPos, int childPos, boolean isSelectAll);

        void onJianClicked(int parentPos, int childPos);

        void onJiaClicked(int parentPos, int childPos);

        void onAddClicked(int parentPos, int childPos);

        void onMutiSizeClicked(int parentPos, int childPos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    private OnImgClickListener onImgClickListener;

    public void setOnImgClickListener(OnImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
    }

    public interface OnImgClickListener {
        void onClick(int pos, String villageCode, String villageName);
    }

}
