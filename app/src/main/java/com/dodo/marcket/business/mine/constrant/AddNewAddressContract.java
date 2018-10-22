package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.AreaParamBean;
import com.dodo.marcket.bean.ChildAddressBean;
import com.dodo.marcket.bean.MyAddressBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class AddNewAddressContract {

    public interface View extends BaseView {
        void addAddress(Object myAddressBeanList);
        void getlistChildArea(List<ChildAddressBean> childAddressBeans);
        void getlistChildArea2(List<ChildAddressBean> childAddressBeans);
        void deleteAddress(boolean b);
        void getFirstArea(List<ChildAddressBean> s);
    }

    public interface Presenter {


        void upDateAddress(long id,String consignee,String phone,String address,boolean isDefault,String province,String city, String district,AreaParamBean areaParam);
        void addAddress(int id,String consignee,String phone,String address,boolean isDefault,String province,String city, String district,AreaParamBean areaParam);
        void getlistChildArea(long id);
        void getlistChildArea2(long id);
        void deleteAddress(long id);
        void getFirstArea();
    }
}
