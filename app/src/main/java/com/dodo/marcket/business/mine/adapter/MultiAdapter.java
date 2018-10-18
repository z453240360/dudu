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
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    //有几种类型
    private static final String DEALWITH2 = "待付款";//待付款
    private static final String SEND2 = "待发货";//代发货
    private static final String DELIVER2 = "配送中";//配送中
    private static final String FINISH2 = "已完成";//已完成
    private static final String CANCEL2 = "已取消";//已取消
    private static final String CANCEL22 = "已退款";//已取消

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

        String type = mDatas.get(position).getOrderStatus() + "";

        switch (type) {
            case DEALWITH2://待付款
                return DEALWITH;
            case SEND2 + ""://代发货
                return SEND;
            case DELIVER2 + ""://配送中
                return DELIVER;
            case FINISH2 + "":
                return FINISH;//已完成
            case CANCEL2 + "":
            case CANCEL22 + "":
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
                return new CancelHolder(mInflater.inflate(R.layout.item_money, parent, false));
        }
        return new CancelHolder(mInflater.inflate(R.layout.item_money, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        OrderList orderList = mDatas.get(position);
        final int id = orderList.getId();//id
        int retCode = orderList.getRetCode();//保存订单返回码.0:成功,1:失败
        final String sn = orderList.getSn();//订单号
        String msg = orderList.getMsg();//保存订单返信息
        String createDate = orderList.getCreateDate();//下单日期
        String orderStatus = orderList.getOrderStatus();//订单状态
        double amount = MathUtils.round(orderList.getAmount(), 2);//订单金额
        double payAmount = MathUtils.round(orderList.getPayAmount(), 2);//付款金额
        double offsetAmount = MathUtils.round(orderList.getOffsetAmount(), 2);//调整金额，正数要向客户收钱,负数要退钱给客户
        List<OrderList.OrderItemsBean> orderItems = orderList.getOrderItems();//订单明细
        int size = 0;
        if (orderItems == null || orderItems.size() == 0) {
            orderItems = new ArrayList<>();
        } else {
            size = orderItems.size();
        }


        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        ImgAdapter adapter = new ImgAdapter(mContext, orderItems);

        adapter.setOnItemClickListener(new ImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {
                if (mListener != null) {
                    mListener.onItemClick(position, id, sn);
                }
            }
        });

        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case DEALWITH://待付款
                final DealwithHolder dealwithHolder = (DealwithHolder) holder;

                dealwithHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });
                dealwithHolder.mTxtOrderStatus.setText(orderStatus);
                dealwithHolder.mTxtOrderNumber.setText(sn);
                dealwithHolder.mTxtOrderTime.setText(createDate);
                dealwithHolder.mTxtOrderPrice.setText("¥" + amount + "");
                dealwithHolder.mRvOrderImg.setAdapter(adapter);
                dealwithHolder.mRvOrderImg.setLayoutManager(manager);
                dealwithHolder.mTxtOrderSum.setText("x " + size + "");
                //取消订单
                dealwithHolder.mTxtOrderCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.cancelOrder(id, position);
                        }
                    }
                });

                //马上付钱
                dealwithHolder.mTxtOrderPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.payOrder(sn, position);
                        }
                    }
                });

                dealwithHolder.mLLRv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });
                dealwithHolder.mRvOrderImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });

                break;

            case SEND://代发货
                SendHolder sendHolder = (SendHolder) holder;

                sendHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });

                sendHolder.mTxtOrderNumber.setText(sn);//订单号
                sendHolder.mTxtOrderTime.setText(createDate);//日期
                sendHolder.mTxtOrderPrice.setText("¥ " + payAmount + "");//实付
                sendHolder.mTxtOrderPayReal.setText(("¥ " +  offsetAmount) + "");//实收
                sendHolder.mTxtOrderPayReal2.setText("¥ " + amount);//应付
                sendHolder.mTxtOrderSum.setText("x " + size + "");
                sendHolder.mRvOrderImg.setAdapter(adapter);
                sendHolder.mRvOrderImg.setLayoutManager(manager);
                sendHolder.mTxtOrderStatus.setText(orderStatus);
                sendHolder.mRvOrderImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });
                //取消订单
                sendHolder.mTxtOrderCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.cancelOrder(id, position);
                        }
                    }
                });

                break;

            case DELIVER://配送中
                DeliverHolder deliverHolder = (DeliverHolder) holder;

                deliverHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });
                deliverHolder.mTxtOrderStatus.setText(orderStatus);
                deliverHolder.mTxtOrderNumber.setText(sn);//订单号
                deliverHolder.mTxtOrderTime.setText(createDate);//日期
                deliverHolder.mTxtOrderPrice.setText("¥ " + payAmount + "");//实付
                deliverHolder.mTxtOrderPayReal.setText("¥ " + ( offsetAmount) + "");//实收
                deliverHolder.mTxtOrderPayReal2.setText("¥ " + amount);//应付
                deliverHolder.mTxtOrderSum.setText("x " + size + "");
                deliverHolder.mRvOrderImg.setAdapter(adapter);
                deliverHolder.mRvOrderImg.setLayoutManager(manager);

                deliverHolder.mRvOrderImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });

                //取消订单
                deliverHolder.mTxtOrderCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.cancelOrder(id, position);
                        }
                    }
                });


                break;

            case FINISH://已完成
                FinishHolder finishHolder = (FinishHolder) holder;
                finishHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });
                finishHolder.mTxtOrderStatus.setText(orderStatus);
                finishHolder.mTxtOrderSum.setText("x " + size + "");
                finishHolder.mTxtOrderNumber.setText(sn);//订单号
                finishHolder.mTxtOrderTime.setText(createDate);//日期
                finishHolder.mTxtOrderPrice.setText("¥ " + payAmount + "");//实付
//                finishHolder.mTxtOrderPayReal.setText((payAmount + offsetAmount) + "");//实收
//                finishHolder.mTxtOrderPayReal2.setText("" + amount);//应付
                finishHolder.mRvOrderImg.setAdapter(adapter);
                finishHolder.mRvOrderImg.setLayoutManager(manager);

                finishHolder.mRvOrderImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });

                //再来一单
                finishHolder.mTxtOrderAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.againOrder(id, position);
                        }
                    }
                });

                //查看评价
                finishHolder.mTxtOrderDiscuss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.disOrder(id, position);
                        }
                    }
                });


                break;
            case CANCEL://已取消
                CancelHolder cancelHolder = (CancelHolder) holder;
                cancelHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });
                cancelHolder.mTxtOrderStatus.setText(orderStatus);
                cancelHolder.mTxtOrderSum.setText("x " + size + "");
                cancelHolder.mTxtOrderNumber.setText(sn);//订单号
                cancelHolder.mTxtOrderTime.setText(createDate);//日期
                cancelHolder.mTxtOrderPrice.setText("¥ " + payAmount + "");//实付
                cancelHolder.mTxtOrderPayReal.setText("¥ " + (offsetAmount) + "");//实收
                cancelHolder.mTxtOrderPayReal2.setText("¥ " + amount);//应付

                cancelHolder.mRvOrderImg.setAdapter(adapter);
                cancelHolder.mRvOrderImg.setLayoutManager(manager);

                cancelHolder.mRvOrderImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position, id, sn);
                        }
                    }
                });

                //再来订单
                cancelHolder.mTxtOrderCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.againOrder(id, position);
                        }
                    }
                });


                break;
        }
    }


    class FinishHolder extends RecyclerView.ViewHolder {

        TextView mTxtOrderNumber;
        TextView mTxtOrderTime;
        TextView mTxtOrderStatus;
        TextView mTxtOrderAgain;
        TextView mTxtOrderSum;
        RecyclerView mRvOrderImg;
        TextView mTxtOrderPrice;
        TextView mTxtOrderDiscuss;

        public FinishHolder(View itemView) {
            super(itemView);

            mTxtOrderAgain = itemView.findViewById(R.id.mTxt_orderAgainOrder);
            mTxtOrderNumber = itemView.findViewById(R.id.mTxt_orderNumber);
            mTxtOrderTime = itemView.findViewById(R.id.mTxt_orderTime);
            mTxtOrderStatus = itemView.findViewById(R.id.mTxt_orderStatus);
            mTxtOrderSum = itemView.findViewById(R.id.mTxt_orderSum);
            mTxtOrderPrice = itemView.findViewById(R.id.mTxt_orderPrice);
            mTxtOrderDiscuss = itemView.findViewById(R.id.mTxt_orderDiscuss);
            mRvOrderImg = itemView.findViewById(R.id.mRv_orderImg);
        }
    }

    class DeliverHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_orderNumber)
        TextView mTxtOrderNumber;
        @BindView(R.id.mTxt_orderTime)
        TextView mTxtOrderTime;
        @BindView(R.id.mTxt_orderStatus)
        TextView mTxtOrderStatus;
        @BindView(R.id.mRv_orderImg)
        RecyclerView mRvOrderImg;
        @BindView(R.id.mTxt_orderSum)
        TextView mTxtOrderSum;
        @BindView(R.id.mTxt_orderPrice)
        TextView mTxtOrderPrice;
        @BindView(R.id.mTxt_orderPayReal)
        TextView mTxtOrderPayReal;
        @BindView(R.id.mTxt_orderPayReal2)
        TextView mTxtOrderPayReal2;
        @BindView(R.id.mTxt_orderCancel)
        TextView mTxtOrderCancel;

        public DeliverHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class SendHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_orderNumber)
        TextView mTxtOrderNumber;
        @BindView(R.id.mTxt_orderTime)
        TextView mTxtOrderTime;
        @BindView(R.id.mTxt_orderStatus)
        TextView mTxtOrderStatus;
        @BindView(R.id.mRv_orderImg)
        RecyclerView mRvOrderImg;
        @BindView(R.id.mTxt_orderSum)
        TextView mTxtOrderSum;
        @BindView(R.id.mTxt_orderPrice)
        TextView mTxtOrderPrice;
        @BindView(R.id.mTxt_orderPayReal)
        TextView mTxtOrderPayReal;
        @BindView(R.id.mTxt_orderPayReal2)
        TextView mTxtOrderPayReal2;
        @BindView(R.id.mTxt_orderCancel)
        TextView mTxtOrderCancel;

        public SendHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class DealwithHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_orderNumber)
        TextView mTxtOrderNumber;
        @BindView(R.id.mTxt_orderTime)
        TextView mTxtOrderTime;
        @BindView(R.id.mTxt_orderStatus)
        TextView mTxtOrderStatus;
        @BindView(R.id.mRv_orderImg)
        RecyclerView mRvOrderImg;
        @BindView(R.id.mTxt_orderSum)
        TextView mTxtOrderSum;
        @BindView(R.id.mTxt_orderPrice)
        TextView mTxtOrderPrice;
        @BindView(R.id.mTxt_orderCancel)
        TextView mTxtOrderCancel;
        @BindView(R.id.mTxt_orderPay)
        TextView mTxtOrderPay;
        @BindView(R.id.mLL_rv)
        LinearLayout mLLRv;


        public DealwithHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CancelHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTxt_orderNumber)
        TextView mTxtOrderNumber;
        @BindView(R.id.mTxt_orderTime)
        TextView mTxtOrderTime;
        @BindView(R.id.mTxt_orderStatus)
        TextView mTxtOrderStatus;
        @BindView(R.id.mRv_orderImg)
        RecyclerView mRvOrderImg;
        @BindView(R.id.mTxt_orderSum)
        TextView mTxtOrderSum;
        @BindView(R.id.mTxt_orderPrice)
        TextView mTxtOrderPrice;
        @BindView(R.id.mTxt_orderPayReal)
        TextView mTxtOrderPayReal;
        @BindView(R.id.mTxt_orderPayReal2)
        TextView mTxtOrderPayReal2;
        @BindView(R.id.mTxt_orderCancel)
        TextView mTxtOrderCancel;


        public CancelHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, int orderId, String sn);

        void cancelOrder(int id, int postion);

        void payOrder(String sn, int postion);

        void disOrder(int id, int postion);

        void againOrder(int id, int postion);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
