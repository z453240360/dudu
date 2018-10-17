package com.dodo.marcket.bean;


import java.util.List;

/**
 *具体商品对象
 */
public class ProductBean {

    private boolean isSelected = true;
    /**
     * cartNumber : 0
     * id : 6
     * image : http://s1.12chu.com/default/512-512.png
     * isGift : true
     * name : 正品1
     * packaging : 塑料箱
     * price : 1
     * specificationNumber : 0
     * stock : null                                           库存
     * unit :                                                 单位
     * unitPrice : 1                                          单价
     * weight : 1                                             重量
     */
    private String type;
    private int cartNumber;
    private long id;
    private String image;
    private boolean isGift;
    private String name;
    private String packaging;
    private double price;
    private int specificationNumber; // 0 单一规格  2 表示多规格
    private Integer stock;
    private String unit;
    private double unitPrice;
    private double weight;
    private String memo; //备注
    private String tag;
    private String fullName;
    private String introduction="";//介绍
    private List<String> productImages; //商品图片中中图
    private List<SpecificationsBean> specifications;//规格
    private boolean isShowNum = false;//是否显示数量
    private int showNum = 1;//自己添加购物车的数量

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public boolean isShowNum() {
        return isShowNum;
    }

    public void setShowNum(boolean showNum) {
        isShowNum = showNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public  List<SpecificationsBean> getSpecifications() {
        return specifications;
    }

    public void setSpecifications( List<SpecificationsBean> specifications) {
        this.specifications = specifications;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean gift) {
        isGift = gift;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
