package com.dodo.marcket.business.shoppingcar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.CartItemsBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.wedget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.MyViewHolder> {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<CartItemsBean> mDatas = new ArrayList<>();

    public PayAdapter(Context context, List<CartItemsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_pay_product, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CartItemsBean cartsBean = mDatas.get(position);
        CartItemsBean.ProductInfoBean productBean = cartsBean.getProductInfo();
        int quantity = cartsBean.getQuantity();
        boolean isCanBuy = true;
//        if (productBean.getStock() == null) {
//            isCanBuy = true;
//        } else if (productBean.getStock() == 0) {
//            isCanBuy = false;
//        } else if (productBean.getStock() > 0){
//            isCanBuy = true;
//        }

        if (position == mDatas.size() - 1) {
            holder.mTxtLine.setVisibility(View.GONE);
        } else {
            holder.mTxtLine.setVisibility(View.VISIBLE);
        }

        holder.mTxtQuality.setText("x "+quantity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });

        holder.mTxtProductName.setText("我是标题" + position);


        //减号点击
        holder.mImgJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onJianClicked(position);
                }
            }
        });

        //加号点击
        holder.mImgJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onJiaClicked(position);
                }
            }
        });

//        //左上角的超值特惠标签
//        if (productBean.getTag() == null || productBean.getTag().equals("")) {
//            holder.mImgSuperValue.setVisibility(View.GONE);
//        } else {
//            holder.mImgSuperValue.setVisibility(View.VISIBLE);
//            ImageLoaders.displayImage(holder.mImgSuperValue, productBean.getTag());
//        }


        holder.mTxtProductName.setText(productBean.getName());//名字
        holder.mTxtPrice.setText("¥" + productBean.getUnitPrice() + "/" + productBean.getUnit());//单价
        holder.mTxtProductMsg.setText(productBean.getMemo());//描述
        holder.mTxtProductPrice.setText(productBean.getPrice() + "");//销售价格

        if (productBean.getPackaging().equals("")){
            holder.mTxtPackage.setVisibility(View.GONE);
        }else {
            holder.mTxtPackage.setVisibility(View.VISIBLE);
            holder.mTxtPackage.setText(productBean.getPackaging());//包装
        }
        ImageLoaders.displayImage(holder.mImgProductImg, productBean.getImage());

        //是否显示已经售完
        if (isCanBuy) {
            holder.mImgSalesOut.setVisibility(View.GONE);
        } else {
            holder.mImgSalesOut.setVisibility(View.VISIBLE);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mImg_select)
        ImageView mImgSelect;
        @BindView(R.id.mLL_select)
        LinearLayout mLLSelect;
        @BindView(R.id.mImg_productImg)
        ImageView mImgProductImg;
        @BindView(R.id.mImg_salesOut)
        ImageView mImgSalesOut;
        @BindView(R.id.mTxt_productName)
        TextView mTxtProductName;
        @BindView(R.id.mTxt_productMsg)
        TextView mTxtProductMsg;
        @BindView(R.id.mTxt_package)
        TextView mTxtPackage;
        @BindView(R.id.ll_boxs)
        LinearLayout llBoxs;
        @BindView(R.id.mTxt_price)
        TextView mTxtPrice;
        @BindView(R.id.mTxt_productPriceName)
        TextView mTxtProductPriceName;
        @BindView(R.id.mTxt_productPrice)
        TextView mTxtProductPrice;
        @BindView(R.id.mImg_jian)
        ImageView mImgJian;
        @BindView(R.id.mTxt_num)
        TextView mTxtNum;
        @BindView(R.id.mImg_jia)
        ImageView mImgJia;
        @BindView(R.id.mImg_add)
        ImageView mImgAdd;
        @BindView(R.id.mTxt_quality)
        TextView mTxtQuality;
        @BindView(R.id.mLL_main)
        LinearLayout mLLMain;
        @BindView(R.id.mTxt_line)
        TextView mTxtLine;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos);

        void onSelectedClick(int pos, boolean isSelectAll);

        void onJianClicked(int pos);

        void onJiaClicked(int pos);

        void onAddClicked(int pos);

        void onMutiSizeClicked(int pos);
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
        void onClick(int pos);
    }


}
