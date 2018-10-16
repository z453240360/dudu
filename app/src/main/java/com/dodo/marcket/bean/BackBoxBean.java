package com.dodo.marcket.bean;

import java.util.List;

/**
 * 申请退框对象
 */
public class BackBoxBean {
    /**
     * boxs : [{"boxCode":"毛重20斤","boxId":2,"boxName":"毛重20斤（允许1斤误差）","boxPrice":0,"quantity":1}]
     * effectiveDate : 2018-10-23 10:27:42
     * orderComplateDate : 2018-10-16 10:27:42
     * orderDate : 2018-10-16 10:20:06
     * orderId : 115
     * orderSn : SO20181016293795
     */

    private String effectiveDate;//有效期
    private String orderComplateDate;//订单完成日期
    private String orderDate;//下单时间
    private int orderId;//订单ID
    private String orderSn;//订单号
    private List<BackBoxChildBean> boxs;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getOrderComplateDate() {
        return orderComplateDate;
    }

    public void setOrderComplateDate(String orderComplateDate) {
        this.orderComplateDate = orderComplateDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public List<BackBoxChildBean> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<BackBoxChildBean> boxs) {
        this.boxs = boxs;
    }
}
