package com.dodo.marcket.bean;

import java.io.Serializable;

public class CommentBean implements Serializable{

    private String productName;
    private long productId;
    private int score;
    private boolean support;//点赞1 差评0；

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSupport() {
        return support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }
}
