package com.dodo.marcket.bean;

import java.util.List;
/**
 *一级列表对象
 */
public class FirstClassfyBean {


    /**
     * id : 79
     * name : 蔬菜
     * products : []
     * showType :
     * subProductCategory : [{"id":80,"name":"新鲜蔬菜","products":[],"showType":"","subProductCategory":[]},
     * {"id":82,"name":"时令蔬菜","products":[],"showType":"","subProductCategory":[]}]
     */
    private boolean isFirstSelected = false;
    private boolean isShowList = false;
    private int id;
    private String name;
    private String showType;
    private List<?> products;
    private List<SubProductCategoryBean> subProductCategory;

    public boolean isShowList() {
        return isShowList;
    }

    public void setShowList(boolean showList) {
        isShowList = showList;
    }

    public boolean isFirstSelected() {
        return isFirstSelected;
    }

    public void setFirstSelected(boolean firstSelected) {
        isFirstSelected = firstSelected;
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

    public List<?> getProducts() {
        return products;
    }

    public void setProducts(List<?> products) {
        this.products = products;
    }

    public List<SubProductCategoryBean> getSubProductCategory() {
        return subProductCategory;
    }

    public void setSubProductCategory(List<SubProductCategoryBean> subProductCategory) {
        this.subProductCategory = subProductCategory;
    }

    public static class SubProductCategoryBean {
        /**
         * id : 80
         * name : 新鲜蔬菜
         * products : []
         * showType :
         * subProductCategory : []
         */
        private boolean isSelected;
        private int id;
        private String name;
        private String showType;
        private List<?> products;
        private List<?> subProductCategory;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
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

        public List<?> getProducts() {
            return products;
        }

        public void setProducts(List<?> products) {
            this.products = products;
        }

        public List<?> getSubProductCategory() {
            return subProductCategory;
        }

        public void setSubProductCategory(List<?> subProductCategory) {
            this.subProductCategory = subProductCategory;
        }
    }
}
