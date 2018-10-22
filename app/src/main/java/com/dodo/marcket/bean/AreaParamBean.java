package com.dodo.marcket.bean;

/**
 * 区域参数
 */
public class AreaParamBean {
    private long id;
    private String consignee;



    public AreaParamBean() {
    }

    public AreaParamBean(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
