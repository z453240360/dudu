package com.dodo.marcket.bean.params;

import java.io.Serializable;

public class PayParamsFatherBean implements Serializable{
    private int quantity;
    private PayParamsBean productParam;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductParam(PayParamsBean productParam) {
        this.productParam = productParam;
    }
}
