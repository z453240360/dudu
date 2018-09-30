package com.dodo.marcket.bean;


/**
 *请求购物车参数对象
 */
public class ProductParmsBean {
    private long id;//产品ID
    private long categoryId;//分类ID



    public ProductParmsBean(long id) {
        this.id = id;
    }

    public ProductParmsBean(long id, long categoryId) {
        this.id = id;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
