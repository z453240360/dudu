package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.OrderList;

import java.util.List;

/**
 * ***********************************************************
 * author: dd
 * 订单页面
 * *************************************************************
 */
public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public LayoutInflater mInflater;
    public List<OrderList> mDatas;
    //有几种类型
    private static final int DEALWITH = 1;//待付款
    private static final int SEND = 2;//代发货
    private static final int DELIVER = 3;//配送中
    private static final int FINISH = 4;//已完成
    private static final int CANCEL = 5;//已取消


    public MultiAdapter(Context context, List<OrderList> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    //返回当前位置的类型
    @Override
    public int getItemViewType(int position) {

        String type = mDatas.get(position).getStatus()+"";

        switch (type){
            case DEALWITH+""://待付款
                return DEALWITH;
            case SEND+""://代发货
                return SEND;
            case DELIVER+""://配送中
                return DELIVER;
            case FINISH+"":
                return FINISH;//已完成
            case CANCEL+"":
                return CANCEL;//已取消
        }
       return FINISH;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DEALWITH://待付款
                return new DealwithHolder(mInflater.inflate(R.layout.item_dealwith, parent, false));
            case SEND://代发货
                return new SendHolder(mInflater.inflate(R.layout.item_send, parent, false));
            case DELIVER://配送中
                return new DeliverHolder(mInflater.inflate(R.layout.item_deliver, parent, false));
            case FINISH://已完成
                return new FinishHolder(mInflater.inflate(R.layout.item_finish, parent, false));
            case CANCEL://已取消
                return new FinishHolder(mInflater.inflate(R.layout.item_money, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {



        int itemViewType = getItemViewType(position);
        switch (itemViewType) {

            case DEALWITH://待付款
                DealwithHolder dealwithHolder = (DealwithHolder) holder;

                dealwithHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                break;

            case SEND://代发货
                SendHolder sendHolder = (SendHolder) holder;

                sendHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;

            case DELIVER://配送中
                DeliverHolder deliverHolder = (DeliverHolder) holder;

                deliverHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;

            case FINISH://已完成
                FinishHolder finishHolder = (FinishHolder) holder;
                finishHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case CANCEL://已取消
                CancelHolder cancelHolder = (CancelHolder) holder;
                cancelHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }


    class FinishHolder extends RecyclerView.ViewHolder {
        public FinishHolder(View itemView) {
            super(itemView);
        }
    }

    class DeliverHolder extends RecyclerView.ViewHolder {
        public DeliverHolder(View itemView) {
            super(itemView);
        }
    }


    class SendHolder extends RecyclerView.ViewHolder {
        public SendHolder(View itemView) {
            super(itemView);
        }
    }

    class DealwithHolder extends RecyclerView.ViewHolder {
        public DealwithHolder(View itemView) {
            super(itemView);

        }
    }

    class CancelHolder extends RecyclerView.ViewHolder {
        public CancelHolder(View itemView) {
            super(itemView);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, View view);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
