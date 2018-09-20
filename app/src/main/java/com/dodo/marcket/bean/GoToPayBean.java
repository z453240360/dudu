package com.dodo.marcket.bean;

import java.util.List;

public class GoToPayBean {

    /**
     * anHaos : []
     * boxAmount : 75.0
     * carts : [{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":13,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"test","packaging":"纸盒","price":111,"specificationNumber":0,"stock":32,"tag":"","unit":"","unitPrice":0.091585,"weight":1212},"quantity":2},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":12,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"1213","packaging":"塑料箱","price":31313,"specificationNumber":0,"stock":0,"tag":"热点","unit":"","unitPrice":28.184519,"weight":1111},"quantity":4},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":11,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"11","packaging":"塑料箱","price":121,"specificationNumber":0,"stock":110,"tag":"","unit":"13","unitPrice":1,"weight":121},"quantity":17},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":2,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"冬瓜","packaging":"塑料箱","price":27,"specificationNumber":0,"stock":76,"tag":"热点","unit":"","unitPrice":2.25,"weight":12},"quantity":10},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":1,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"土豆","packaging":"纸盒","price":4,"specificationNumber":0,"stock":100,"tag":"","unit":"","unitPrice":4,"weight":1},"quantity":9},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":5,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"毛豆[米黄色]","packaging":"纸盒","price":123,"specificationNumber":0,"stock":3,"tag":"","unit":"","unitPrice":11.181819,"weight":11},"quantity":2},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":7,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"正品3[深蓝色]","packaging":"塑料箱","price":1,"specificationNumber":0,"stock":3,"tag":"","unit":"","unitPrice":1,"weight":1},"quantity":3},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":10,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"毛豆[深蓝色]","packaging":"纸盒","price":123,"specificationNumber":0,"stock":3,"tag":"热点","unit":"","unitPrice":11.181819,"weight":11},"quantity":3},{"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":6,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":true,"memo":"","name":"正品4[黄色]","packaging":"塑料箱","price":1,"specificationNumber":0,"stock":3,"tag":"","unit":"","unitPrice":1,"weight":1},"quantity":2}]
     * freight : 0
     * giftItemInfos : [{"id":6,"name":"正品4"}]
     * onlineDiscount : 1.3
     * paymentMethods : [{"name":"支付宝"},{"name":"货到付款"},{"name":"微信支付"}]
     * productAmount : 128457.0
     * receiverInfo : null
     * userPoint : 0
     */

    private double boxAmount;
    private double freight;
    private double onlineDiscount;
    private double productAmount;
    private MyAddressBean receiverInfo;
    private double userPoint;
    private List<DisCountBean> anHaos;
    private List<CartItemsBean> carts;
    private List<GiftItemInfosBean> giftItemInfos;
    private List<PaymentMethodsBean> paymentMethods;

    public double getBoxAmount() {
        return boxAmount;
    }

    public void setBoxAmount(double boxAmount) {
        this.boxAmount = boxAmount;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getOnlineDiscount() {
        return onlineDiscount;
    }

    public void setOnlineDiscount(double onlineDiscount) {
        this.onlineDiscount = onlineDiscount;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }

    public MyAddressBean getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(MyAddressBean receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public double getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(double userPoint) {
        this.userPoint = userPoint;
    }

    public List<DisCountBean> getAnHaos() {
        return anHaos;
    }

    public void setAnHaos(List<DisCountBean> anHaos) {
        this.anHaos = anHaos;
    }

    public List<CartItemsBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartItemsBean> carts) {
        this.carts = carts;
    }

    public List<GiftItemInfosBean> getGiftItemInfos() {
        return giftItemInfos;
    }

    public void setGiftItemInfos(List<GiftItemInfosBean> giftItemInfos) {
        this.giftItemInfos = giftItemInfos;
    }

    public List<PaymentMethodsBean> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodsBean> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

//    public static class CartsBean {
//        /**
//         * productInfo : {"boxCode":"","boxPrice":null,"cartNumber":0,"id":13,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"test","packaging":"纸盒","price":111,"specificationNumber":0,"stock":32,"tag":"","unit":"","unitPrice":0.091585,"weight":1212}
//         * quantity : 2
//         */
//
//        private ProductInfoBean productInfo;
//        private int quantity;
//
//        public ProductInfoBean getProductInfo() {
//            return productInfo;
//        }
//
//        public void setProductInfo(ProductInfoBean productInfo) {
//            this.productInfo = productInfo;
//        }
//
//        public int getQuantity() {
//            return quantity;
//        }
//
//        public void setQuantity(int quantity) {
//            this.quantity = quantity;
//        }
//
//        public static class ProductInfoBean {
//            /**
//             * boxCode :
//             * boxPrice : null
//             * cartNumber : 0
//             * id : 13
//             * image : http://s1.12chu.com/default/512-512.png
//             * isGift : false
//             * memo :
//             * name : test
//             * packaging : 纸盒
//             * price : 111.0
//             * specificationNumber : 0
//             * stock : 32
//             * tag :
//             * unit :
//             * unitPrice : 0.091585
//             * weight : 1212
//             */
//
//            private String boxCode;
//            private Object boxPrice;
//            private int cartNumber;
//            private int id;
//            private String image;
//            private boolean isGift;
//            private String memo;
//            private String name;
//            private String packaging;
//            private double price;
//            private int specificationNumber;
//            private int stock;
//            private String tag;
//            private String unit;
//            private double unitPrice;
//            private int weight;
//
//            public String getBoxCode() {
//                return boxCode;
//            }
//
//            public void setBoxCode(String boxCode) {
//                this.boxCode = boxCode;
//            }
//
//            public Object getBoxPrice() {
//                return boxPrice;
//            }
//
//            public void setBoxPrice(Object boxPrice) {
//                this.boxPrice = boxPrice;
//            }
//
//            public int getCartNumber() {
//                return cartNumber;
//            }
//
//            public void setCartNumber(int cartNumber) {
//                this.cartNumber = cartNumber;
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getImage() {
//                return image;
//            }
//
//            public void setImage(String image) {
//                this.image = image;
//            }
//
//            public boolean isIsGift() {
//                return isGift;
//            }
//
//            public void setIsGift(boolean isGift) {
//                this.isGift = isGift;
//            }
//
//            public String getMemo() {
//                return memo;
//            }
//
//            public void setMemo(String memo) {
//                this.memo = memo;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getPackaging() {
//                return packaging;
//            }
//
//            public void setPackaging(String packaging) {
//                this.packaging = packaging;
//            }
//
//            public double getPrice() {
//                return price;
//            }
//
//            public void setPrice(double price) {
//                this.price = price;
//            }
//
//            public int getSpecificationNumber() {
//                return specificationNumber;
//            }
//
//            public void setSpecificationNumber(int specificationNumber) {
//                this.specificationNumber = specificationNumber;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getTag() {
//                return tag;
//            }
//
//            public void setTag(String tag) {
//                this.tag = tag;
//            }
//
//            public String getUnit() {
//                return unit;
//            }
//
//            public void setUnit(String unit) {
//                this.unit = unit;
//            }
//
//            public double getUnitPrice() {
//                return unitPrice;
//            }
//
//            public void setUnitPrice(double unitPrice) {
//                this.unitPrice = unitPrice;
//            }
//
//            public int getWeight() {
//                return weight;
//            }
//
//            public void setWeight(int weight) {
//                this.weight = weight;
//            }
//        }
//    }

    public static class GiftItemInfosBean {
        /**
         * id : 6
         * name : 正品4
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

    public static class PaymentMethodsBean {
        /**
         * name : 支付宝
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
