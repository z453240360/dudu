package com.dodo.marcket.bean;


/**
 * banner对象
 *
 *
 *
 *
 *
 */
public class BannerBean {

    /**
     * id : 1
     * path :
     * title : 测试
     * type : product
     */

    private int id;
    private String path;
    private String title;
    private String type;
    private String value;



    /**
     * type 类型
     * product         获取path的值，跳转产品页面
     * promotion       获取path的值，跳转热销活动页面
     * procutcategory  获取path的值，跳转产品页面
     * web             获取path的值，显示web页
     * search          获取path的值，跳转搜索页面，并且显示path的搜索内容
     *
     *
     */


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
