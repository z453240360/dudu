package com.dodo.marcket.bean;

import java.util.List;

public class SelectPostTimeBean {

    /**
     * selectDay : 2018-09-19
     * selectTimes : ["3:00--5:00","5:00--7:00","7:00--9:00","13:00--15:00","15:00--17:00"]
     */

    private String selectDay;
    private List<ItemBean> itemBeansList;
    private List<String> selectTimes;
    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSelectDay() {
        return selectDay;
    }

    public void setSelectDay(String selectDay) {
        this.selectDay = selectDay;
    }

    public List<ItemBean> getItemBeansList() {
        return itemBeansList;
    }

    public void setItemBeansList(List<ItemBean> itemBeansList) {
        this.itemBeansList = itemBeansList;
    }

    public List<String> getSelectTimes() {
        return selectTimes;
    }

    public void setSelectTimes(List<String> selectTimes) {
        this.selectTimes = selectTimes;
    }

    public static class ItemBean{
        private String name;
        private boolean isSelected;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
