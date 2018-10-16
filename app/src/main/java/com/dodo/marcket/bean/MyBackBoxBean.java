package com.dodo.marcket.bean;

import java.util.List;

/**
 * 退框对象
 */
public class MyBackBoxBean {


    /**
     * list : [{"createDate":"2018-10-16 14:20:03","items":[{"boxs":[{"boxCode":"毛重20斤","boxId":2,"boxName":"毛重20斤（允许1斤误差）","boxPrice":0,"returnQty":1,"totalPrice":0}],"orderDate":"2018-10-16 10:20:06","orderId":115,"orderSn":"SO20181016293795"}],"sn":"T2018101630459","status":"未确认 ","totlePrice":0,"totleQty":1},{"createDate":"2018-10-16 14:18:45","items":[{"boxs":[{"boxCode":"毛重20斤","boxId":2,"boxName":"毛重20斤（允许1斤误差）","boxPrice":0,"returnQty":1,"totalPrice":0}],"orderDate":"2018-10-16 10:20:06","orderId":115,"orderSn":"SO20181016293795"}],"sn":"T2018101630323","status":"未确认 ","totlePrice":0,"totleQty":1}]
     * total : 2
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * createDate : 2018-10-16 14:20:03
         * items : [{"boxs":[{"boxCode":"毛重20斤","boxId":2,"boxName":"毛重20斤（允许1斤误差）","boxPrice":0,"returnQty":1,"totalPrice":0}],"orderDate":"2018-10-16 10:20:06","orderId":115,"orderSn":"SO20181016293795"}]
         * sn : T2018101630459
         * status : 未确认
         * totlePrice : 0
         * totleQty : 1
         */

        private String createDate;
        private String sn;
        private String status;
        private double totlePrice;
        private int totleQty;
        private List<ItemsBean> items;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getTotlePrice() {
            return totlePrice;
        }

        public void setTotlePrice(double totlePrice) {
            this.totlePrice = totlePrice;
        }

        public int getTotleQty() {
            return totleQty;
        }

        public void setTotleQty(int totleQty) {
            this.totleQty = totleQty;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * boxs : [{"boxCode":"毛重20斤","boxId":2,"boxName":"毛重20斤（允许1斤误差）","boxPrice":0,"returnQty":1,"totalPrice":0}]
             * orderDate : 2018-10-16 10:20:06
             * orderId : 115
             * orderSn : SO20181016293795
             */

            private String orderDate;
            private int orderId;
            private String orderSn;
            private List<BoxsBean> boxs;

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }

            public List<BoxsBean> getBoxs() {
                return boxs;
            }

            public void setBoxs(List<BoxsBean> boxs) {
                this.boxs = boxs;
            }

            public static class BoxsBean {
                /**
                 * boxCode : 毛重20斤
                 * boxId : 2
                 * boxName : 毛重20斤（允许1斤误差）
                 * boxPrice : 0
                 * returnQty : 1
                 * totalPrice : 0
                 */

                private String boxCode;
                private int boxId;
                private String boxName;
                private double boxPrice;
                private int returnQty;
                private double totalPrice;

                public String getBoxCode() {
                    return boxCode;
                }

                public void setBoxCode(String boxCode) {
                    this.boxCode = boxCode;
                }

                public int getBoxId() {
                    return boxId;
                }

                public void setBoxId(int boxId) {
                    this.boxId = boxId;
                }

                public String getBoxName() {
                    return boxName;
                }

                public void setBoxName(String boxName) {
                    this.boxName = boxName;
                }

                public double getBoxPrice() {
                    return boxPrice;
                }

                public void setBoxPrice(double boxPrice) {
                    this.boxPrice = boxPrice;
                }

                public int getReturnQty() {
                    return returnQty;
                }

                public void setReturnQty(int returnQty) {
                    this.returnQty = returnQty;
                }

                public double getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(double totalPrice) {
                    this.totalPrice = totalPrice;
                }
            }
        }
    }
}
