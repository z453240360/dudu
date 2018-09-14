package com.dodo.marcket.bean;

public class HomePageActivityBean {


    /**
     * id : 4
     * introduction :
     * mobileImage : http://cdn.duoduofresh.com/upload/image/201808/08175db9-30ce-492f-b040-d9d5c12eec12-large.jpg
     * name : 11
     * productInfoList : null
     * smallMobileImage : http://cdn.duoduofresh.com/upload/image/201808/08175db9-30ce-492f-b040-d9d5c12eec12-mobile.jpg
     */

    private long id;
    private String introduction;
    private String mobileImage;//促销展示图片
    private String name;
    private String smallMobileImage;//促销展示小图片
    private int kind=1;
    private String size="";

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSmallMobileImage() {
        return smallMobileImage;
    }

    public void setSmallMobileImage(String smallMobileImage) {
        this.smallMobileImage = smallMobileImage;
    }
}
