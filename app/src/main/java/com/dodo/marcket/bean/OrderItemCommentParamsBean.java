package com.dodo.marcket.bean;

import java.io.Serializable;

public class OrderItemCommentParamsBean implements Serializable{

    private Long productId;//商品Id
    private boolean support = true;//赞
    private String productName;
    private boolean canClick = true;//是否可以点击评论

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public boolean isSupport() {
        return support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }
}
