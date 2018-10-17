package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.AreaParamBean;
import com.dodo.marcket.bean.ChildAddressBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.AddNewAddressActivity;
import com.dodo.marcket.business.mine.constrant.AddNewAddressContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class AddNewAddressPresenter extends BasePresenter<AddNewAddressActivity> implements AddNewAddressContract.Presenter{

    /**
     * 新增会员地址
     * @param id    非毕传
     * @param consignee 收货人名字
     * @param phone  电话
     * @param address 地址
     * @param isDefault 是否默认
     * @param province 省
     * @param areaParam 区域ID
     */
    @Override
    public void addAddress(int id, String consignee, String phone, String address, boolean isDefault, String province, AreaParamBean areaParam) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setConsignee(consignee);
        phoneBean.setPhone(phone);
        phoneBean.setAddress(address);
        phoneBean.setDefault(isDefault);
        phoneBean.setProvince(province);
        phoneBean.setAreaParam(areaParam);

        String name = "member.addReceiver";
        addSubscription(apiModel.addNewAddress(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

            @Override
            public void apiSuccess(Boolean s) {
                mView.addAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //编辑
    @Override
    public void upDateAddress(int id, String consignee, String phone, String address, boolean isDefault, String province, AreaParamBean areaParam) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setConsignee(consignee);
        phoneBean.setPhone(phone);
        phoneBean.setAddress(address);
        phoneBean.setDefault(isDefault);
        phoneBean.setProvince(province);
        phoneBean.setAreaParam(areaParam);

        String name = "member.updateReceiver";
        addSubscription(apiModel.addNewAddress(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

            @Override
            public void apiSuccess(Boolean s) {
                mView.addAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //根据id获取该地区的子区域,上海的id=792
    @Override
    public void getlistChildArea(long id) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        String name = "area.listChildArea";
        addSubscription(apiModel.getListChildArea(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<ChildAddressBean>>(mContext,"sdasd") {

            @Override
            public void apiSuccess(List<ChildAddressBean> s) {
                mView.getlistChildArea(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    /**
     * 删除会员地址
     * @param id
     */
    @Override
    public void deleteAddress(long id) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        String name = "member.deleteReceiver";
        addSubscription(apiModel.deleteReceiver(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext,"sdasd") {

            @Override
            public void apiSuccess(Boolean s) {
                mView.deleteAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

}
