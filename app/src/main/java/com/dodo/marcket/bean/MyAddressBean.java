package com.dodo.marcket.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *地址库对象
 */
public class MyAddressBean implements Serializable{

    /**
     * address : bgfc
     * areaInfo : {"id":795,"name":"上海市长宁区"}
     * consignee : fghhh
     * default : false
     * id : 13
     * phone : fghjj
     * province : 上海市
     */

    private String address;
    private AreaInfoBean areaInfo;
    private String consignee;
    @SerializedName("default")
    private boolean defaultX;
    private int id;
    private String phone;
    private String province;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AreaInfoBean getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfoBean areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public boolean isDefaultX() {
        return defaultX;
    }

    public void setDefaultX(boolean defaultX) {
        this.defaultX = defaultX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public static class AreaInfoBean implements Serializable{
        /**
         * id : 795
         * name : 上海市长宁区
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
