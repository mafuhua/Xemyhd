package com.yuen.xemyhd.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/4.
 */
public class WXBean {


    /**
     * code : 0
     * data : {"appid":"wxcab1c0202ebea846","noncestr":"jpnh3d8bj18wuhb4r45o358p30lcn4yh","package":"Sign=WXPay","partnerid":"1361691202","prepayid":"wx20160706151827691585a8d90759100041","sign":"8BDE228AC895E0B1356EB56B105144A0","timestamp":1467789505}
     * msg : 成功
     */

    private String code;
    /**
     * appid : wxcab1c0202ebea846
     * noncestr : jpnh3d8bj18wuhb4r45o358p30lcn4yh
     * package : Sign=WXPay
     * partnerid : 1361691202
     * prepayid : wx20160706151827691585a8d90759100041
     * sign : 8BDE228AC895E0B1356EB56B105144A0
     * timestamp : 1467789505
     */

    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private int timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}
