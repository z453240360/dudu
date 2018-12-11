package com.dodo.marcket.business.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.KeFuBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客服列表
 */

public class KeFuAdapter extends RecyclerView.Adapter<KeFuAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<KeFuBean.ListBean> mDatas = new ArrayList<>();

    public KeFuAdapter(Context context, List<KeFuBean.ListBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_kefu, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        KeFuBean.ListBean keFuBean = mDatas.get(position);

        holder.mTxtMeKeFuTime.setText(keFuBean.getProblemDate());
        holder.mTxtKeFuAnswer.setText(keFuBean.getAnswer());
        holder.mTxtMeKeFuTime2.setText(keFuBean.getAnswerDate());
        holder.mTxtMyQuestion.setText(keFuBean.getProblem());

        if (keFuBean.getAnswer().equals("")){
            holder.mLLKefu.setVisibility(View.GONE);
        }else {
            holder.mLLKefu.setVisibility(View.VISIBLE);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_meKeFuTime)
        TextView mTxtMeKeFuTime;
        @BindView(R.id.mTxt_myQuestion)
        TextView mTxtMyQuestion;
        @BindView(R.id.mLL_me)
        LinearLayout mLLMe;
        @BindView(R.id.mTxt_meKeFuTime2)
        TextView mTxtMeKeFuTime2;
        @BindView(R.id.mTxt_keFuAnswer)
        TextView mTxtKeFuAnswer;
        @BindView(R.id.mLL_kefu)
        LinearLayout mLLKefu;
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


    private OnImgClickListener onImgClickListener;

    public void setOnImgClickListener(OnImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
    }

    public interface OnImgClickListener {
        void onClick(int pos, String villageCode, String villageName);
    }

}
