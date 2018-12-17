package com.dodo.marcket.bean;

import java.util.List;

/**
 * 作者：东东
 * 日期：2018/12/10 001017:11
 * 描述：
 */
public class MiaoShaBean {


    /**
     * beginDate : 2018-10-01 16:51:08
     * endDate : 2018-12-31 16:51:12
     * id : 7
     * introduction : <p>
     <img src="http://cdn.duoduofresh.com/upload/image/201810/b92b7624-5442-423b-983f-27fe24105af2.png" alt="" />
     </p>
     <p>
     商品5折啦
     </p>
     * mobileImage : http://cdn.duoduofresh.com/upload/image/201812/613b4322-afcd-4536-9c09-06f23a8d4d6b.jpg
     * name : 5折商品
     * productInfoList : [{"boxCode":"","boxPrice":0,"cartNumber":0,"id":45,"image":"http://cdn.duoduofresh.com/upload/image/201812/9e534f36-ca39-4797-83bc-a077364474f4.jpg","isGift":false,"memo":"","name":"贝贝小番茄","packaging":"泡沫箱","price":185,"promotionPrice":92.5,"specificationNumber":0,"stock":20,"tag":"","unit":"斤","unitPrice":7.4,"weight":25},{"boxCode":"","boxPrice":0,"cartNumber":0,"id":41,"image":"http://cdn.duoduofresh.com/upload/image/201811/957c155c-cffb-4044-b9b4-df3469173f0b.jpg","isGift":false,"memo":"","name":"水果黄瓜","packaging":"泡沫箱带水瓶","price":44,"promotionPrice":22,"specificationNumber":0,"stock":20,"tag":"","unit":"斤","unitPrice":1.83,"weight":24},{"boxCode":"","boxPrice":0,"cartNumber":0,"id":21,"image":"http://cdn.duoduofresh.com/upload/image/201812/3150ec84-9d16-4e0d-849e-c150d034a408.jpg","isGift":false,"memo":"","name":"精品西红柿","packaging":"泡沫箱","price":200,"promotionPrice":100,"specificationNumber":0,"stock":222,"tag":"","unit":"斤","unitPrice":200,"weight":1}]
     * size :
     * smallMobileImage : http://cdn.duoduofresh.com/upload/image/201811/c64ffa1b-655c-49c7-88ba-262bc17f46e5.jpg
     */

    private String beginDate;
    private String endDate;
    private int id;
    private String introduction;
    private String mobileImage;
    private String name;
    private String size;
    private String smallMobileImage;
    private List<ProductInfoListBean> productInfoList;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSmallMobileImage() {
        return smallMobileImage;
    }

    public void setSmallMobileImage(String smallMobileImage) {
        this.smallMobileImage = smallMobileImage;
    }

    public List<ProductInfoListBean> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfoListBean> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public static class ProductInfoListBean {
        /**
         * boxCode :
         * boxPrice : 0
         * cartNumber : 0
         * id : 45
         * image : http://cdn.duoduofresh.com/upload/image/201812/9e534f36-ca39-4797-83bc-a077364474f4.jpg
         * isGift : false
         * memo :
         * name : 贝贝小番茄
         * packaging : 泡沫箱
         * price : 185
         * promotionPrice : 92.5
         * specificationNumber : 0
         * stock : 20
         * tag :
         * unit : 斤
         * unitPrice : 7.4
         * weight : 25
         */

        private String boxCode;
        private double boxPrice;
        private int cartNumber;
        private int id;
        private String image;
        private boolean isGift;
        private String memo;
        private String name;
        private String packaging;
        private double price;
        private double promotionPrice;
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

        public double getBoxPrice() {
            return boxPrice;
        }

        public void setBoxPrice(double boxPrice) {
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

        public double getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(double promotionPrice) {
            this.promotionPrice = promotionPrice;
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
