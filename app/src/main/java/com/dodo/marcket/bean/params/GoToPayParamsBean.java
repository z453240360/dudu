package com.dodo.marcket.bean.params;

import com.dodo.marcket.bean.CartItemsBean;
import com.dodo.marcket.bean.DisCountBean;

import java.util.List;
import java.util.PropertyResourceBundle;

public class GoToPayParamsBean {
    private long receiverId = 0L;
    private List<CartItemParam> cartItemParam;//商品列表
    private List<DisCountBean> anHaos;
    private String receiverDate;//收货时间
    private Long paymentMethodId = 0L;//支付方式ID
    private double usePoint;//用户积分
    private long id = 0L;//订单号
    private String memo;//备注
    private String orderType = "android";//ios或者android
    private String giftId;//赠品ID
    private int status;//查询状态:0=全部,1=待付款,2=待发货,shipped=配送中,complete=已完成,cancel=已取消


    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public void setCartItemParam(List<CartItemParam> cartItemParam) {
        this.cartItemParam = cartItemParam;
    }

    public void setAnHaos(List<DisCountBean> anHaos) {
        this.anHaos = anHaos;
    }

    public void setReceiverDate(String receiverDate) {
        this.receiverDate = receiverDate;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public void setUsePoint(double usePoint) {
        this.usePoint = usePoint;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public long getReceiverId() {
        return receiverId;
    }

    public List<CartItemParam> getCartItemParam() {
        return cartItemParam;
    }

    public List<DisCountBean> getAnHaos() {
        return anHaos;
    }

    public String getReceiverDate() {
        return receiverDate;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public double getUsePoint() {
        return usePoint;
    }

    public long getId() {
        return id;
    }

    public String getMemo() {
        return memo;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getGiftId() {
        return giftId;
    }

    public int getStatus() {
        return status;
    }

    public static class CartItemParam{
        private ProductParam productParam;
        private long quantity;

        public ProductParam getProductParam() {
            return productParam;
        }

        public void setProductParam(ProductParam productParam) {
            this.productParam = productParam;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        public static class ProductParam{
            private long id;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }
        }
    }
}
