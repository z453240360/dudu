package com.dodo.marcket.business.clasify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.wedget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class Product2Adapter extends RecyclerView.Adapter<Product2Adapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ProductBean> mDatas = new ArrayList<>();

    public Product2Adapter(Context context, List<ProductBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_product2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final ProductBean productBean = mDatas.get(position);

        holder.mFrame.getLayoutParams().height = ScreenUtil.getScreenWidth(mContext) / 2 - ScreenUtil.dip2px(mContext, 20);
        holder.mFrame.getLayoutParams().width = ScreenUtil.getScreenWidth(mContext) / 2 - ScreenUtil.dip2px(mContext, 20);

        boolean isCanBuy = false;
        if (productBean.getStock() == null) {
            isCanBuy = true;
        } else if (productBean.getStock() == 0) {
            isCanBuy = false;
        } else if (productBean.getStock() > 0) {
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

                if (productBean.isSelected()) {
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

                int specificationNumber = productBean.getSpecificationNumber();
                if (specificationNumber >= 1) {//多规格
                    if (mListener != null) {
                        mListener.onMutiSizeClicked(position);
                    }
                } else {
                    if (mListener != null) {//单规格
                        mListener.onAddClicked(position);
                    }
                }
            }
        });


        //设置选中图标
        if (productBean.isSelected()) {
            holder.mImgSelect.setImageResource(R.mipmap.xuanzhong1);
        } else {
            holder.mImgSelect.setImageResource(R.mipmap.xuanzhong2);
        }

        //左上角的超值特惠标签
        if (productBean.getTag() == null || productBean.getTag().equals("")) {
            holder.mImgSuperValue.setVisibility(View.GONE);
        } else {
            holder.mImgSuperValue.setVisibility(View.VISIBLE);
            ImageLoaders.displayImage(holder.mImgSuperValue, productBean.getTag());
        }


        holder.mTxtProductName.setText(productBean.getName());//名字
        holder.mTxtPrice.setText("¥" + productBean.getUnitPrice() + "/" + productBean.getUnit());//单价

        if (productBean.getMemo().equals("")) {
            holder.mTxtProductMsg.setVisibility(View.INVISIBLE);//描述
        } else {
            holder.mTxtProductMsg.setVisibility(View.VISIBLE);
            holder.mTxtProductMsg.setText(productBean.getMemo());//描述
        }

        holder.mTxtProductPrice.setText(productBean.getPrice() + "");//销售价格
        holder.mTxtPackage.setText(productBean.getPackaging());//包装
        ImageLoaders.displayImage(holder.mImgProductImg, productBean.getImage());

        //是否显示已经售完
        if (isCanBuy) {
            holder.mImgAdd.setVisibility(View.VISIBLE);
            holder.mImgSalesOut.setVisibility(View.GONE);
        } else {
            holder.mImgAdd.setVisibility(View.GONE);
            holder.mImgSalesOut.setVisibility(View.VISIBLE);
        }


        if (productBean.getStock() == null) {
            isCanBuy = true;
            holder.mTxtStock.setText("库存：9999");
        } else if (productBean.getStock() == 0) {
            isCanBuy = false;
            holder.mTxtStock.setText("库存：0");
        } else if (productBean.getStock() > 0){
            isCanBuy = true;
            holder.mTxtStock.setText("库存："+productBean.getStock());
        }

        holder.mTxtPackageWe.setText(productBean.getWeight()+" "+productBean.getUnit());
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
        @BindView(R.id.mImg_jia)
        ImageView mImgJia;
        @BindView(R.id.mImg_add)
        ImageView mImgAdd;
        @BindView(R.id.mImg_addCar)
        CircleImageView mImgAddCar;
        @BindView(R.id.mFrame)
        FrameLayout mFrame;
        @BindView(R.id.mTxt_packageWe)
        TextView mTxtPackageWe;
        @BindView(R.id.mTxt_stock)
        TextView mTxtStock;

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


    //
    public void getTotalPrice() {

    }

    //全选或者不全选
    public void setSelectAll(boolean isSelectAll) {
        if (mDatas.size() == 0) {
            return;
        }

        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).setSelected(isSelectAll);
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

        mDatas.get(pos).setSelected(select);
        notifyDataSetChanged();
    }


    //判断是否全部选中
    public boolean isSelectAll() {

        if (mDatas.size() == 0) {
            return false;
        }
        boolean midValue = true;

        for (int i = 0; i < mDatas.size(); i++) {

            boolean selected = mDatas.get(i).isSelected();
            if (selected != midValue) {
                return false;
            }

        }

        return true;
    }
}
