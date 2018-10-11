package com.dodo.marcket.bean;

import java.util.List;

public class OrderDetailBean {

    /**
     * amount : 2.71
     * createDate : 2018-10-03 16:52:04
     * id : 66
     * msg :
     * offsetAmount : 0
     * orderItems : [{"orderPrice":4,"orderWeight":1,"productInfo":{"boxCode":"","boxPrice":null,"cartNumber":0,"id":1,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"土豆","packaging":"纸盒","price":4,"specificationNumber":0,"stock":100,"tag":"","unit":"","unitPrice":4,"weight":1},"qty":1,"realPrice":0,"realWeight":0}]
     * orderStatus : 待付款
     * payAmount : 0
     * paymentStatus : 未支付
     * retCode : 0
     * sn : SO20181003192437
     */

    private double amount;
    private String createDate;
    private int id;
    private String msg;
    private double offsetAmount;
    private String orderStatus;
    private int payAmount;
    private String paymentStatus;
    private int retCode;
    private String sn;
    private List<OrderItemsBean> orderItems;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(double offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class OrderItemsBean {
        /**
         * orderPrice : 4
         * orderWeight : 1
         * productInfo : {"boxCode":"","boxPrice":null,"cartNumber":0,"id":1,"image":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg","isGift":false,"memo":"","name":"土豆","packaging":"纸盒","price":4,"specificationNumber":0,"stock":100,"tag":"","unit":"","unitPrice":4,"weight":1}
         * qty : 1
         * realPrice : 0
         * realWeight : 0
         */

        private double orderPrice;
        private double orderWeight;
        private ProductInfoBean productInfo;
        private int qty;
        private double realPrice;
        private double realWeight;

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public double getOrderWeight() {
            return orderWeight;
        }

        public void setOrderWeight(double orderWeight) {
            this.orderWeight = orderWeight;
        }

        public ProductInfoBean getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(ProductInfoBean productInfo) {
            this.productInfo = productInfo;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(double realPrice) {
            this.realPrice = realPrice;
        }

        public double getRealWeight() {
            return realWeight;
        }

        public void setRealWeight(int realWeight) {
            this.realWeight = realWeight;
        }

        public static class ProductInfoBean {
            /**
             * boxCode :
             * boxPrice : null
             * cartNumber : 0
             * id : 1
             * image : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=780835599,1196854585&fm=26&gp=0.jpg
             * isGift : false
             * memo :
             * name : 土豆
             * packaging : 纸盒
             * price : 4
             * specificationNumber : 0
             * stock : 100
             * tag :
             * unit :
             * unitPrice : 4
             * weight : 1
             */

            private String boxCode;
            private Object boxPrice;
            private int cartNumber;
            private int id;
            private String image;
            private boolean isGift;
            private String memo;
            private String name;
            private String packaging;
            private double price;
            private int specificationNumber;
            private int stock;
            private String tag;
            private String unit;
            private double unitPrice;
            private double weight;

            public String getBoxCode() {
                return boxCode;
            }

            public void setBoxCode(String boxCode) {
                this.boxCode = boxCode;
            }

            public Object getBoxPrice() {
                return boxPrice;
            }

            public void setBoxPrice(Object boxPrice) {
                this.boxPrice = boxPrice;
            }

            public int getCartNumber() {
                return cartNumber;
            }

            public void setCartNumber(int cartNumber) {
                this.cartNumber = cartNumber;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getSpecificationNumber() {
                return specificationNumber;
            }

            public void setSpecificationNumber(int specificationNumber) {
                this.specificationNumber = specificationNumber;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
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

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }
        }
    }
}