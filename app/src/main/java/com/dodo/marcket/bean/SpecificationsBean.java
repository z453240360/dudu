package com.dodo.marcket.bean;

import java.util.List;

/**
 * 商品规格
 */
public class SpecificationsBean {
    private long id;
    private String name;


    private List<SpecificationValuesBean> specificationValues; //规格

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecificationValuesBean> getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(List<SpecificationValuesBean> specificationValues) {
        this.specificationValues = specificationValues;
    }
}
