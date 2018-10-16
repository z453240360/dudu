package com.dodo.marcket.bean;
/**
 * 申请退框对象
 */
public class BackBoxChildBean {

    private String boxCode;//筐编号
    private int boxId;//筐ID
    private String boxName;//筐名称
    private double boxPrice;//筐单价
    private int quantity;//可退筐数量
    private boolean isSelect = true;
    private int backQty = 1;//要退筐的数量

    public int getBackQty() {
        return backQty;
    }

    public void setBackQty(int backQty) {
        this.backQty = backQty;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
