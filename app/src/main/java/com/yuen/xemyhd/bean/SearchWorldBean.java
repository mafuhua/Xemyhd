package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class SearchWorldBean {

    /**
     * code : 0
     * data : [{"id":"15","pro_h_price":"0.00","pro_img":"http://192.168.2.117/xiaoermei/upload/","pro_kg":"","pro_name":"15","pro_price":"128.00","pro_xiao":"0"},{"id":"10","pro_h_price":"0.00","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460777662-22766.jpg","pro_kg":"","pro_name":"10","pro_price":"6.00","pro_xiao":"0"},{"id":"11","pro_h_price":"0.00","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460777662-22766.jpg","pro_kg":"","pro_name":"11","pro_price":"7.00","pro_xiao":"0"},{"id":"12","pro_h_price":"0.00","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460777662-22766.jpg","pro_kg":"","pro_name":"12","pro_price":"8.00","pro_xiao":"0"},{"id":"13","pro_h_price":"0.00","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460777662-22766.jpg","pro_kg":"","pro_name":"14","pro_price":"100.00","pro_xiao":"0"}]
     * msg : 成功
     */

    private String code;
    private String msg;
    /**
     * id : 15
     * pro_h_price : 0.00
     * pro_img : http://192.168.2.117/xiaoermei/upload/
     * pro_kg :
     * pro_name : 15
     * pro_price : 128.00
     * pro_xiao : 0
     */

    private List<DataBean> data;

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

    public static class DataBean {
        private String id;
        private String pro_h_price;
        private String pro_img;
        private String pro_kg;
        private String pro_name;
        private String pro_price;
        private String pro_xiao;

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

        public String getPro_kg() {
            return pro_kg;
        }

        public void setPro_kg(String pro_kg) {
            this.pro_kg = pro_kg;
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

        public String getPro_xiao() {
            return pro_xiao;
        }

        public void setPro_xiao(String pro_xiao) {
            this.pro_xiao = pro_xiao;
        }
    }
}
