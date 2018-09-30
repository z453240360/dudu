package com.dodo.marcket.bean.params;

import com.dodo.marcket.bean.ProductParmsBean;

/**
 * 更新购物车数量参数
 */

public class UpCarNumBean {
    private int quantity;
    private ProductParmsBean productParam;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductParmsBean getProductParam() {
        return productParam;
    }

    public void setProductParam(ProductParmsBean productParam) {
        this.productParam = productParam;
    }
}
