package com.dodo.marcket.business.clasify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.CartItemsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类页面显示购物车底部弹框
 */

public class CarPopAdapter extends RecyclerView.Adapter<CarPopAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<CartItemsBean> mDatas = new ArrayList<>();

    public CarPopAdapter(Context context, List<CartItemsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_carpop, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CartItemsBean cartItemsBean = mDatas.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }


            }
        });

        CartItemsBean.ProductInfoBean productInfo = cartItemsBean.getProductInfo();
        String name = productInfo.getName();
        final long id = productInfo.getId();
        double price = productInfo.getPrice();
        int quantity = cartItemsBean.getQuantity();

        holder.mTxtNum.setText(""+quantity);
        holder.mTxtCarPopName.setText(name);
        holder.mTxtCarPopPrice.setText(price+"");
        holder.mImgJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onJiaClick(position,id);
                }
            }
        });

        holder.mImgJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onJianClick(position,id);
                }
            }
        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_carPopName)
        TextView mTxtCarPopName;
        @BindView(R.id.mTxt_carPopPrice)
        TextView mTxtCarPopPrice;
        @BindView(R.id.mImg_jian)
        ImageView mImgJian;
        @BindView(R.id.mTxt_num)
        TextView mTxtNum;
        @BindView(R.id.mImg_jia)
        ImageView mImgJia;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos);

        void onJiaClick(int parentPos,long proId);

        void onJianClick(int pos,long proId);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
