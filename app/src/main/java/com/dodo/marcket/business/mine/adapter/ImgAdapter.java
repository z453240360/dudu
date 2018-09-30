package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.utils.ImageLoaders;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<OrderList.OrderItemsBean> mDatas = new ArrayList<>();

    public ImgAdapter(Context context, List<OrderList.OrderItemsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_order_img, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            holder.mTxt_mineName.setText(position+"嘟嘟生鲜");
        ImageLoaders.displayImage(holder.mImg_orderImg,mDatas.get(position).getProductInfo().getImage());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg_orderImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImg_orderImg = itemView.findViewById(R.id.mImg_orderImg);
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
