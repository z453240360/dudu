package com.dodo.marcket.bean.params;

import java.util.List;

public class PayBean {
    private List<PayParamsFatherBean> cartItemParamList;

    public void setCartItemParamList(List<PayParamsFatherBean> cartItemParamList) {
        this.cartItemParamList = cartItemParamList;
    }
}
