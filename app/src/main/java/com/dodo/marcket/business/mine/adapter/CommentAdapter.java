package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.CommentBean;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.OrderItemCommentParamsBean;
import com.dodo.marcket.bean.params.CommentParamsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 *
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<OrderItemCommentParamsBean> mDatas = new ArrayList<>();

    public CommentAdapter(Context context, List<OrderItemCommentParamsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_comment, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final OrderItemCommentParamsBean commentBean = mDatas.get(position);

        String name = commentBean.getProductName();
        long id = commentBean.getProductId();
//        int score = commentBean.getScore();
        boolean support = commentBean.isSupport();//是否点赞

        holder.mImg1.setOnClickListener(new View.OnClickListener() {//点赞
            @Override
            public void onClick(View v) {
                if (!commentBean.isCanClick()){
                    return;
                }
                if (mListener!=null){
                    mListener.onZanClicked(position);
                }
            }
        });

        holder.mImg2.setOnClickListener(new View.OnClickListener() {//差评
            @Override
            public void onClick(View v) {

                if (!commentBean.isCanClick()){
                    return;
                }

                if (mListener!=null){
                    mListener.onLowClicked(position);
                }
            }
        });

        holder.mTxtName.setText(name);

        if (support){
            holder.mImg1.setImageResource(R.mipmap.zan3);
            holder.mImg2.setImageResource(R.mipmap.zan1);
        }else {

            holder.mImg2.setImageResource(R.mipmap.zan2);
            holder.mImg1.setImageResource(R.mipmap.zan4);
        }

//        if (score==0){
//            holder.mImg1.setImageResource(R.mipmap.zan4);
//            holder.mImg2.setImageResource(R.mipmap.zan1);
//        }else if (score==1){
//            holder.mImg1.setImageResource(R.mipmap.zan2);
//            holder.mImg2.setImageResource(R.mipmap.zan4);
//        }else if (score==5){
//            holder.mImg1.setImageResource(R.mipmap.zan3);
//            holder.mImg2.setImageResource(R.mipmap.zan1);
//        }else {
//            holder.mImg1.setImageResource(R.mipmap.zan4);
//            holder.mImg2.setImageResource(R.mipmap.zan1);
//        }

    }


    //存在差评或者未评价情况
    public boolean getCommetStatus(){
        if (mDatas.size()==0){
            return false;
        }

        for (int i = 0; i < mDatas.size(); i++) {
            boolean score = mDatas.get(i).isSupport();
            if (!score){
                return false;
            }
        }
        return true;
    }

    //获取商品评价对象
    public List<OrderItemCommentParamsBean> getSupportPro(){
        if (mDatas.size()==0){
            return null;
        }

        List<OrderItemCommentParamsBean> list = new ArrayList<>();

        for (int i = 0; i < mDatas.size(); i++) {
            long id = mDatas.get(i).getProductId();
            boolean support = mDatas.get(i).isSupport();
            OrderItemCommentParamsBean paramsBean = new OrderItemCommentParamsBean();
            paramsBean.setProductId(id);
            paramsBean.setSupport(support);
            list.add(paramsBean);
        }
        return list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_name)
        TextView mTxtName;
        @BindView(R.id.mImg_1)
        ImageView mImg1;
        @BindView(R.id.mImg_2)
        ImageView mImg2;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int parentPos);
        void onZanClicked(int pos);
        void onLowClicked(int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }



}
