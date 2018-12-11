package com.dodo.marcket.bean;

/**
 * 作者：东东
 * 日期：2018/12/10 001015:48
 * 描述：
 */
public class MyPointBean {

    /**
     * afterPoint : 0
     * beforePoint : 0
     * id : 2c938603666737300166673e5bc20000
     * memberId : 3
     * memberName : 18912367774
     * point : 0
     * type : 订单完成
     */

    private double afterPoint;
    private double beforePoint;
    private String id;
    private int memberId;
    private String memberName;
    private int point;
    private String type;
    private String happenDate;
    private int currentPoint;

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public double getAfterPoint() {
        return afterPoint;
    }

    public void setAfterPoint(double afterPoint) {
        this.afterPoint = afterPoint;
    }

    public double getBeforePoint() {
        return beforePoint;
    }

    public void setBeforePoint(double beforePoint) {
        this.beforePoint = beforePoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
