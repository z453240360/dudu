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
    private int id;
    private int methons;
    private String name;
    private int orders;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMethons() {
        return methons;
    }

    public void setMethons(int methons) {
        this.methons = methons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}
