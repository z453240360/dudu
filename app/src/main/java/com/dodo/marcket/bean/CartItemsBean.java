package com.dodo.marcket.bean;

public  class CartItemsBean {
    /**
     * productInfo : {"boxCode":"xxx-112-121313","boxPrice":15,"cartNumber":0,"id":13,"image":"http://s1.12chu.com/default/512-512.png","isGift":false,"memo":"","name":"test","packaging":"纸盒","price":111,"specificationNumber":0,"stock":32,"tag":"","unit":"","unitPrice":0.091585,"weight":1212}
     * quantity : 1
     */

    private ProductInfoBean productInfo;
    private int quantity;
    private String promotionName;

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

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
//        private int boxPrice;
        private double cartNumber;
        private long id;
        private String image;
        private boolean isGift;
        private String memo;
        private String name;
        private String packaging;
        private double price;
        private double specificationNumber;
        private Integer stock;
        private String tag;
        private String unit;
        private double unitPrice;
        private double weight;

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

//        public int getBoxPrice() {
//            return boxPrice;
//        }
//
//        public void setBoxPrice(int boxPrice) {
//            this.boxPrice = boxPrice;
//        }

        public double getCartNumber() {
            return cartNumber;
        }

        public void setCartNumber(double cartNumber) {
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getSpecificationNumber() {
            return specificationNumber;
        }

        public void setSpecificationNumber(double specificationNumber) {
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

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
}
