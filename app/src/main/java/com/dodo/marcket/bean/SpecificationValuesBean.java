package com.dodo.marcket.bean;

/**\
 * 具体规格对象
 */
public class SpecificationValuesBean {

    private long id;
    private String name;
    private String image;
    private boolean currentSpecVal;//是否是当前的规格值


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isCurrentSpecVal() {
        return currentSpecVal;
    }

    public void setCurrentSpecVal(boolean currentSpecVal) {
        this.currentSpecVal = currentSpecVal;
    }
}
