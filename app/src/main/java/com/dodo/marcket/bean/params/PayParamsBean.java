package com.dodo.marcket.bean.params;

import java.io.Serializable;

public class PayParamsBean implements Serializable {

    private long  id;
    private long categoryId;


    public void setId(long id) {
        this.id = id;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
