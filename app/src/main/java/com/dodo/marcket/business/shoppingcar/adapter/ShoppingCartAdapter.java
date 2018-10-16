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
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.params.PayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
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

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<CartItemsBean> mDatas = new ArrayList<>();

    public ShoppingCartAdapter(Context context, List<CartItemsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_shopping, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        final ProductBean productBean = mDatas.get(position).getProductInfo();
        CartItemsBean cartItemsBean = mDatas.get(position);
        String promotionName = cartItemsBean.getPromotionName();

        final CartItemsBean.ProductInfoBean productBean = cartItemsBean.getProductInfo();
        boolean isCanBuy = false;
        if (productBean.getStock() == null) {
            isCanBuy = true;
        } else if (productBean.getStock() == 0) {
            isCanBuy = false;
        } else if (productBean.getStock() > 0){
            isCanBuy = true;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });

        holder.mTxtProductName.setText("我是标题" + position);

        //选择标签被点击
        holder.mLLSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (productBean.isSelect()) {
                    setSelectOne(position, false);
                } else {
                    setSelectOne(position, true);
                }

                if (mListener != null) {
                    mListener.onSelectedClick(position, isSelectAll());
                }

            }
        });

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

        //添加购物车图标被点击
        holder.mImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAddClicked(position);
                }
            }
        });


        //设置选中图标
        if (productBean.isSelect()) {
            holder.mImgSelect.setImageResource(R.mipmap.xuanzhong1);
        } else {
            holder.mImgSelect.setImageResource(R.mipmap.xuanzhong2);
        }

        holder.mTxtProductName.setText(productBean.getName());//名字
        holder.mTxtPrice.setText("¥" + productBean.getUnitPrice() + "/" + productBean.getUnit());//单价
        holder.mTxtProductMsg.setText(productBean.getMemo());//描述
        holder.mTxtProductPrice.setText(productBean.getPrice() + "");//销售价格
        holder.mTxtPackage.setText(productBean.getPackaging());//包装
        holder.mTxtNum.setText(cartItemsBean.getQuantity()+"");
        ImageLoaders.displayImage(holder.mImgProductImg, productBean.getImage());
        if (isCanBuy){
            holder.mImgSalesOut.setVisibility(View.GONE);
        }else {
            holder.mImgSalesOut.setVisibility(View.VISIBLE);
        }

        if (promotionName.equals("")){
            holder.mLLHuodong.setVisibility(View.GONE);
        }else {
            holder.mLLHuodong.setVisibility(View.VISIBLE);
            holder.mTxtHuodong2.setText(promotionName);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mImg_superValue)
        ImageView mImgSuperValue;
        @BindView(R.id.mImg_select)
        ImageView mImgSelect;
        @BindView(R.id.mLL_select)
        LinearLayout mLLSelect;
        @BindView(R.id.mLL_huodong)
        LinearLayout mLLHuodong;

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
        @BindView(R.id.mImg_jia)
        ImageView mImgJia;
        @BindView(R.id.mImg_add)
        ImageView mImgAdd;
        @BindView(R.id.mImg_addCar)
        CircleImageView mImgAddCar;
        @BindView(R.id.mTxt_huodong2)
        TextView mTxtHuodong2;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * 接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(int parentPos);

        void onSelectedClick(int pos, boolean isSelectAll);

        void onJianClicked(int pos);

        void onJiaClicked(int pos);

        void onAddClicked(int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    //全选或者不全选
    public void setSelectAll(boolean isSelectAll) {
        if (mDatas.size() == 0) {
            return;
        }

        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).getProductInfo().setSelect(isSelectAll);
        }
        notifyDataSetChanged();
    }

    //选中一个
    public void setSelectOne(int pos, boolean select) {
        if (mDatas.size() == 0) {
            return;
        }

        if (pos > mDatas.size() - 1) {
            return;
        }

        mDatas.get(pos).getProductInfo().setSelect(select);
        notifyDataSetChanged();
    }


    //判断是否全部选中
    public boolean isSelectAll() {

        if (mDatas.size() == 0) {
            return false;
        }
        boolean midValue = true;

        for (int i = 0; i < mDatas.size(); i++) {

            boolean selected = mDatas.get(i).getProductInfo().isSelect();
            if (selected != midValue) {
                return false;
            }

        }

        return true;
    }

    //获取选中商品去结算
    public List<PayParamsFatherBean> getPayList(){
        if (mDatas.size()==0){
            return new ArrayList<PayParamsFatherBean>();
        }

        List<PayParamsFatherBean>  payList = new ArrayList<>();

        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getProductInfo().isSelect()){
                long id = mDatas.get(i).getProductInfo().getId();//产品ID
                int quantity = mDatas.get(i).getQuantity();//数量
                PayParamsBean payParamsBean = new PayParamsBean();
                payParamsBean.setId(id);

                PayParamsFatherBean payParamsFatherBean = new PayParamsFatherBean();
                payParamsFatherBean.setProductParam(payParamsBean);
                payParamsFatherBean.setQuantity(quantity);
                payList.add(payParamsFatherBean);
            }
        }

        return payList;
    }
}
