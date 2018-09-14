package com.dodo.marcket.business.homepage.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.HomePageActivityBean;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.homepage.constrant.HomePageContract;
import com.dodo.marcket.business.homepage.fragment.HomePageFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.ArrayList;
import java.util.List;


public class HomePagePresenter extends BasePresenter<HomePageFragment> implements HomePageContract.Presenter{

    //热销商品
    @Override
    public void getHotProduct() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "product.getHotProduct";
        addSubscription(apiModel.getHotProductList(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<List<ProducHeadBean>>(mContext) {

            @Override
            public void apiSuccess(List<ProducHeadBean> s) {

                List<ProductBean> productBeans = new ArrayList<>();
                if (s!=null){
                    for (int i = 0; i < s.size(); i++) {
                        List<ProductBean> products = s.get(i).getProducts();

                        if (products!=null&&products.size()!=0){
                            productBeans.addAll(products);
                        }
                    }
                }
                mView.getHotProducts(productBeans);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //广告信息
    @Override
    public void getAdPosition(long adPositionId) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setAdPositionId(adPositionId);
        String name = "ad.getAdPosition";
        addSubscription(apiModel.getBanner(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<BannerBean>>(mContext) {
            @Override
            public void apiSuccess(List<BannerBean> s) {
                mView.getAdPosition(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //获取banner信息
    @Override
    public void getBanner(long adPositionId) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setAdPositionId(adPositionId);
        String name = "ad.getAdPosition";
        addSubscription(apiModel.getBanner(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<BannerBean>>(mContext) {
            @Override
            public void apiSuccess(List<BannerBean> s) {
                mView.getBanner(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //首页活动信息
    @Override
    public void getAllPromotion() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "promotion.getAllPromotion";
        addSubscription(apiModel.getAllPromotion(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<HomePageActivityBean>>(mContext) {
            @Override
            public void apiSuccess(List<HomePageActivityBean> s) {
                mView.getAllPromotion(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    /**
     * 添加商品
     * @param quantity 数量
     * @param productParmsBean
     */
    @Override
    public void addProduct(int quantity, ProductParmsBean productParmsBean) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setQuantity(quantity);
        phoneBean.setProductParmsBean(productParmsBean);
        String name = "cart.addProduct";
        addSubscription(apiModel.addProduct(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext,"asd") {

            @Override
            public void apiSuccess(Boolean s) {
                mView.addProduct(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
