package com.dodo.marcket.business.clasify.adapter;

import android.content.Context;
import android.graphics.Color;
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

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<FirstClassfyBean> mDatas = new ArrayList<>();

    public ClassifyAdapter(Context context, List<FirstClassfyBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_homepage, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FirstClassfyBean firstClassfyBean = mDatas.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position, 0);
                }

                initSelect(position, 0);

            }
        });

        List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = firstClassfyBean.getSubProductCategory();

        if (firstClassfyBean.isFirstSelected()) {
            holder.mRv_list.setVisibility(View.VISIBLE);
            holder.mTxt_mineName.setTextColor(mContext.getResources().getColor(R.color.basicColor));
        } else {
            holder.mRv_list.setVisibility(View.GONE);
            holder.mTxt_mineName.setTextColor(mContext.getResources().getColor(R.color.black));
        }


        holder.mTxt_mineName.setText(firstClassfyBean.getName());


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        ClassifyChildAdapter adapter = new ClassifyChildAdapter(mContext, subProductCategory);
        holder.mRv_list.setAdapter(adapter);
        holder.mRv_list.setLayoutManager(manager);

        adapter.setOnItemClickListener(new ClassifyChildAdapter.OnChildItemClickListener() {

            @Override
            public void onChildItemClick(int parentPos, int id) {
                if (mListener != null) {
                    mListener.onChildClick(position, id);
                }

                initSelect(position, parentPos);
            }
        });

        if (position == mDatas.size() - 1) {
            holder.mTxt_line.setVisibility(View.INVISIBLE);
        } else {
            holder.mTxt_line.setVisibility(View.VISIBLE);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxt_mineName, mTxt_line;
        private RecyclerView mRv_list;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTxt_mineName = itemView.findViewById(R.id.mTxt_mineName);
            mTxt_line = itemView.findViewById(R.id.mTxt_line);
            mRv_list = itemView.findViewById(R.id.mRv_list);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos, int id);

        void onChildClick(int parentPos, int id);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    //选中
    public void initSelect(int pos, int childPos) {
        if (mDatas.size() == 0) {
            return;
        }
        for (int i = 0; i < mDatas.size(); i++) {
            if (i == pos) {
                mDatas.get(i).setFirstSelected(true);

                List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = mDatas.get(i).getSubProductCategory();

                if (subProductCategory.size() > 0 && childPos >= 0) {
                    for (int j = 0; j < subProductCategory.size(); j++) {
                        if (j == childPos) {
                            subProductCategory.get(j).setSelected(true);
                        } else {
                            subProductCategory.get(j).setSelected(false);
                        }
                    }
                } else {
                    for (int j = 0; j < subProductCategory.size(); j++) {
                        if (j == 0) {
                            subProductCategory.get(j).setSelected(true);
                        } else {
                            subProductCategory.get(j).setSelected(false);
                        }
                    }
                }

            } else {
                mDatas.get(i).setFirstSelected(false);

                List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = mDatas.get(i).getSubProductCategory();

                if (subProductCategory.size() > 0) {
                    for (int j = 0; j < subProductCategory.size(); j++) {
                        subProductCategory.get(j).setSelected(false);
                    }
                }
            }
        }

        notifyDataSetChanged();
    }
}
