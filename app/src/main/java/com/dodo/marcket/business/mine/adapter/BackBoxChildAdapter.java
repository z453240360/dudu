package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.BackBoxChildBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申请退框单列表页面
 */

public class BackBoxChildAdapter extends RecyclerView.Adapter<BackBoxChildAdapter.MyViewHolder> {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<BackBoxChildBean> mDatas = new ArrayList<>();

    public BackBoxChildAdapter(Context context, List<BackBoxChildBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_backboxchild, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_backBoxNumber)
        TextView mTxtBackBoxNumber;
        @BindView(R.id.mTxt_backBoxTime)
        TextView mTxtBackBoxTime;
        @BindView(R.id.mRv_backBoxList)
        RecyclerView mRvBackBoxList;
        @BindView(R.id.mTxt_overData)
        TextView mTxtOverData;
        @BindView(R.id.mTxt_overDesc)
        TextView mTxtOverDesc;
        @BindView(R.id.mTxt_line)
        TextView mTxtLine;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
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
