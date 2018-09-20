package com.dodo.marcket.bean;

import java.util.List;

public class ShoppingCarBean {

    /**
     * boxAmount : 0
     * cartItems : [{"productInfo":{"boxCode":"xxx-112-121313","boxPrice":15,"cartNumber":0,"id":13,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"test","packaging":"纸盒","price":111,"specificationNumber":0,"stock":32,"tag":"","unit":"","unitPrice":0.091585,"weight":1212},"quantity":1},{"productInfo":{"boxCode":"xxx-112-121313","boxPrice":15,"cartNumber":0,"id":12,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"1213","packaging":"塑料箱","price":31313,"specificationNumber":0,"stock":null,"tag":"热点","unit":"","unitPrice":28.184519,"weight":1111},"quantity":2},{"productInfo":{"boxCode":"xxx-112-121313","boxPrice":15,"cartNumber":0,"id":11,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"11","packaging":"塑料箱","price":121,"specificationNumber":0,"stock":110,"tag":"","unit":"13","unitPrice":1,"weight":121},"quantity":2},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":2,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"冬瓜","packaging":"塑料箱","price":27,"specificationNumber":0,"stock":76,"tag":"热点","unit":"","unitPrice":2.25,"weight":12},"quantity":2},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":1,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"土豆","packaging":"纸盒","price":4,"specificationNumber":0,"stock":100,"tag":"","unit":"","unitPrice":4,"weight":1},"quantity":1},{"productInfo":{"boxCode":"xxx-112-121313","boxPrice":15,"cartNumber":0,"id":5,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"毛豆[米黄色 均码]","packaging":"纸盒","price":123,"specificationNumber":0,"stock":3,"tag":"","unit":"","unitPrice":11.181819,"weight":11},"quantity":1},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":7,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"正品1[深蓝色 深蓝色 XS]","packaging":"塑料箱","price":1,"specificationNumber":0,"stock":3,"tag":"","unit":"","unitPrice":1,"weight":1},"quantity":1}]
     * freeFreight : 200
     * freight : 20
     * minPrice : 1
     * productAmount : 0
     */

    private double boxAmount;//boxamount是框金额
    private double freeFreight; //freefrieght是满200免运费
    private double freight;//第二个是不满200收20运费
    private double minPrice;//minprice是订单最小金额，就是商品大于minprice才能下单，做批发的
    private double productAmount;//productamount是商品金额
    private List<CartItemsBean> cartItems;

    public double getBoxAmount() {
        return boxAmount;
    }

    public void setBoxAmount(int boxAmount) {
        this.boxAmount = boxAmount;
    }

    public double getFreeFreight() {
        return freeFreight;
    }

    public void setFreeFreight(double freeFreight) {
        this.freeFreight = freeFreight;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public List<CartItemsBean> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsBean> cartItems) {
        this.cartItems = cartItems;
    }


}
