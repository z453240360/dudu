package com.dodo.marcket;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dodo.marcket.bean.SpecificationValuesBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.clasify.activity.ClasifyActivity;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.business.mine.adapter.MineAdapter;
import com.dodo.marcket.huizlogin.HuiLoginUtil;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.wedget.YhFlowLayout;
import com.nex3z.flowlayout.FlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.internal.operators.flowable.FlowableFromIterable;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.actTabLayout)
    TabLayout actTabLayout;
    @BindView(R.id.mRv)
    RecyclerView mRv;
    @BindView(R.id.mBtn_1)
    Button mBtn1;
    @BindView(R.id.mBtn_2)
    Button mBtn2;
    @BindView(R.id.mBtn_3)
    Button mBtn3;
    @BindView(R.id.mBtn_4)
    Button mBtn4;
    @BindView(R.id.flowlayout)
    YhFlowLayout flowlayout;
    @BindView(R.id.mFlow_dd)
    FlowLayout mFlowDd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        showDemoList();

        showShopCarActivity();
        displayUI();

        showDDFlow();
    }

    //显示liushi布局
    private void showDDFlow() {
        List<SpecificationValuesBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SpecificationValuesBean specificationValuesBean = new SpecificationValuesBean();
            specificationValuesBean.setCurrentSpecVal(true);
            specificationValuesBean.setName("我是测试数值");

            list.add(specificationValuesBean);
        }
        for (int i = 0; i < 10; i++) {
            SpecificationValuesBean specificationValuesBean = list.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.wedget_tag, null);

            TextView mTxtTag = (TextView) view.findViewById(R.id.mTxt_tag);
            mTxtTag.setText(specificationValuesBean.getName());

            if (specificationValuesBean.isCurrentSpecVal()){
                mTxtTag.setBackgroundResource(R.drawable.shape_lunkuo_line);
                mTxtTag.setTextColor(this.getResources().getColor(R.color.basicColor));
            }else {
                mTxtTag.setBackgroundResource(R.drawable.shape_lunkuo_line3);
                mTxtTag.setTextColor(this.getResources().getColor(R.color.black_333333));
            }

            final int finalI = i;
            mTxtTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            mFlowDd.addView(view);
        }

    }

    private void showDemoList() {
        List<String> mDates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDates.add("ss");
        }
        MineAdapter adapter = new MineAdapter(this, mDates);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
    }

    //购物车列表
    private void showShopCarActivity() {
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//
//        List<ProductBean> shopList = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            shopList.add(new ProductBean());
//        }
//        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this, shopList);
//        mRv.setLayoutManager(manager);
//        mRv.setAdapter(shoppingCartAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.mBtn_1, R.id.mBtn_2, R.id.mBtn_3, R.id.mBtn_4,R.id.mBtn_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBtn_1:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.mBtn_2:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.mBtn_3:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.mBtn_4:
                startActivity(new Intent(this, ClasifyActivity.class));
                break;

            case R.id.mBtn_5:
                //  需要使用Intent类的第2个参数指定Uri
//                Intent intent = new  Intent();
//                intent.setComponent(new ComponentName("io.dcloud.general","io.dcloud.general.activity.HuiZActivity"));
//
//                //  设置value属性值
//                intent.putExtra("appId" , "D3JBF6E2UXZBPRCSSV72LZG56BR52AK7NVTMNCUKBWNHMCWHPC2EJ2VH4DUCXTFMFKEKU3YL53TOM7IOYMTUHOWTSIBRQMZUDWHEMRWM5YXWSMDOKLV52IAQ7HRCZTGOBCF3WCD4VUYAFH5H4DS7XXBN72UPXXE55H52V64TFAU5QMACLP64LOTUMC6ZPQ5EQQBKKC7I4FL26QDXFCJDHUBJ6DKQCYVJ7LZSJVRWAIYOZCMZFUGUDFC4SLSZP53DQ3RAPGYKCAR2T5ANQNT2BXWXWW2SNXFVY7F5LEZNG66UZRMTGRNQ" );
//
//                startActivityForResult(intent,2000);

                //调用市民版登陆
                HuiLoginUtil.HZLogin(MainActivity.this,"AS1TG2MUT3WLVJYNPAOHYHM4TUJMC3S3GW7LW3VCTZWF6SGCJCFTQYQ7PTHUTRG7FF6IREQMRV7HZFUOQ6CTPUCSHBE7RVQNSHENNNDRE2TQ32YFQVMIJ7YDS5Z7PPLABXMUTDYNZELQNYC2JMZEC6L33TF2R2LIONLZUKLN36IMYXDK5YWURMEJBKFVV4B2UEEPYMMKLXJFTWVVE5YYBYKY7DX4L4LRBC6FXSMGHR6I3BBG32RAS6PCXSZY6JJ7VFCZHJ6XKHKTHD3HWX2BN6DSWG6FDOAZEDQNTEBVVRHWXAQ6FQZQ");


                break;

        }
    }


    private List<String> mDatas = new ArrayList<>();

    private void displayUI() {

        for (int i = 0; i < 20; i++) {
            mDatas.add("测试" + (i ^ 2));
        }

        for (int i = 0; i < mDatas.size(); i++) {
            final String data = mDatas.get(i);
            TextView tv = new TextView(this);
            tv.setText(data);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tv.setGravity(Gravity.CENTER);
            int paddingy = ScreenUtil.dip2px(this, 7);
            int paddingx = ScreenUtil.dip2px(this, 6);
            tv.setPadding(paddingx, paddingy, paddingx, paddingy);
            tv.setClickable(false);

            int shape = GradientDrawable.RECTANGLE;
            int radius = ScreenUtil.dip2px(this, 4);
            int strokeWeight = ScreenUtil.dip2px(this, 1);
            int stokeColor = getResources().getColor(R.color.black_333333);
            int stokeColor2 = getResources().getColor(R.color.basicColor);

//            GradientDrawable normalBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor, Color.WHITE);
//            GradientDrawable pressedBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor2, getResources().getColor(R.color.green));
//            StateListDrawable selector = DrawableUtils.getSelector(normalBg, pressedBg);
//            tv.setBackgroundDrawable(selector);
//            ColorStateList colorStateList = DrawableUtils.getColorSelector(getResources().getColor(R.color.text_color_gray), getResources().getColor(R.color.white));
//            tv.setTextColor(colorStateList);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            flowlayout.addView(tv);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HuiLoginUtil.REQUESTCODE) {
            switch (resultCode) {
                case 2000:
                    String result = data.getStringExtra("value");
                    mBtn1.setText(result);
                    Log.i("hz", "onActivityResult: 获取到返回值"+result);
                    break;
            }
        }
    }
}