package com.dodo.marcket.bean;

public class AliPayBean {


    /**
     * aliPayResult : {"orderInfo":"alipay_sdk=alipay-sdk-java-3.3.49.ALL&app_id=2018081060957707&biz_content=%7B%22body%22%3A%22%E5%93%86%E5%93%86%E7%94%9F%E9%B2%9C%E8%AE%A2%E5%8D%95%3ASO20180920161894%22%2C%22out_trade_no%22%3A%22SO20180920161894%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%93%86%E5%93%86%E7%94%9F%E9%B2%9C%E8%AE%A2%E5%8D%95%3ASO20180920161894%22%2C%22total_amount%22%3A%22128454.70%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapi.duoduofresh.com%2F%2Fapi%2Fnotify%2Falipay.jhtml&sign=cy1kzTlhRWnZqixVK%2Fz1igtED%2FsVdPgzuTHdaSftin0nGIP6Gr%2FoTvkJ1%2FrSV0pPnKHgso4p8RSPAb7%2F%2FbLbsGvVDaxPuDufJBPvDmLSBETwvyCR3nNBAWi06ayCSKsYIqde4qYirb96ib9VvOLP6%2FQo42IvbtIqEz6vNmKs9YJwenktYJsk%2FYJDQveUrnLIZZDcyiMaM5erb2QcLn2DLZToRUskhfzhItWISOyxwiU%2F%2FVJphJryeRNH0jS09z3dYTX6utqA1ADgWUh5XGPcZsVE18pwlUv4pXFL0YCIMp%2BfKJ73jOH1rM42q4ihFo%2BIrhPdHaYXanEJxxAZnujBUQ%3D%3D&sign_type=RSA2&timestamp=2018-09-20+16%3A11%3A16&version=1.0"}
     * paymentMethonCode : alipay
     * paymentMethonName : 支付宝
     * wxPayResult : null
     */

    private AliPayResultBean aliPayResult;
    private String paymentMethonCode;
    private String paymentMethonName;
    private WxPayResult wxPayResult;

    public AliPayResultBean getAliPayResult() {
        return aliPayResult;
    }

    public void setAliPayResult(AliPayResultBean aliPayResult) {
        this.aliPayResult = aliPayResult;
    }

    public String getPaymentMethonCode() {
        return paymentMethonCode;
    }

    public void setPaymentMethonCode(String paymentMethonCode) {
        this.paymentMethonCode = paymentMethonCode;
    }

    public String getPaymentMethonName() {
        return paymentMethonName;
    }

    public void setPaymentMethonName(String paymentMethonName) {
        this.paymentMethonName = paymentMethonName;
    }

    public WxPayResult getWxPayResult() {
        return wxPayResult;
    }

    public void setWxPayResult(WxPayResult wxPayResult) {
        this.wxPayResult = wxPayResult;
    }

    public static class AliPayResultBean {
        /**
         * orderInfo : alipay_sdk=alipay-sdk-java-3.3.49.ALL&app_id=2018081060957707&biz_content=%7B%22body%22%3A%22%E5%93%86%E5%93%86%E7%94%9F%E9%B2%9C%E8%AE%A2%E5%8D%95%3ASO20180920161894%22%2C%22out_trade_no%22%3A%22SO20180920161894%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%93%86%E5%93%86%E7%94%9F%E9%B2%9C%E8%AE%A2%E5%8D%95%3ASO20180920161894%22%2C%22total_amount%22%3A%22128454.70%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapi.duoduofresh.com%2F%2Fapi%2Fnotify%2Falipay.jhtml&sign=cy1kzTlhRWnZqixVK%2Fz1igtED%2FsVdPgzuTHdaSftin0nGIP6Gr%2FoTvkJ1%2FrSV0pPnKHgso4p8RSPAb7%2F%2FbLbsGvVDaxPuDufJBPvDmLSBETwvyCR3nNBAWi06ayCSKsYIqde4qYirb96ib9VvOLP6%2FQo42IvbtIqEz6vNmKs9YJwenktYJsk%2FYJDQveUrnLIZZDcyiMaM5erb2QcLn2DLZToRUskhfzhItWISOyxwiU%2F%2FVJphJryeRNH0jS09z3dYTX6utqA1ADgWUh5XGPcZsVE18pwlUv4pXFL0YCIMp%2BfKJ73jOH1rM42q4ihFo%2BIrhPdHaYXanEJxxAZnujBUQ%3D%3D&sign_type=RSA2&timestamp=2018-09-20+16%3A11%3A16&version=1.0
         */

        private String orderInfo;

        public String getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
        }
    }

    public static class WxPayResult{
        private String appId;
        private String nonceStr;
        private String packageValue;
        private String partnerId;
        private String prepayId;
        private String sign;
        private String timeStamp;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageValue() {
            return packageValue;
        }

        public void setPackageValue(String packageValue) {
            this.packageValue = packageValue;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }
    }
}
