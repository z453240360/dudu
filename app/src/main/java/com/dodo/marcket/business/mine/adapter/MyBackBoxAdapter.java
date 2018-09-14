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
import com.dodo.marcket.bean.MyBackBoxBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的退框单列表页面
 */

public class MyBackBoxAdapter extends RecyclerView.Adapter<MyBackBoxAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyBackBoxBean> mDatas = new ArrayList<>();

    public MyBackBoxAdapter(Context context, List<MyBackBoxBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_mybackbox, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_backBoxNumber)
        TextView mTxtBackBoxNumber;
        @BindView(R.id.mTxt_backBoxStatus)
        TextView mTxtBackBoxStatus;
        @BindView(R.id.mTxt_backBoxTime)
        TextView mTxtBackBoxTime;
        @BindView(R.id.mRv_myBackBoxChild)
        RecyclerView mRvMyBackBoxChild;
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
