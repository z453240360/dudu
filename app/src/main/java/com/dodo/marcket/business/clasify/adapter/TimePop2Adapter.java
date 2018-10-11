package com.dodo.marcket.business.clasify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.SelectPostTimeBean;
import com.dodo.marcket.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类页面显示购物车底部弹框
 */

public class TimePop2Adapter extends RecyclerView.Adapter<TimePop2Adapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SelectPostTimeBean.ItemBean> mDatas = new ArrayList<>();

    public TimePop2Adapter(Context context, List<SelectPostTimeBean.ItemBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_timepop, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onItemClick(position,mDatas.get(position).getName());
                }
            }
        });

        holder.mTxtDesc.setText(mDatas.get(position).getName());

        if (mDatas.get(position).isSelected()){
            holder.mTxtDesc.setTextSize(ScreenUtil.sp2px(mContext,8));
            holder.mTxtDesc.setTextColor(Color.parseColor("#333333"));
        }else {
            holder.mTxtDesc.setTextSize(ScreenUtil.sp2px(mContext,7));
            holder.mTxtDesc.setTextColor(Color.parseColor("#b9b9b9"));
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mTxt_name)
        TextView mTxtDesc;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, String select);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void selectItem(int pos){
        if (mDatas.size()>0){
            for (int i = 0; i < mDatas.size(); i++) {
                if (pos==i){
                    mDatas.get(i).setSelected(true);
                }else {
                    mDatas.get(i).setSelected(false);
                }
            }
        }
        notifyDataSetChanged();
    }

}
