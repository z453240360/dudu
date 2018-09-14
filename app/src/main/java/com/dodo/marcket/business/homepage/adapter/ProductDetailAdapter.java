package com.dodo.marcket.business.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.bean.SpecificationValuesBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.utils.ScreenUtil;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<SpecificationsBean> mDatas = new ArrayList<>();

    public ProductDetailAdapter(Context context, List<SpecificationsBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_productdetail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        SpecificationsBean specificationsBean = mDatas.get(position);
        long pId = specificationsBean.getId();//父标签的Id
        String name = specificationsBean.getName();//父标签的名字

        holder.mTxt_nameProduct.setText(name);
        List<SpecificationValuesBean> specificationValues = specificationsBean.getSpecificationValues();//子标签的列表

        if (specificationValues != null && specificationValues.size() != 0) {
            displayUI(position, specificationValues, holder.yhFlowLayout, mContext);
        }

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private FlowLayout yhFlowLayout;
        private TextView mTxt_nameProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            yhFlowLayout = itemView.findViewById(R.id.mTag);
            mTxt_nameProduct = itemView.findViewById(R.id.mTxt_nameProduct);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int parentPos, String specParam);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    private void displayUI(int parentPso, final List<SpecificationValuesBean> mDatas, FlowLayout mFw, final Context context) {
        mFw.removeAllViews();
        for (int i = 0; i < mDatas.size(); i++) {
            TextView textView = buildLabel(context, mDatas.get(i), parentPso, i);
            mFw.addView(textView);
        }
    }

    private TextView buildLabel(Context context, final SpecificationValuesBean specificationValuesBean, final int parentPso, final int childPos) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(ScreenUtil.dip2px(context, 10), ScreenUtil.dip2px(context, 5), ScreenUtil.dip2px(context, 10), ScreenUtil.dip2px(context, 5));//4个参数按顺序分别是左上右下
        TextView textView = new TextView(context);

//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, ScreenUtil.sp2px(context,14));
        textView.setTextSize(ScreenUtil.sp2px(context, 6));
        textView.setText(specificationValuesBean.getName());

        if (specificationValuesBean.isCurrentSpecVal()) {
            textView.setBackgroundResource(R.drawable.shape_lunkuo_line);
            textView.setTextColor(context.getResources().getColor(R.color.basicColor));
        } else {
            textView.setBackgroundResource(R.drawable.shape_lunkuo_line3);
            textView.setTextColor(context.getResources().getColor(R.color.black_333333));
        }

        textView.setPadding(ScreenUtil.dip2px(context, 20), ScreenUtil.dip2px(context, 10), ScreenUtil.dip2px(context, 20), ScreenUtil.dip2px(context, 10));

        textView.setLayoutParams(layoutParams);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SpecificationValuesBean> specificationValues = mDatas.get(parentPso).getSpecificationValues();
                for (int i = 0; i < specificationValues.size(); i++) {
                    if (i==childPos){
                        specificationValues.get(i).setCurrentSpecVal(true);
                    }else {
                        specificationValues.get(i).setCurrentSpecVal(false);
                    }
                }


                if (mListener != null) {
                    mListener.onItemClick(parentPso, getSelect());
                }
            }
        });
        return textView;
    }

    //选中标签，获取规格值
    public String getSelect() {
        if (mDatas.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < mDatas.size(); i++) {
            SpecificationsBean specificationsBean = mDatas.get(i);
            long id = specificationsBean.getId();
            List<SpecificationValuesBean> specificationValues = specificationsBean.getSpecificationValues();

            long selectIdString = getSelectIdString(specificationValues);

            if (i == mDatas.size() - 1) {
                stringBuffer.append(id + ":" + selectIdString);
            } else {
                stringBuffer.append(id + ":" + selectIdString + ",");
            }

        }

        return stringBuffer.toString();
    }


    public long getSelectIdString(List<SpecificationValuesBean> specificationValues) {
        for (int i = 0; i < specificationValues.size(); i++) {
            if (specificationValues.get(i).isCurrentSpecVal()) {
                return specificationValues.get(i).getId();
            }
        }
        return 0L;

    }
}
