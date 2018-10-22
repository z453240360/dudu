package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.ChildAddressBean;
import com.dodo.marcket.bean.MyAddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class ChildAddressAdapter extends RecyclerView.Adapter<ChildAddressAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ChildAddressBean> mDatas = new ArrayList<>();

    public ChildAddressAdapter(Context context, List<ChildAddressBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_child_addaddress, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onItemClick(position,mDatas.get(position).getId(),mDatas.get(position).getName());
                }
            }
        });

        holder.mTxt_addAddress.setText(mDatas.get(position).getName());

        if (mDatas.get(position).isSelect()){
            holder.mTxt_addAddress.setTextColor(mContext.getResources().getColor(R.color.basicColor));
        }else {
            holder.mTxt_addAddress.setTextColor(mContext.getResources().getColor(R.color.black_333333));
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTxt_addAddress;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTxt_addAddress = itemView.findViewById(R.id.mTxt_addAddress);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos,long id,String name);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public void setItem(int pos){
        if (mDatas.size()==0){
            return;
        }

        for (int i = 0; i < mDatas.size(); i++) {
            if (i==pos){
                mDatas.get(i).setSelect(true);
            }else {
                mDatas.get(i).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }

}
