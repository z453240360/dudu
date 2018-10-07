package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.MyAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的地址列表
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyAddressBean> mDatas = new ArrayList<>();

    public AddressAdapter(Context context, List<MyAddressBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_address, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final MyAddressBean myAddressBean = mDatas.get(position);

        //我的名字
        holder.mTxtMyName.setText(myAddressBean.getConsignee());

        //我的电话
        holder.mTxtMyPhone.setText(myAddressBean.getPhone());

        //我的地址
        holder.mTxtMyAddress.setText(myAddressBean.getAreaInfo().getName() + myAddressBean.getAddress());

        //点击编辑按钮
        holder.mTxtMyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position, myAddressBean);
                }
            }
        });

        //设置默认
        if (myAddressBean.isDefaultX()) {
            holder.mCheckBox.setChecked(true);
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.GONE);
            holder.mCheckBox.setChecked(false);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtMyName;
        private TextView mTxtMyPhone;
        private TextView mTxtMyAddress;
        private TextView mTxtMyEdit;
        private CheckBox mCheckBox;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTxtMyName = itemView.findViewById(R.id.mTxt_myName);
            mTxtMyPhone = itemView.findViewById(R.id.mTxt_myPhone);
            mTxtMyAddress = itemView.findViewById(R.id.mTxt_myAddress);
            mTxtMyEdit = itemView.findViewById(R.id.mTxt_myEdit);
            mCheckBox = itemView.findViewById(R.id.mCheckBox);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, MyAddressBean myAddressBean);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
