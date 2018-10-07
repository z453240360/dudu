package com.dodo.marcket.bean.basebean;

import com.dodo.marcket.bean.AreaParamBean;
import com.dodo.marcket.bean.ProductParmsBean;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class PhoneBean {

    private String mobile;
    private String messageCode;
    private String data;
    private String strParam;
    private long id;
    private int pageNumber;
    private int pageSize;
    private long adPositionId;
    private int status;  // 优惠券  0 表示可以使用。不是0则返回过期和已使用
    private int quantity;
    private ProductParmsBean productParam;
    private AreaParamBean areaParam;
    private String consignee;
    private String phone;
    private String address;
    private boolean isDefault;
    private String province;
    private String code;
    private String key;
    private String productId;
    private String specParam;


    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setSpecParam(String specParam) {
        this.specParam = specParam;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProductParam(ProductParmsBean productParam) {
        this.productParam = productParam;
    }

    public void setAreaParam(AreaParamBean areaParam) {
        this.areaParam = areaParam;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductParmsBean(ProductParmsBean productParmsBean) {
        this.productParam = productParmsBean;
    }




    public void setStatus(int status) {
        this.status = status;
    }

    public void setAdPositionId(long adPositionId) {
        this.adPositionId = adPositionId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setStrParam(String strParam) {
        this.strParam = strParam;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
