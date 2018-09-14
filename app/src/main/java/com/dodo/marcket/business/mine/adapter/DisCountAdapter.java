package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        holder.mTxtDiscountPrice.setText(amount+"");
        holder.mTxtDiscountDec.setText("满"+disCountBean.getLowLimit()+"可用");

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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos, int childPos, String status, String villageCode, String villageName);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

}
