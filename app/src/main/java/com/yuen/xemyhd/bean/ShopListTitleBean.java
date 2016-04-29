package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ShopListTitleBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"1","pro_img":"http://192.168.2.131/xiaoermei/upload/product/201604/1460346615-89747.jpg","pro_name":"1","pro_price":"4.00","pro_h_price":"5.00"}]
     * t2_data : [{"id":"2","nav_name":"大家电","parent_id":"1"},{"id":"3","nav_name":"厨卫大电","parent_id":"1"},{"id":"4","nav_name":"厨房小电","parent_id":"1"},{"id":"5","nav_name":"生活电器","parent_id":"1"},{"id":"6","nav_name":"个护健康","parent_id":"1"},{"id":"7","nav_name":"五金家装","parent_id":"1"}]
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * pro_img : http://192.168.2.131/xiaoermei/upload/product/201604/1460346615-89747.jpg
     * pro_name : 1
     * pro_price : 4.00
     * pro_h_price : 5.00
     */

    private List<DataBean> data;
    /**
     * id : 2
     * nav_name : 大家电
     * parent_id : 1
     */

    private List<T2DataBean> t2_data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public List<T2DataBean> getT2_data() {
        return t2_data;
    }

    public void setT2_data(List<T2DataBean> t2_data) {
        this.t2_data = t2_data;
    }

    public static class DataBean {
        private String id;
        private String pro_img;
        private String pro_name;
        private String pro_price;
        private String pro_h_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPro_h_price() {
            return pro_h_price;
        }

        public void setPro_h_price(String pro_h_price) {
            this.pro_h_price = pro_h_price;
        }
    }


}
