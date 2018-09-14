package com.dodo.marcket.bean;

import java.util.List;

public class HotBean {


    /**
     * id : 4
     * introduction : 阿斯顿撒打算<img src="http://dudu-app.oss-cn-shanghai.aliyuncs.com/upload/image/201808/2806f619-83e9-4564-ab1b-43d24a7a97b8.jpg" alt="" />
     * mobileImage : http://cdn.duoduofresh.com/upload/image/201809/cfdb56f9-0aee-4761-9bab-2c8e46c5a73a.png
     * name : 11
     * productInfoList : []
     * size :
     * smallMobileImage : http://cdn.duoduofresh.com/upload/image/201809/0d6c33ac-3dfc-416d-8ce9-a08ea6fbbb73.png
     */

    private int id;
    private String introduction;
    private String mobileImage;
    private String name;
    private String size;
    private String smallMobileImage;
    private List<ProductBean> productInfoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSmallMobileImage() {
        return smallMobileImage;
    }

    public void setSmallMobileImage(String smallMobileImage) {
        this.smallMobileImage = smallMobileImage;
    }

    public List<ProductBean> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductBean> productInfoList) {
        this.productInfoList = productInfoList;
    }
}
