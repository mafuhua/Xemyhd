package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class OrderBean2 {

    /**
     * code : 0
     * data : [{"id":"67","image":"http://192.168.0.149/xiaoermei/upload/product/201605/1462880105-25814.jpg","name":"u嘟嘟符合等你呢","num":"1","order_id":"1468047443593643","price":"21515.00","pro_id":"50","time":"1468047443"}]
     * msg : 成功
     * order_list : {"adds_id":"","order_id":"1468047443593643","price":"21515.00","shop_id":"1","time":"1468047443","type":"1","user_id":"352"}
     */

    private String code;
    private String msg;
    /**
     * adds_id :
     * order_id : 1468047443593643
     * price : 21515.00
     * shop_id : 1
     * time : 1468047443
     * type : 1
     * user_id : 352
     */

    private OrderListBean order_list;
    /**
     * id : 67
     * image : http://192.168.0.149/xiaoermei/upload/product/201605/1462880105-25814.jpg
     * name : u嘟嘟符合等你呢
     * num : 1
     * order_id : 1468047443593643
     * price : 21515.00
     * pro_id : 50
     * time : 1468047443
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

    public OrderListBean getOrder_list() {
        return order_list;
    }

    public void setOrder_list(OrderListBean order_list) {
        this.order_list = order_list;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OrderListBean {
        private String adds_id;
        private String order_id;
        private String price;
        private String shop_id;
        private String time;
        private String type;
        private String user_id;

        public String getAdds_id() {
            return adds_id;
        }

        public void setAdds_id(String adds_id) {
            this.adds_id = adds_id;
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

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
