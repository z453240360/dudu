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
        holder.mTxtPrice.setText("¥" + productBean.getUnitPrice() + "/" + productBean.getUnit());//单价

        if (productBean.getMemo().equals("")) {
            holder.mTxtProductMsg.setVisibility(View.GONE);//描述
        } else {
            holder.mTxtProductMsg.setVisibility(View.VISIBLE);
            holder.mTxtProductMsg.setText(productBean.getMemo());//描述
        }

        holder.mTxtProductPrice.setText(productBean.getPrice() + "");//销售价格
        if (productBean.getPackaging().equals("")) {
            holder.mTxtPackage.setVisibility(View.GONE);
        } else {
            holder.mTxtPackage.setVisibility(View.VISIBLE);
            holder.mTxtPackage.setText(productBean.getPackaging());//包装
        }
        holder.mTxtPackageWe.setText("约" + productBean.getWeight() + " " + productBean.getUnit());
        ImageLoaders.displayImage(holder.mImgProductImg, productBean.getImage());
        holder.mTxtNumber.setText(" x " + productBean1.getQty());

        if (position == mDatas.size() - 1) {
            holder.mTxtLine.setVisibility(View.GONE);
        } else {
            holder.mTxtLine.setVisibility(View.VISIBLE);
        }

        double orderWeight = productBean1.getOrderWeight();//应发货
        double orderPrice = productBean1.getOrderPrice();//应收
        double realPrice = productBean1.getRealPrice();//实收
        double realWeight = productBean1.getRealWeight();//实发货
        String ow = "应发货：" + orderWeight + " " + productBean.getUnit();
        String rw = "   实发货：" + realWeight + " " + productBean.getUnit();
        String op = "   实付款：" + realPrice + " " + " 元";
        String rp = "   应收款：" + orderPrice + " " + " 元";

        if (mDatas.get(position).getOrderStatus().equals("配送中")||mDatas.get(position).getOrderStatus().equals("已完成")){
            holder.mLLPayMsg.setVisibility(View.VISIBLE);
            holder.mTxtPayMsg.setText(ow + rw + rp + op);
        }else {
            holder.mLLPayMsg.setVisibility(View.GONE);
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
        @BindView(R.id.mTxt_packageWe)
        TextView mTxtPackageWe;
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
        @BindView(R.id.mTxt_Number)
        TextView mTxtNumber;
        @BindView(R.id.mTxt_payMsg)
        TextView mTxtPayMsg;
        @BindView(R.id.mLL_payMsg)
        LinearLayout mLLPayMsg;
        @BindView(R.id.mLL_main)
        LinearLayout mLLMain;
        @BindView(R.id.mTxt_line)
        TextView mTxtLine;
        @BindView(R.id.mImg_superValue)
        ImageView mImgSuperValue;

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
