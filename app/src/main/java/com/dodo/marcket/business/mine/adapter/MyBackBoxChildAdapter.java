package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.MyBackBoxBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的退框单列表页面
 */

public class MyBackBoxChildAdapter extends RecyclerView.Adapter<MyBackBoxChildAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyBackBoxBean.ListBean.ItemsBean> mDatas = new ArrayList<>();

    public MyBackBoxChildAdapter(Context context, List<MyBackBoxBean.ListBean.ItemsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_mybackboxchild, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MyBackBoxBean.ListBean.ItemsBean itemsBean = mDatas.get(position);
        String orderDate = itemsBean.getOrderDate();
        int orderId = itemsBean.getOrderId();
        String orderSn = itemsBean.getOrderSn();

        holder.mTxtBackBoxNumber.setText(orderSn);
        holder.mTxtBackBoxTime.setText(orderDate);

        List<MyBackBoxBean.ListBean.ItemsBean.BoxsBean> boxs = itemsBean.getBoxs();
        if (boxs != null && boxs.size() != 0) {
            MyBackBoxChild2Adapter adapter = new MyBackBoxChild2Adapter(mContext,boxs);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            holder.mRvBackBoxList.setLayoutManager(manager);
            holder.mRvBackBoxList.setAdapter(adapter);
        }
        if (position==mDatas.size()-1){
            holder.mTxtLine.setVisibility(View.GONE);
        }else {
            holder.mTxtLine.setVisibility(View.VISIBLE);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mTxt_backBoxNumber)
        TextView mTxtBackBoxNumber;
        @BindView(R.id.mTxt_backBoxTime)
        TextView mTxtBackBoxTime;
        @BindView(R.id.mRv_backBoxList)
        RecyclerView mRvBackBoxList;
        @BindView(R.id.mTxt_line)
        TextView mTxtLine;

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


}
