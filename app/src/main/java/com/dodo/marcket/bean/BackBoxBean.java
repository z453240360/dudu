package com.dodo.marcket.bean;

import java.util.List;

/**
 * 申请退框对象
 */
public class BackBoxBean {
    private List<BackBoxChildBean> backBoxChildBeans;

    public List<BackBoxChildBean> getBackBoxChildBeans() {
        return backBoxChildBeans;
    }

    public void setBackBoxChildBeans(List<BackBoxChildBean> backBoxChildBeans) {
        this.backBoxChildBeans = backBoxChildBeans;
    }
}
