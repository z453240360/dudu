package com.dodo.marcket.bean;

/**
 * 优惠券对象
 */
public class DisCountBean {


    /**
     * amount : 10
     * anHaoStatus : NEW
     * anHaoType : FOR_MONEY
     * anhao : JbWAWUVZ42
     * endDate : 2018-08-31 00:00:00
     * lowLimit : 0
     * no :
     * startDate : 2018-08-15 00:00:00
     */

    private double amount;//面额，即满x减y中的y
    private String anHaoStatus;  //new 可以用  userd已使用  cancel作废
    private String anHaoType;   //all_shop  全场满减  for_money  代金
    private String anhao;//卡号
    private String endDate;//结束日期
    private double lowLimit;//最低下单金额，即满x减y中的x
    private String no; 	//
    private String startDate;//开始日期
    private boolean isSelected;
    private String status;//优惠券状态，0 表示可用，其他表示不能用；

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAnHaoStatus() {
        return anHaoStatus;
    }

    public void setAnHaoStatus(String anHaoStatus) {
        this.anHaoStatus = anHaoStatus;
    }

    public String getAnHaoType() {
        return anHaoType;
    }

    public void setAnHaoType(String anHaoType) {
        this.anHaoType = anHaoType;
    }

    public String getAnhao() {
        return anhao;
    }

    public void setAnhao(String anhao) {
        this.anhao = anhao;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(double lowLimit) {
        this.lowLimit = lowLimit;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
