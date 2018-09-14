package com.dodo.marcket.business.clasify.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.FirstClassfyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class ClassifyChildAdapter extends RecyclerView.Adapter<ClassifyChildAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<FirstClassfyBean.SubProductCategoryBean> mDatas = new ArrayList<>();

    public ClassifyChildAdapter(Context context, List<FirstClassfyBean.SubProductCategoryBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_child_classfy, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onChildItemClick(position,mDatas.get(position).getId());
                }
            }
        });
        FirstClassfyBean.SubProductCategoryBean subProductCategoryBean = mDatas.get(position);
        String name = mDatas.get(position).getName();


        holder.mTxt_mineName.setText(name);

        if (subProductCategoryBean.isSelected()){
            holder.mTxt_mineName.setTextColor(mContext.getResources().getColor(R.color.basicColor));
            holder.mTxt_back.setTextColor(mContext.getResources().getColor(R.color.basicColor));
            holder.mTxt_back.setVisibility(View.VISIBLE);
        }else {
            holder.mTxt_mineName.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.mTxt_back.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.mTxt_back.setVisibility(View.GONE);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxt_mineName;
        private TextView mTxt_back;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTxt_mineName = itemView.findViewById(R.id.mTxt_mineName);
            mTxt_back = itemView.findViewById(R.id.mTxt_back);
        }
    }

    public interface OnChildItemClickListener {
        void onChildItemClick(int parentPos,int id);
    }

    private OnChildItemClickListener mListener;

    public void setOnItemClickListener(OnChildItemClickListener listener) {
        this.mListener = listener;
    }

}
