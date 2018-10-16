package com.dodo.marcket.business.clasify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.GoToPayBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class GiftPopAdapter extends RecyclerView.Adapter<GiftPopAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GoToPayBean.GiftItemInfosBean> mDatas = new ArrayList<>();

    public GiftPopAdapter(Context context, List<GoToPayBean.GiftItemInfosBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_gift_pop, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        GoToPayBean.GiftItemInfosBean payMethodBean = mDatas.get(position);
        String name = payMethodBean.getName();
        int id = payMethodBean.getId();

        boolean selected = payMethodBean.isSelect();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onItemClick(position,0);
                }
            }
        });
        if (selected){
            holder.mCheckBoxSelect.setChecked(true);
        }else {
            holder.mCheckBoxSelect.setChecked(false);
        }

        holder.mTxtDesc.setText(name);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mCheckBox_select)
        CheckBox mCheckBoxSelect;
        @BindView(R.id.mTxt_desc)
        TextView mTxtDesc;
        @BindView(R.id.mLL_line)
        LinearLayout mLLLine;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, int id);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void selectItem(int pos){
        if (mDatas.size()>0){
            for (int i = 0; i < mDatas.size(); i++) {
                if (pos==i){
                    mDatas.get(i).setSelect(true);
                }else {
                    mDatas.get(i).setSelect(false);
                }
            }
        }
        notifyDataSetChanged();
    }
}
