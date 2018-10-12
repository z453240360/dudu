package com.dodo.marcket.business.clasify.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.business.clasify.adapter.ProductAdapter;
import com.dodo.marcket.business.clasify.constrant.SearchContract;
import com.dodo.marcket.business.clasify.presenter.SearchPresenter;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.homepage.adapter.ProductDetailAdapter;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.NumberUtils;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.photo.PopupWindowHelper;
import com.dodo.marcket.wedget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @BindView(R.id.mEd_search)
    ClearEditText mEdSearch;
    @BindView(R.id.mRv_search)
    RecyclerView mRvSearch;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;

    private List<ProductBean> mDates = new ArrayList<>();
    private LinearLayoutManager manager;
    private ProductAdapter adapter;
    private int page = 1;
    private int pageSize = 10;

    private View pickVIew;
    private List<SpecificationsBean> specificationsBeanList = new ArrayList<>();
    private ImageView mImg_productImg;
    private TextView mTxt_productName;
    private TextView mTxt_productMsg;
    private TextView mTxt_package;
    private TextView mTxt_price;
    private TextView mTxt_productPrice;
    private RecyclerView mRv_mutle;
    private ImageView mImg_jian;
    private ImageView mImg_jia;
    private TextView mTxt_num;
    private ProductDetailAdapter productDetailAdapter;
    private boolean isCanBuy = false;
    private Button mBtn_select;
    private long buyId;

    private PopupWindow popupWindow;


    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("搜索");
        initRv();
        initPickView();

        String search = getIntent().getStringExtra("search");

        if (search != null && !search.equals("")) {
            mEdSearch.setText(search);
            mPresenter.searchProduct(search, page, pageSize);
        }

        //搜索框文字改变及时请求
        mEdSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    return;
                }
                page = 1;
                mDates.clear();
                adapter.notifyDataSetChanged();
                mPresenter.searchProduct(s.toString(), page, pageSize);
            }
        });

        //软键盘点击确定按钮
        mEdSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))

                {

                    if ((mEdSearch.getText().toString().trim()).equals("")) {
                        return false;
                    }
                    page = 1;
                    mDates.clear();
                    adapter.notifyDataSetChanged();
                    mPresenter.searchProduct(mEdSearch.getText().toString().trim(), page, pageSize);
                    return true;

                }

                return false;

            }

        });


    }

    private void initRv() {
        manager = new LinearLayoutManager(mContext);
        adapter = new ProductAdapter(mContext, mDates);

        mRvSearch.setLayoutManager(manager);
        mRvSearch.setAdapter(adapter);


        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {//查看详情
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productId", mDates.get(parentPos).getId());
                startActivity(intent);
            }

            @Override
            public void onSelectedClick(int pos, boolean isSelectAll) {

            }

            @Override
            public void onJianClicked(int pos) {

            }

            @Override
            public void onJiaClicked(int pos) {

            }

            @Override
            public void onAddClicked(int pos) {
                if (!hastoken){
                    goToLogin();
                    return;
                }

                ProductBean productBean = mDates.get(pos);
                long id = productBean.getId();
                mPresenter.addProduct(1, new ProductParmsBean(id));
            }

            @Override
            public void onMutiSizeClicked(int pos) {
                if (!hastoken){
                    goToLogin();
                    return;
                }
//                ToastUtils.show(mContext, "多规格");
                mPresenter.getProductDetailById(mDates.get(pos).getId());
            }
        });
    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMsg(String msg, String type) {
        showErrorToast(msg);
    }

    @Override
    public void getSearchResult(List<ProductBean> productList) {
        if (productList == null || productList.size() == 0) {
            if (page==1){
                mLLNoDate.setVisibility(View.VISIBLE);
            }
            return;
        }else {
            mLLNoDate.setVisibility(View.GONE);
        }

        if (page==1){
            mDates.clear();
        }
        page++;
//        mDates.clear();
        mDates.addAll(productList);
        adapter.notifyDataSetChanged();
    }

    //添加商品结果
    @Override
    public void addProduct(boolean isAdd) {
        if (isAdd) {
            ToastUtils.show(mContext, "加入购物车成功");
        } else {
            ToastUtils.show(mContext, "加入购物车失败");
        }
    }


    //获取商品详情（用于多重规格的商品的展示）
    @Override
    public void getProductDetailById(ProductBean productBean) {
        if (productBean == null) {
            showErrorToast("获取商品信息错误");
            return;
        }

        if (productBean == null) {
            return;
        }

        isCanBuy = false;
        if (productBean.getStock() == null) {//不限制库存
            isCanBuy = true;
        } else if (productBean.getStock() == 0) {
            isCanBuy = false;
        } else if (productBean.getStock() > 0) {
            isCanBuy = true;
        }

//        //是否可以购买
//        if (isCanBuy) {
//            mBtn_select.setClickable(true);
//            mBtn_select.setBackgroundResource(R.color.basicColor);
//        } else {
//            mBtn_select.setClickable(false);
//            mBtn_select.setBackgroundResource(R.color.defalute);
//        }

        List<SpecificationsBean> specifications = productBean.getSpecifications();//商品规格
        List<String> productImages = productBean.getProductImages();//banner图片
        String name = productBean.getName();//商品名称
        String introduction = productBean.getIntroduction();//商品描述
        String memo = productBean.getMemo();//备注
        double price = productBean.getPrice();//价格
        String unit = productBean.getUnit();//单位
        double unitPrice = productBean.getUnitPrice();
        String image = productBean.getImage();
        String packaging = productBean.getPackaging();
        long id = productBean.getId();
        buyId = id;

        ImageLoaders.displayImage(mImg_productImg, image);
        mTxt_productName.setText(name);
        mTxt_productMsg.setText(memo);
        mTxt_price.setText("¥" + unitPrice + "/"+unit);
        mTxt_package.setText(packaging);
        mTxt_productPrice.setText("" + price + "");

        if (specifications == null || specifications.size() == 0) {
//            carId = mId;
        } else {
            specificationsBeanList.clear();
            specificationsBeanList.addAll(specifications);
            productDetailAdapter.notifyDataSetChanged();
        }

        shoePOP();
    }


    public void shoePOP() {
        popupWindow.showAtLocation(mActivity.getWindow().getDecorView(),
                Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopupWindowHelper.backgroundAlpha(mActivity, 1f);
            }
        });
        PopupWindowHelper.backgroundAlpha(mActivity, 0.7f);
    }


    private void initPickView() {
        pickVIew = LayoutInflater.from(mActivity).inflate(R.layout.layout_popup_goods, null, false);

        View view_pop = pickVIew.findViewById(R.id.view_pop);
        //产品图片
        mImg_productImg = pickVIew.findViewById(R.id.mImg_productImg);
        //名称
        mTxt_productName = pickVIew.findViewById(R.id.mTxt_productName);
        //备注
        mTxt_productMsg = pickVIew.findViewById(R.id.mTxt_productMsg);
        //包装
        mTxt_package = pickVIew.findViewById(R.id.mTxt_package);
        //价格
        mTxt_price = pickVIew.findViewById(R.id.mTxt_price);
        //售价
        mTxt_productPrice = pickVIew.findViewById(R.id.mTxt_productPrice);
        //标签列表
        mRv_mutle = pickVIew.findViewById(R.id.mRv_mutle);
        //加号
        mImg_jian = pickVIew.findViewById(R.id.mImg_jian);
        //减号
        mImg_jia = pickVIew.findViewById(R.id.mImg_jia);
        //数量
        mTxt_num = pickVIew.findViewById(R.id.mTxt_num);
        //选好了
        mBtn_select = pickVIew.findViewById(R.id.mBtn_select);

        popupWindow = PopupWindowHelper.createPopupWindow(pickVIew, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        productDetailAdapter = new ProductDetailAdapter(mContext, specificationsBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv_mutle.setAdapter(productDetailAdapter);
        mRv_mutle.setLayoutManager(linearLayoutManager);

        productDetailAdapter.setOnItemClickListener(new ProductDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, String specParam) {
                mPresenter.getProductBySize(buyId, specParam);

            }
        });

        //点击加号
        mImg_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTxt_num.getText().toString().trim();
                int i = NumberUtils.string2Int(s);
                i++;
                mTxt_num.setText(i + "");
            }
        });

        //点击减号
        mImg_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTxt_num.getText().toString().trim();
                int i = NumberUtils.string2Int(s);
                if (i <= 1) {
                    ToastUtils.show(mContext, "已经是最少了");
                    return;
                } else {
                    i--;
                    mTxt_num.setText(i + "");
                }
            }
        });

        //点击选好了
        mBtn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTxt_num.getText().toString().trim();
                int i = NumberUtils.string2Int(s);
                mPresenter.addProduct(i, new ProductParmsBean(buyId));
            }
        });
    }

}
