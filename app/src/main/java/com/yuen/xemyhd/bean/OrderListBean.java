package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class OrderListBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"order_id":"1463991971371472","user_id":"352","shop_id":"1","price":"64545.00","type":"0","time":"1463991971","fu_time":null,"pro":[{"id":"21","order_id":"1463991971371472","pro_id":"50","name":"u嘟嘟符合等你呢","price":"21515.00","image":"http://192.168.2.133/xiaoermei/upload/product/201605/1462880105-25814.jpg","num":"3","time":"1463991971"}]},{"order_id":"1463991971385781","user_id":"352","shop_id":"5","price":"153.00","type":"0","time":"1463991971","fu_time":null,"pro":[{"id":"22","order_id":"1463991971385781","pro_id":"4","name":"4","price":"152.00","image":"http://192.168.2.133/xiaoermei/upload/product/201604/1460777662-22766.jpg","num":"1","time":"1463991971"},{"id":"23","order_id":"1463991971385781","pro_id":"5","name":"5","price":"1.00","image":"http://192.168.2.133/xiaoermei/upload/product/201604/1460777662-22766.jpg","num":"1","time":"1463991971"}]}]
     */

    private String code;
    private String msg;
    /**
     * order_id : 1463991971371472
     * user_id : 352
     * shop_id : 1
     * price : 64545.00
     * type : 0
     * time : 1463991971
     * fu_time : null
     * pro : [{"id":"21","order_id":"1463991971371472","pro_id":"50","name":"u嘟嘟符合等你呢","price":"21515.00","image":"http://192.168.2.133/xiaoermei/upload/product/201605/1462880105-25814.jpg","num":"3","time":"1463991971"}]
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
        private String order_id;
        private String user_id;
        private String shop_id;
        private String price;
        private String type;
        private String time;
        private Object fu_time;
        /**
         * id : 21
         * order_id : 1463991971371472
         * pro_id : 50
         * name : u嘟嘟符合等你呢
         * price : 21515.00
         * image : http://192.168.2.133/xiaoermei/upload/product/201605/1462880105-25814.jpg
         * num : 3
         * time : 1463991971
         */

        private List<ProBean> pro;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Object getFu_time() {
            return fu_time;
        }

        public void setFu_time(Object fu_time) {
            this.fu_time = fu_time;
        }

        public List<ProBean> getPro() {
            return pro;
        }

        public void setPro(List<ProBean> pro) {
            this.pro = pro;
        }

        public static class ProBean {
            private String id;
            private String order_id;
            private String pro_id;
            private String name;
            private String price;
            private String image;
            private String num;
            private String time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getPro_id() {
                return pro_id;
            }

            public void setPro_id(String pro_id) {
                this.pro_id = pro_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
