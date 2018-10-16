package com.dodo.marcket.business.mine.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.BackBoxChildBean;
import com.dodo.marcket.bean.params.BackBoxParams;
import com.dodo.marcket.bean.params.BackBoxParamsBean;
import com.dodo.marcket.utils.DateUtil;
import com.dodo.marcket.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申请退框单列表页面
 */

public class BackBoxAdapter extends RecyclerView.Adapter<BackBoxAdapter.MyViewHolder> {



    private Context mContext;
    private LayoutInflater mInflater;
    private List<BackBoxBean> mDatas = new ArrayList<>();

    public BackBoxAdapter(Context context, List<BackBoxBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_backbox, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BackBoxBean backBoxBean = mDatas.get(position);
        String effectiveDate = backBoxBean.getEffectiveDate();//有效期
        String orderComplateDate = backBoxBean.getOrderComplateDate();//订单完成日期
        String orderDate = backBoxBean.getOrderDate();//下单时间
        int orderId = backBoxBean.getOrderId();//订单ID
        String orderSn = backBoxBean.getOrderSn();//订单号

        holder.mTxtBackBoxNumber.setText(orderSn);
        holder.mTxtBackBoxTime.setText(orderDate);
        holder.mTxtOverData.setText(effectiveDate);


        final List<BackBoxChildBean> backBoxChildBeans = backBoxBean.getBoxs();

        if (backBoxChildBeans != null && backBoxChildBeans.size() != 0) {
            BackBoxChildAdapter adapter = new BackBoxChildAdapter(mContext, backBoxChildBeans);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            holder.mRvBackBoxList.setAdapter(adapter);
            holder.mRvBackBoxList.setLayoutManager(manager);

            adapter.setOnItemClickListener(new BackBoxChildAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int pos) {

                }

                @Override
                public void onselectClick(int pos) {
                    if (backBoxChildBeans.get(pos).isSelect()) {
                        backBoxChildBeans.get(pos).setSelect(false);
                    }else {
                        backBoxChildBeans.get(pos).setSelect(true);
                    }
                    notifyDataSetChanged();
                }

                @Override
                public void onJiaClick(int pos) {
                    int backQty = backBoxChildBeans.get(pos).getBackQty();
                    int quantity = backBoxChildBeans.get(pos).getQuantity();

                    if (backQty<quantity) {
                        backQty++;
                        backBoxChildBeans.get(pos).setBackQty(backQty);
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onJianClick(int pos) {

                    int backQty = backBoxChildBeans.get(pos).getBackQty();
                    int quantity = backBoxChildBeans.get(pos).getQuantity();

                    if (backQty<quantity&&backQty>1) {
                        backQty--;
                        backBoxChildBeans.get(pos).setBackQty(backQty);
                        notifyDataSetChanged();
                    }
                }
            });

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
        @BindView(R.id.mTxt_overData)
        TextView mTxtOverData;
        @BindView(R.id.mTxt_overDesc)
        TextView mTxtOverDesc;
        @BindView(R.id.mTxt_line)
        TextView mTxtLine;

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

    //获取选中框信息
    public List<BackBoxParams> getSelectOrder(){
        if (mDatas.size()==0){
            return new ArrayList<BackBoxParams>();
        }

        List<BackBoxParams> backBoxParamsBeans = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            String orderSn = mDatas.get(i).getOrderSn();
            List<BackBoxChildBean> boxs = mDatas.get(i).getBoxs();
            if (boxs!=null&&boxs.size()!=0) {
                for (int j = 0; j < boxs.size(); j++) {
                    if (boxs.get(j).isSelect()) {
                        int boxId = boxs.get(j).getBoxId();
                        int backQty = boxs.get(j).getBackQty();
                        BackBoxParams params = new BackBoxParams();
                        params.setBoxId(boxId + "");
                        params.setQuantity(backQty);
                        params.setOrderSn(orderSn);
                        backBoxParamsBeans.add(params);
                    }
                }
            }
        }

        return backBoxParamsBeans;
    }

}
