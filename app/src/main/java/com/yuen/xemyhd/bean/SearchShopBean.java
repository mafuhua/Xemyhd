package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class SearchShopBean {

    /**
     * code : 0
     * data : [{"id":"47","pro_h_price":"12.00","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1461650543-56538.jpg","pro_name":"新的一天","pro_price":"12.00"}]
     * data_user : {"shop_etime":"23","shop_time":"1","user_id":"1"}
     * msg : 成功
     * t_data : [{"id":"1","nav_name":"家用电器","parent_id":"0"},{"id":"92","nav_name":"手机数码","parent_id":"0"},{"id":"173","nav_name":"电脑办公","parent_id":"0"},{"id":"264","nav_name":"家居家具","parent_id":"0"},{"id":"368","nav_name":"男装女装","parent_id":"0"},{"id":"491","nav_name":"个护化妆","parent_id":"0"},{"id":"541","nav_name":"鞋靴箱包","parent_id":"0"},{"id":"631","nav_name":"运动户外","parent_id":"0"},{"id":"742","nav_name":"母婴","parent_id":"0"},{"id":"836","nav_name":"食品酒类","parent_id":"0"}]
     */

    private String code;
    /**
     * shop_etime : 23
     * shop_time : 1
     * user_id : 1
     */

    private DataUserBean data_user;
    private String msg;
    /**
     * id : 47
     * pro_h_price : 12.00
     * pro_img : http://192.168.2.117/xiaoermei/upload/product/201604/1461650543-56538.jpg
     * pro_name : 新的一天
     * pro_price : 12.00
     */

    private List<DataBean> data;
    /**
     * id : 1
     * nav_name : 家用电器
     * parent_id : 0
     */

    private List<TDataBean> t_data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataUserBean getData_user() {
        return data_user;
    }

    public void setData_user(DataUserBean data_user) {
        this.data_user = data_user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<TDataBean> getT_data() {
        return t_data;
    }

    public void setT_data(List<TDataBean> t_data) {
        this.t_data = t_data;
    }

    public static class DataUserBean {
        private String shop_etime;
        private String shop_time;
        private String user_id;

        public String getShop_etime() {
            return shop_etime;
        }

        public void setShop_etime(String shop_etime) {
            this.shop_etime = shop_etime;
        }

        public String getShop_time() {
            return shop_time;
        }

        public void setShop_time(String shop_time) {
            this.shop_time = shop_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }

    public static class DataBean {
        private String id;
        private String pro_h_price;
        private String pro_img;
        private String pro_name;
        private String pro_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPro_h_price() {
            return pro_h_price;
        }

        public void setPro_h_price(String pro_h_price) {
            this.pro_h_price = pro_h_price;
        }

        public String getPro_img() {
            return pro_img;
        }

        public void setPro_img(String pro_img) {
            this.pro_img = pro_img;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }
    }

    public static class TDataBean {
        private String id;
        private String nav_name;
        private String parent_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNav_name() {
            return nav_name;
        }

        public void setNav_name(String nav_name) {
            this.nav_name = nav_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
