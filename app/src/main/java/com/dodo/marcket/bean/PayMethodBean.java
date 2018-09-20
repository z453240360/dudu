package com.dodo.marcket.bean;

public class PayMethodBean {

    /**
     * code : alipay
     * id : 1
     * methons : 0
     * name : 支付宝
     * orders : 1
     */

    private String code;
    private long id;
    private long methons;
    private String name;
    private long orders;
    private boolean isSelected = false;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMethons() {
        return methons;
    }

    public void setMethons(long methons) {
        this.methons = methons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrders() {
        return orders;
    }

    public void setOrders(long orders) {
        this.orders = orders;
    }
}
