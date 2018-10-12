package com.dodo.marcket.bean;

import java.util.List;

/**
 *产品对象
 */
public class ProducHeadBean {

    /**
     * id : 79
     * name : 蔬菜
     * products : [{"cartNumber":0,"id":1,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"土豆","packaging":"纸盒","price":4,"specificationNumber":0,"stock":null,"tag":"","unit":"","unitPrice":4,"weight":1},{"cartNumber":0,"id":5,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"毛豆","packaging":"纸盒","price":123,"specificationNumber":0,"stock":null,"tag":"","unit":"","unitPrice":11.181819,"weight":11}]
     * showType : 1
     * subProductCategory : []
     */

    private int id;
    private String name;
    private String showType;// 1 横排 2 竖排
    private List<ProductBean> products;
    private ProductBean productInfo;


    public ProductBean getProductInfo() {
        return productInfo;
    }


    public void setProductInfo(ProductBean productInfo) {
        this.productInfo = productInfo;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

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

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

}
