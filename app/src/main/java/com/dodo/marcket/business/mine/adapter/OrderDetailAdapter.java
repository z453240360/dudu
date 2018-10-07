package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.OrderDetailBean;
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

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<OrderDetailBean.OrderItemsBean> mDatas = new ArrayList<>();

    public OrderDetailAdapter(Context context, List<OrderDetailBean.OrderItemsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_orderdetail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        OrderDetailBean.OrderItemsBean productBean1 = mDatas.get(position);
        OrderDetailBean.OrderItemsBean.ProductInfoBean productBean = productBean1.getProductInfo();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });

        holder.mTxtProductName.setText(productBean.getName());//名字
        holder.mTxtPrice.setText("¥"+productBean.getUnitPrice()+"/"+productBean.getUnit());//单价

        if (productBean.getMemo().equals("")) {
            holder.mTxtProductMsg.setVisibility(View.GONE);//描述
        }else {
            holder.mTxtProductMsg.setVisibility(View.VISIBLE);
            holder.mTxtProductMsg.setText(productBean.getMemo());//描述
        }

        holder.mTxtProductPrice.setText(productBean.getPrice()+"");//销售价格
        holder.mTxtPackage.setText(productBean.getPackaging());//包装
        ImageLoaders.displayImage(holder.mImgProductImg,productBean.getImage());
        holder.mTxt_Number.setText(" x "+productBean.getCartNumber());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mImg_superValue)
        ImageView mImgSuperValue;
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
        @BindView(R.id.mTxt_package)
        TextView mTxtPackage;
        @BindView(R.id.mTxt_Number)
        TextView mTxt_Number;
        @BindView(R.id.mImg_jia)
        ImageView mImgJia;
        @BindView(R.id.mImg_add)
        ImageView mImgAdd;
        @BindView(R.id.mImg_addCar)
        CircleImageView mImgAddCar;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos);
        void onUseClic(int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

}
