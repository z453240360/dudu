package com.dodo.marcket.bean.basebean;

/**
 * 存储用户信息
 */

public class UserInfoBean {

    /**
     * hasPayOrder : 0             已完成订单数量
     * isHasSalesman : false       是否绑定业务员
     * memberRank :                会员等级
     * mobile : 18912367774        手机号
     * noPayOrderNumber : 0        待付款订单数量
     * noRecevedOrder : 0          待收货订单数量
     * point : 0                   积分
     * salesmanCode :	           业务员编号
     * salesmanName :              业务员姓名
     * sex : 男                    性别
     * smallHeadImage :            用户头像
     * username : 18912367774	   用户名
     * voucherNumber : 0           优惠券数量
     */

    private int hasPayOrder;
    private boolean isHasSalesman;
    private String memberRank;
    private String mobile;
    private int noPayOrderNumber;
    private int noRecevedOrder;
    private int point;
    private String salesmanCode;
    private String salesmanName;
    private String sex;
    private String smallHeadImage;
    private String username;
    private int voucherNumber;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHasPayOrder() {
        return hasPayOrder;
    }

    public void setHasPayOrder(int hasPayOrder) {
        this.hasPayOrder = hasPayOrder;
    }

    public boolean isIsHasSalesman() {
        return isHasSalesman;
    }

    public void setIsHasSalesman(boolean isHasSalesman) {
        this.isHasSalesman = isHasSalesman;
    }

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getNoPayOrderNumber() {
        return noPayOrderNumber;
    }

    public void setNoPayOrderNumber(int noPayOrderNumber) {
        this.noPayOrderNumber = noPayOrderNumber;
    }

    public int getNoRecevedOrder() {
        return noRecevedOrder;
    }

    public void setNoRecevedOrder(int noRecevedOrder) {
        this.noRecevedOrder = noRecevedOrder;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(String salesmanCode) {
        this.salesmanCode = salesmanCode;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSmallHeadImage() {
        return smallHeadImage;
    }

    public void setSmallHeadImage(String smallHeadImage) {
        this.smallHeadImage = smallHeadImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(int voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
}
