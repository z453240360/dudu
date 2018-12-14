package com.dodo.marcket.business.homepage.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.MiaoShaBean;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页秒杀列表
 */

public class HomeMiaoShaAdapter extends RecyclerView.Adapter<HomeMiaoShaAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MiaoShaBean.ProductInfoListBean> mDatas = new ArrayList<>();

    public HomeMiaoShaAdapter(Context context, List<MiaoShaBean.ProductInfoListBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_home_miaosha, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MiaoShaBean.ProductInfoListBean bean = mDatas.get(position);
        String image = bean.getImage();
        ImageLoaders.displayImage(holder.mImgMiaoshao,image);


        holder.mTxtMiaoshaPrice.setText("¥ "+ bean.getPrice());
        holder.mTxtMiaoshaRealPrice.setText("¥ "+MathUtils.round(bean.getPromotionPrice(),2));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onItemClick(position,bean);
                }
            }
        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mImg_miaoshao)
        ImageView mImgMiaoshao;
        @BindView(R.id.mTxt_miaosha_realPrice)
        TextView mTxtMiaoshaRealPrice;
        @BindView(R.id.mTxt_miaosha_Price)
        TextView mTxtMiaoshaPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mTxtMiaoshaPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
//            setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, MiaoShaBean.ProductInfoListBean productInfoListBean);
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
        void onClick(int pos, MiaoShaBean.ProductInfoListBean productInfoListBean);
    }

}
