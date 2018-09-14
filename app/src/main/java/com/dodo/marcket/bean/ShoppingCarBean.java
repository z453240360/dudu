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

    private int boxAmount;
    private int freeFreight;
    private int freight;
    private int minPrice;
    private int productAmount;
    private List<CartItemsBean> cartItems;

    public int getBoxAmount() {
        return boxAmount;
    }

    public void setBoxAmount(int boxAmount) {
        this.boxAmount = boxAmount;
    }

    public int getFreeFreight() {
        return freeFreight;
    }

    public void setFreeFreight(int freeFreight) {
        this.freeFreight = freeFreight;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getProductAmount() {
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

    public static class CartItemsBean {
        /**
         * productInfo : {"boxCode":"xxx-112-121313","boxPrice":15,"cartNumber":0,"id":13,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"test","packaging":"纸盒","price":111,"specificationNumber":0,"stock":32,"tag":"","unit":"","unitPrice":0.091585,"weight":1212}
         * quantity : 1
         */

        private ProductInfoBean productInfo;
        private int quantity;

        public ProductInfoBean getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(ProductInfoBean productInfo) {
            this.productInfo = productInfo;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public static class ProductInfoBean {
            /**
             * boxCode : xxx-112-121313
             * boxPrice : 15
             * cartNumber : 0
             * id : 13
             * image : http://s1.12chu.com/default/512-512.png
             * isGift : false
             * memo :
             * name : test
             * packaging : 纸盒
             * price : 111
             * specificationNumber : 0
             * stock : 32
             * tag :
             * unit :
             * unitPrice : 0.091585
             * weight : 1212
             */
            private boolean isSelect=true;
            private String boxCode;
            private int boxPrice;
            private int cartNumber;
            private long id;
            private String image;
            private boolean isGift;
            private String memo;
            private String name;
            private String packaging;
            private int price;
            private int specificationNumber;
            private Integer stock;
            private String tag;
            private String unit;
            private double unitPrice;
            private int weight;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getBoxCode() {
                return boxCode;
            }

            public void setBoxCode(String boxCode) {
                this.boxCode = boxCode;
            }

            public int getBoxPrice() {
                return boxPrice;
            }

            public void setBoxPrice(int boxPrice) {
                this.boxPrice = boxPrice;
            }

            public int getCartNumber() {
                return cartNumber;
            }

            public void setCartNumber(int cartNumber) {
                this.cartNumber = cartNumber;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public boolean isIsGift() {
                return isGift;
            }

            public void setIsGift(boolean isGift) {
                this.isGift = isGift;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPackaging() {
                return packaging;
            }

            public void setPackaging(String packaging) {
                this.packaging = packaging;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSpecificationNumber() {
                return specificationNumber;
            }

            public void setSpecificationNumber(int specificationNumber) {
                this.specificationNumber = specificationNumber;
            }

            public Integer getStock() {
                return stock;
            }

            public void setStock(Integer stock) {
                this.stock = stock;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }
        }
    }
}
