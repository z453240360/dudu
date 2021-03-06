package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.utils.MathUtils;

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
    private List<MyBackBoxBean.ListBean> mDatas = new ArrayList<>();

    public MyBackBoxAdapter(Context context, List<MyBackBoxBean.ListBean> datas) {
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
        MyBackBoxBean.ListBean bean = mDatas.get(position);
        String sn = bean.getSn();
        double totlePrice = bean.getTotlePrice();
        String createDate = bean.getCreateDate();
        String status = bean.getStatus();
        int totleQty = bean.getTotleQty();

        holder.mTxtBackBoxStatus.setText(status);
        holder.mTxtBackBoxNumber.setText(sn);
        holder.mTxtBackBoxTime.setText(createDate);
        holder.mTxtTotalPrice.setText(MathUtils.round(totlePrice,2)+"");
        List<MyBackBoxBean.ListBean.ItemsBean> items = bean.getItems();
        if (items != null && items.size() != 0) {
            MyBackBoxChildAdapter adapter = new MyBackBoxChildAdapter(mContext, items);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            holder.mRvMyBackBoxChild.setLayoutManager(manager);
            holder.mRvMyBackBoxChild.setAdapter(adapter);
        }


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
        @BindView(R.id.mTxt_totalPrice)
        TextView mTxtTotalPrice;

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
