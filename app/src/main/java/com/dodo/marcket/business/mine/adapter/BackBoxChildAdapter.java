package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.BackBoxChildBean;
import com.dodo.marcket.utils.NumberUtils;

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
        final BackBoxChildBean backBoxChildBean = mDatas.get(position);
        String boxCode = backBoxChildBean.getBoxCode();//筐编号
        int boxId = backBoxChildBean.getBoxId();//筐ID
        String boxName = backBoxChildBean.getBoxName();//筐名称
        double boxPrice = backBoxChildBean.getBoxPrice();//筐单价
        final int quantity = backBoxChildBean.getQuantity();//可退筐数量
        int backQty = backBoxChildBean.getBackQty();//想要退框的数量
        final boolean select = backBoxChildBean.isSelect();


        holder.mTxtBoxName.setText(boxName);
        holder.mTxtBoxPrice.setText("¥ "+boxPrice+" /个");
        holder.mTxtBoxQty.setText("x "+quantity);
        holder.mTxtBackBoxNum.setText(backQty+"");

        if (select){
            holder.mImgSelect.setImageResource(R.mipmap.xuanzhong1);
        }else {
            holder.mImgSelect.setImageResource(R.mipmap.xuanzhong2);
        }


        //点击加号
        holder.mImgJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onJiaClick(position);
                }
            }
        });

        //点击减号
        holder.mImgJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onJianClick(position);
                }
            }
        });

        //选中
        holder.mImgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onselectClick(position);
                }
            }
        });
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mImg_select)
        ImageView mImgSelect;
        @BindView(R.id.mTxt_boxName)
        TextView mTxtBoxName;
        @BindView(R.id.mTxt_boxPrice)
        TextView mTxtBoxPrice;
        @BindView(R.id.mTxt_boxQty)
        TextView mTxtBoxQty;
        @BindView(R.id.mImg_jian)
        ImageView mImgJian;
        @BindView(R.id.mTxt_backBoxNum)
        TextView mTxtBackBoxNum;
        @BindView(R.id.mImg_jia)
        ImageView mImgJia;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int postion);
        void onselectClick(int postion);
        void onJiaClick(int postion );
        void onJianClick(int postion);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public void getBackBoxList(){
        for (int i = 0; i <mDatas.size() ; i++) {

        }
    }

}
