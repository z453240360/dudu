package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.DisCountBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class DisCountAdapter extends RecyclerView.Adapter<DisCountAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DisCountBean> mDatas = new ArrayList<>();

    public DisCountAdapter(Context context, List<DisCountBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_discount, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DisCountBean disCountBean = mDatas.get(position);
        double amount = disCountBean.getAmount();
        String anhao = disCountBean.getAnhao();
        String anHaoStatus = disCountBean.getAnHaoStatus();
        String anHaoType = disCountBean.getAnHaoType();
        String startDate = disCountBean.getStartDate();
        String endDate = disCountBean.getEndDate();
        holder.mTxtDiscountPrice.setText(amount + "");
        holder.mTxtDiscountDec.setText("满" + disCountBean.getLowLimit() + "可用");
        holder.mTxtDiscountTime.setText(startDate + " 至 " + endDate);

        String status = disCountBean.getStatus();//0 表示可用，其他不可用

        if (status.equals("0")) {//可用
            holder.mTxtDiscountUse.setClickable(true);
            holder.mTxtDiscountUse.setEnabled(true);
            holder.mLL_0.setBackgroundResource(R.mipmap.discount);
            holder.mTxtDiscountStatus.setBackgroundResource(R.drawable.shape_lunkuo_paint);
            holder.mTxtDiscountUse.setText("立即使用");
            holder.mTxtDiscountUse.setTextColor(Color.parseColor("#20d994"));
            holder.mTxtDiscountUse.setBackgroundResource(R.drawable.shape_lunkuo_line);
        } else {
            holder.mTxtDiscountUse.setClickable(false);
            holder.mTxtDiscountUse.setEnabled(false);
            holder.mLL_0.setBackgroundResource(R.mipmap.discount2);
            holder.mTxtDiscountStatus.setBackgroundResource(R.drawable.shape_lunkuo_paint4);
            holder.mTxtDiscountUse.setBackgroundResource(R.drawable.shape_lunkuo_line3);
            if (anHaoStatus.equals("USERD")) {
                holder.mTxtDiscountUse.setText("已使用");
            }else {
                holder.mTxtDiscountUse.setText("已过期");
            }
            holder.mTxtDiscountUse.setTextColor(Color.parseColor("#cccccc"));
        }

        if (anHaoType.equals("ALL_SHOP")){
            holder.mTxtDiscountStatus.setText("全场满减");
            holder.mTxtDiscountName.setText("满" + disCountBean.getLowLimit() + "减"+amount);
        }else {
            holder.mTxtDiscountStatus.setText("代金券");
            holder.mTxtDiscountName.setText(amount + " 元券");
        }


        holder.mTxtDiscountUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onUseClic(position);
                }
            }
        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_discountPrice)
        TextView mTxtDiscountPrice;
        @BindView(R.id.mTxt_discountDec)
        TextView mTxtDiscountDec;
        @BindView(R.id.mTxt_discountStatus)
        TextView mTxtDiscountStatus;
        @BindView(R.id.mTxt_discountName)
        TextView mTxtDiscountName;
        @BindView(R.id.mTxt_discountTime)
        TextView mTxtDiscountTime;
        @BindView(R.id.dotted_line)
        View dottedLine;
        @BindView(R.id.mTxt_discountUse)
        TextView mTxtDiscountUse;
        @BindView(R.id.mLL_1)
        LinearLayout mLL_1;
        @BindView(R.id.mLL_2)
        LinearLayout mLL_2;
        @BindView(R.id.mLL_0)
        LinearLayout mLL_0;
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
