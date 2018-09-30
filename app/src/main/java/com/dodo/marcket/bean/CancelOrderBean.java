package com.dodo.marcket.bean;

public class CancelOrderBean {

    /**
     * amount : null
     * createDate :
     * id : null
     * msg : Success
     * offsetAmount : null
     * orderItems : null
     * orderStatus :
     * payAmount : null
     * paymentStatus :
     * retCode : 0
     * sn :
     */

    private Object amount;
    private String createDate;
    private Object id;
    private String msg;
    private Object offsetAmount;
    private Object orderItems;
    private String orderStatus;
    private Object payAmount;
    private String paymentStatus;
    private int retCode;
    private String sn;

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(Object offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public Object getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Object orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Object getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Object payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
