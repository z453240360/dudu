package com.dodo.marcket.business.clasify.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.CartItemsBean;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.params.PayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.clasify.adapter.CarPopAdapter;
import com.dodo.marcket.business.clasify.adapter.ClassifyAdapter;
import com.dodo.marcket.business.clasify.constrant.ClassifyFragmentContract;
import com.dodo.marcket.business.clasify.presenter.ClassifyFragmentPresenter;
import com.dodo.marcket.business.shoppingcar.activity.GoToPayActivity;
import com.dodo.marcket.business.shoppingcar.fragment.BuyListFragment;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.photo.PopupWindowHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ClassifyFragment extends BaseFragment<ClassifyFragmentPresenter> implements ClassifyFragmentContract.View {


    public static ClassifyFragment classifyFragment;
    @BindView(R.id.mEd_search)
    TextView mEdSearch;
    @BindView(R.id.mLL_search)
    LinearLayout mLLSearch;
    @BindView(R.id.mRv_first)
    RecyclerView mRvFirst;
    @BindView(R.id.mFrame_second)
    FrameLayout mFrameSecond;
    @BindView(R.id.mTxt_sendPrice)
    TextView mTxtSendPrice;
    @BindView(R.id.mLL_sendPrice)
    LinearLayout mLLSendPrice;
    @BindView(R.id.mImg_huishou)
    ImageView mImgHuishou;
    @BindView(R.id.mTxt_selectAll)
    TextView mTxtSelectAll;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    @BindView(R.id.mTxt_carTotalMoney)
    TextView mTxtCarTotalMoney;
    @BindView(R.id.mTxt_carBoxMoney)
    TextView mTxtCarBoxMoney;
    @BindView(R.id.mTxt_carPostMoney)
    TextView mTxtCarPostMoney;
    @BindView(R.id.mLL_carPost)
    LinearLayout mLLCarPost;
    @BindView(R.id.mTxt_pay)
    TextView mTxtPay;
    @BindView(R.id.mLL_showPrice)
    LinearLayout mLLShowPrice;
    @BindView(R.id.mTxt_carClear)
    TextView mTxtCarClear;
    @BindView(R.id.mTxt_carDelete)
    TextView mTxtCarDelete;
    @BindView(R.id.mLL_showEdit)
    LinearLayout mLLShowEdit;
    @BindView(R.id.mLL_bottomView)
    LinearLayout mLLBottomView;
    @BindView(R.id.mTxt_carNum)
    TextView mTxtCarNum;


    private List<FirstClassfyBean> mDates = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<CartItemsBean> carList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private Fragment lastFragment;
    private LinearLayoutManager firstManager;
    private ClassifyAdapter firstAdapter;
    private View pickVIew;
    private PopupWindow popupWindow;
    private RecyclerView mRv_carPop;
    private CarPopAdapter carPopAdapter;
    private double minPrice;
    private LinearLayout mLL_delete;


    public static ClassifyFragment getInstance() {
        if (classifyFragment == null)
            classifyFragment = new ClassifyFragment();
        return classifyFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

        initRvFirst();
        initPickView();
        mPresenter.getFirstKind();//获取一级分类列表

        if (hastoken) {
            mLLBottomView.setVisibility(View.VISIBLE);
            mPresenter.getProducts();//获取购物车商品
            mPresenter.getCarNum();//获取购物车数量
        }else {
            mLLBottomView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hastoken) {
//            mLLBottomView.setVisibility(View.VISIBLE);
            mPresenter.getProducts();//获取购物车商品
            mPresenter.getCarNum();//获取购物车数量
            initBottomPrice();
        } else {
            mLLBottomView.setVisibility(View.GONE);
        }
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

    //初始化一级列表
    private void initRvFirst() {
        firstManager = new LinearLayoutManager(mContext);
        firstAdapter = new ClassifyAdapter(mContext, mDates);
        mRvFirst.setLayoutManager(firstManager);
        mRvFirst.setAdapter(firstAdapter);
        firstAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, int id) {
                fragmentManager.beginTransaction().hide(lastFragment).commit();
                if (fragmentList.get(pos).isAdded()) {
                    fragmentManager.beginTransaction().show(fragmentList.get(pos)).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.mFrame_second, fragmentList.get(pos)).commit();
                }
                lastFragment = fragmentList.get(pos);
            }

            @Override
            public void onChildClick(int pos, int id) {
                fragmentManager.beginTransaction().hide(lastFragment).commit();
                if (fragmentList.get(pos).isAdded()) {
                    fragmentManager.beginTransaction().show(fragmentList.get(pos)).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.mFrame_second, fragmentList.get(pos)).commit();
                }
                lastFragment = fragmentList.get(pos);
            }
        });
    }


    //一级分类标签数据
    @Override
    public void getFirstKind(List<FirstClassfyBean> firstClassfyBeanList) {
        if (firstClassfyBeanList == null || firstClassfyBeanList.size() == 0) {
            return;
        }

        mDates.clear();
        mDates.addAll(firstClassfyBeanList);
        mDates.get(0).setFirstSelected(true);
        mDates.get(0).setShowList(false);
        List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = mDates.get(0).getSubProductCategory();
        if (subProductCategory.size() > 0) {
            subProductCategory.get(0).setSelected(true);
        }
        firstAdapter.notifyDataSetChanged();
        initSecondClassfy(mDates);
    }

    //获取购物车商品
    @Override
    public void getProducts(ShoppingCarBean productBeans) {
        if (productBeans == null) {
            mLLBottomView.setVisibility(View.GONE);
            return;
        }


        List<CartItemsBean> cartItems = productBeans.getCartItems();
        if (cartItems == null || cartItems.size() == 0) {
            mLLBottomView.setVisibility(View.GONE);
            return;
        }

        mLLBottomView.setVisibility(View.VISIBLE);
        double freeFreight = productBeans.getFreeFreight();
        mTxtSendPrice.setVisibility(View.VISIBLE);
        minPrice = productBeans.getMinPrice();
        mTxtSendPrice.setText("满"+minPrice+"起送,"+"满"+freeFreight+"免运费");
        carList.clear();
        carList.addAll(cartItems);
        carPopAdapter.notifyDataSetChanged();
        initBottomPrice();
    }


    //初始化二级商品列表
    public void initSecondClassfy(List<FirstClassfyBean> firstClassfyList) {
        for (int i = 0; i < firstClassfyList.size(); i++) {

            List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = firstClassfyList.get(i).getSubProductCategory();
            if (subProductCategory.size() != 0) {
                for (int j = 0; j < subProductCategory.size(); j++) {

                    int id = subProductCategory.get(j).getId();
                    BuyListFragment buyListFragment = new BuyListFragment(this,id);
                    fragmentList.add(buyListFragment);
                }
            }

        }

        fragmentManager = mActivity.getSupportFragmentManager();

        lastFragment = fragmentList.get(0);
        fragmentManager.beginTransaction().add(R.id.mFrame_second, fragmentList.get(0)).commit();
    }

    //修改购物车数量
    @Override
    public void updateNum(int qty, boolean b, int pos) {
        if (b) {

            int quantity = carList.get(pos).getQuantity();
            carList.get(pos).setQuantity((quantity + qty));
            if ((quantity + qty) <= 0) {
                carList.remove(pos);
            }

            carPopAdapter.notifyDataSetChanged();
            initBottomPrice();//重新计算价格
        } else {
            ToastUtils.show(mContext, "失败");
        }


    }

    //获取付款信息
    @Override
    public void getPayMsg(GoToPayBean payBean) {
        if (payBean == null) {
            mLLBottomView.setVisibility(View.GONE);
            return;
        }
        mLLBottomView.setVisibility(View.VISIBLE);
        double boxAmount = payBean.getBoxAmount();//筐的金额
        double productAmount = payBean.getProductAmount();//总价格
        double freight = payBean.getFreight();//运费
        mTxtSendPrice.setText("满"+minPrice+"起送");
        if (minPrice >= productAmount) {//低于购买价格，不允许购买
            mTxtPay.setBackgroundResource(R.color.defalute);
            mTxtPay.setClickable(false);
        } else {
            mTxtPay.setBackgroundResource(R.color.basicColor);
            mTxtPay.setClickable(true);
        }

        mTxtCarTotalMoney.setText("" + productAmount);
        mTxtCarBoxMoney.setText("" + boxAmount);
        if ((freight - 0) <= 0) {
            mLLCarPost.setVisibility(View.GONE);
        } else {
            mLLCarPost.setVisibility(View.VISIBLE);
            mTxtCarPostMoney.setText("" + freight);
        }
    }

    //清空购物车
    @Override
    public void clearShoppingCar(boolean b) {
        if (popupWindow!=null){
            popupWindow.dismiss();
        }
        mPresenter.getProducts();
        mLLBottomView.setVisibility(View.GONE);
        mTxtCarTotalMoney.setText("0");
        mTxtCarBoxMoney.setText("0");
        mTxtCarPostMoney.setText("0");
//        mTxtSendPrice.setText("满"+minPrice+"起送");
        mPresenter.getCarNum();
        initBottomPrice();//重新计算价格
    }

    //获取购物车数量
    @Override
    public void getCarNum(int num) {
        if (num <= 0) {
            mTxtCarNum.setVisibility(View.GONE);
        } else if (num > 99) {
            mTxtCarNum.setVisibility(View.VISIBLE);
            mTxtCarNum.setText("99+");
        } else {
            mTxtCarNum.setVisibility(View.VISIBLE);
            mTxtCarNum.setText(num + "");
        }
    }


    //查看已经选择的商品
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


    //查看已经选择的商品(显示购物车商品)
    private void initPickView() {
        pickVIew = LayoutInflater.from(mActivity).inflate(R.layout.layout_popup_car_goods, null, false);

        View view_pop = pickVIew.findViewById(R.id.view_pop);

        mRv_carPop = pickVIew.findViewById(R.id.mRv_carPop);
        mLL_delete = pickVIew.findViewById(R.id.mLL_delete);
        LinearLayoutManager carManager = new LinearLayoutManager(mContext);
        carPopAdapter = new CarPopAdapter(mContext, carList);
        carPopAdapter.setOnItemClickListener(new CarPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {

            }

            @Override
            public void onJiaClick(int pos, long id) {
                ProductParmsBean productParmsBean = new ProductParmsBean(carList.get(pos).getProductInfo().getId());
                mPresenter.updateNum(1, productParmsBean, pos);
            }

            @Override
            public void onJianClick(int pos, long id) {
                ProductParmsBean productParmsBean = new ProductParmsBean(carList.get(pos).getProductInfo().getId());
                mPresenter.updateNum(-1, productParmsBean, pos);

            }
        });
        mRv_carPop.setLayoutManager(carManager);
        mRv_carPop.setAdapter(carPopAdapter);
        popupWindow = PopupWindowHelper.createPopupWindow(pickVIew, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //点击清空按钮
        mLL_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clearShoppingCar();
            }
        });
    }


    //计算购物车界面底部价格
    private void initBottomPrice() {

        if (carList.size() == 0) {
//            showErrorMsg("您没有选择结算的商品", "");
        } else {

            List<PayParamsFatherBean> payList = new ArrayList<>();

            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getProductInfo().isSelect()) {
                    long id = carList.get(i).getProductInfo().getId();//产品ID
                    int quantity = carList.get(i).getQuantity();//数量
                    PayParamsBean payParamsBean = new PayParamsBean();
                    payParamsBean.setId(id);

                    PayParamsFatherBean payParamsFatherBean = new PayParamsFatherBean();
                    payParamsFatherBean.setProductParam(payParamsBean);
                    payParamsFatherBean.setQuantity(quantity);
                    payList.add(payParamsFatherBean);
                }
            }

            mPresenter.payProducts(payList);
        }
    }


    public void initProducts(){
        mPresenter.getProducts();//获取购物车商品
        mPresenter.getCarNum();//获取购物车数量
    }

    @OnClick({R.id.mLL_search, R.id.ll_img,R.id.mTxt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLL_search://跳转搜索页面
                startActivity(SearchActivity.class);
                break;
            case R.id.ll_img://弹出已经加入购物车弹窗
//                mPresenter.getProducts();
                shoePOP();
                break;

            case R.id.mTxt_pay://去结算

                if (carList.size()==0){
                    showErrorMsg("购物车空空如也", "");
                    return;
                }
                List<PayParamsFatherBean>  payList = new ArrayList<>();

                for (int i = 0; i < carList.size(); i++) {
                    if (carList.get(i).getProductInfo().isSelect()){
                        long id = carList.get(i).getProductInfo().getId();//产品ID
                        int quantity = carList.get(i).getQuantity();//数量
                        PayParamsBean payParamsBean = new PayParamsBean();
                        payParamsBean.setId(id);

                        PayParamsFatherBean payParamsFatherBean = new PayParamsFatherBean();
                        payParamsFatherBean.setProductParam(payParamsBean);
                        payParamsFatherBean.setQuantity(quantity);
                        payList.add(payParamsFatherBean);
                    }
                }

                if (payList.size() == 0) {
                    showErrorMsg("购物车空空如也", "");
                }

                Intent intent = new Intent(mActivity, GoToPayActivity.class);
                intent.putExtra("payList", (Serializable) payList);
                startActivity(intent);
                break;
        }
    }

}
