package com.dodo.marcket.bean.basebean;

public class MyMessageEvent {

    private String name;
    private int age;

    private int carNum;

    private int fromWhere;



    public MyMessageEvent() {
    }

    public MyMessageEvent(int carNum) {
        this.carNum = carNum;
    }

    public int getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(int fromWhere) {
        this.fromWhere = fromWhere;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
