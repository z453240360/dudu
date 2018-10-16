package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.utils.MathUtils;
import com.dodo.marcket.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的退框单列表页面
 */

public class MyBackBoxChild2Adapter extends RecyclerView.Adapter<MyBackBoxChild2Adapter.MyViewHolder> {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyBackBoxBean.ListBean.ItemsBean.BoxsBean> mDatas = new ArrayList<>();

    public MyBackBoxChild2Adapter(Context context, List<MyBackBoxBean.ListBean.ItemsBean.BoxsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_mybackboxchild2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        MyBackBoxBean.ListBean.ItemsBean.BoxsBean boxsBean = mDatas.get(position);

        holder.mTxtBackBoxPrice.setText("¥ "+ MathUtils.round(boxsBean.getTotalPrice(),2));
        holder.mTxtBoxName.setText(boxsBean.getBoxName());
        holder.mTxtBoxQty.setText("x "+boxsBean.getReturnQty()+"");
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_boxName)
        TextView mTxtBoxName;
        @BindView(R.id.mTxt_boxQty)
        TextView mTxtBoxQty;
        @BindView(R.id.mTxt_backBoxPrice)
        TextView mTxtBackBoxPrice;
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


}
