package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.MyPointBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class MyPointAdapter extends RecyclerView.Adapter<MyPointAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyPointBean> mDatas = new ArrayList<>();

    public MyPointAdapter(Context context, List<MyPointBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_mypoint, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MyPointBean myPointBean = mDatas.get(position);


        holder.mTxtMyPoint.setText(myPointBean.getPoint()+"");//积分
        holder.mTxtMyPointDesc.setText(myPointBean.getType());//积分描述
        holder.mTxtMyPointTime.setText(myPointBean.getHappenDate());//时间

        if (position == mDatas.size() - 1) {
            holder.mLine.setVisibility(View.INVISIBLE);
        } else {
            holder.mLine.setVisibility(View.VISIBLE);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mTxt_myPoint)
        TextView mTxtMyPoint;
        @BindView(R.id.mTxt_myPointDesc)
        TextView mTxtMyPointDesc;
        @BindView(R.id.mTxt_myPointTime)
        TextView mTxtMyPointTime;
        @BindView(R.id.mLine)
        TextView mLine;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos, int childPos, String status, String villageCode, String villageName);
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
