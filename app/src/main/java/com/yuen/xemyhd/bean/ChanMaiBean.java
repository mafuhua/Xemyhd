package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ChanMaiBean {

    /**
     * code : 0
     * data : [{"id":"20","image":"product/201604/1460777662-22766.jpg","name":"3","num":"10","order_id":"1463797361344610","price":"3.00","pro_id":"12","time":"1463797361"},{"id":"18","image":"product/201604/1460777662-22766.jpg","name":"3","num":"8","order_id":"1463797361344610","price":"1.00","pro_id":"10","time":"1463797361"},{"id":"19","image":"product/201604/1460777662-22766.jpg","name":"3","num":"9","order_id":"1463797361344610","price":"2.00","pro_id":"11","time":"1463797361"}]
     * msg : 成功
     */

    private String code;
    private String msg;
    /**
     * id : 20
     * image : product/201604/1460777662-22766.jpg
     * name : 3
     * num : 10
     * order_id : 1463797361344610
     * price : 3.00
     * pro_id : 12
     * time : 1463797361
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
        private String image;
        private String name;
        private String num;
        private String order_id;
        private String price;
        private String pro_id;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
