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
        void addAddress(boolean myAddressBeanList);
        void getlistChildArea(List<ChildAddressBean> childAddressBeans);
        void deleteAddress(boolean b);
    }

    public interface Presenter {


        void upDateAddress(int id,String consignee,String phone,String address,boolean isDefault,String province,AreaParamBean areaParam);
        void addAddress(int id,String consignee,String phone,String address,boolean isDefault,String province,AreaParamBean areaParam);
        void getlistChildArea(long id);
        void deleteAddress(long id);
    }
}
