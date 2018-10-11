package com.dodo.marcket.bean.params;

import com.dodo.marcket.bean.OrderItemCommentParamsBean;

import java.io.Serializable;
import java.util.List;

public class CommentParamsBean implements Serializable{

    private long orderId;//订单号
    private int expressScore;//快递分
    private int productScore;//商品分
    private String comment;//评论
    private List<OrderItemCommentParamsBean> orderItemComments;

    public List<OrderItemCommentParamsBean> getOrderItemCommentParams() {
        return orderItemComments;
    }

    public void setOrderItemCommentParams(List<OrderItemCommentParamsBean> orderItemCommentParams) {
        this.orderItemComments = orderItemCommentParams;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getExpressScore() {
        return expressScore;
    }

    public void setExpressScore(int expressScore) {
        this.expressScore = expressScore;
    }

    public int getProductScore() {
        return productScore;
    }

    public void setProductScore(int productScore) {
        this.productScore = productScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
