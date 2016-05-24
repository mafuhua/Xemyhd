package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class OrderListBean {


    /**
     * code : 0
     * data : [{"adds_id":"10","order_id":"1463797361649893","price":"152.00","pro":[{"id":"17","image":"http://192.168.2.136/xiaoermei/upload/product/201604/1460777662-22766.jpg","name":"4","num":"1","order_id":"1463797361649893","price":"152.00","pro_id":"13","time":"1463797361"}],"shop_id":"1","shop_title":"家乐福","time":"1463797361","type":"2","user_id":"1"},{"adds_id":"10","order_id":"1463797361344610","price":"56.00","pro":[{"id":"18","image":"http://192.168.2.136/xiaoermei/upload/product/201604/1460777662-22766.jpg","name":"3","num":"8","order_id":"1463797361344610","price":"1.00","pro_id":"10","time":"1463797361"},{"id":"19","image":"http://192.168.2.136/xiaoermei/upload/product/201604/1460777662-22766.jpg","name":"3","num":"9","order_id":"1463797361344610","price":"2.00","pro_id":"11","time":"1463797361"},{"id":"20","image":"http://192.168.2.136/xiaoermei/upload/product/201604/1460777662-22766.jpg","name":"3","num":"10","order_id":"1463797361344610","price":"3.00","pro_id":"12","time":"1463797361"}],"shop_id":"6","shop_title":"家乐福1","time":"1463797361","type":"0","user_id":"1"}]
     * msg : 成功
     */

    private String code;
    private String msg;
    /**
     * adds_id : 10
     * order_id : 1463797361649893
     * price : 152.00
     * pro : [{"id":"17","image":"http://192.168.2.136/xiaoermei/upload/product/201604/1460777662-22766.jpg","name":"4","num":"1","order_id":"1463797361649893","price":"152.00","pro_id":"13","time":"1463797361"}]
     * shop_id : 1
     * shop_title : 家乐福
     * time : 1463797361
     * type : 2
     * user_id : 1
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
        private String adds_id;
        private String order_id;
        private String price;
        private String shop_id;
        private String shop_title;
        private String time;
        private String type;
        private String user_id;
        /**
         * id : 17
         * image : http://192.168.2.136/xiaoermei/upload/product/201604/1460777662-22766.jpg
         * name : 4
         * num : 1
         * order_id : 1463797361649893
         * price : 152.00
         * pro_id : 13
         * time : 1463797361
         */

        private List<ProBean> pro;

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

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
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

        public List<ProBean> getPro() {
            return pro;
        }

        public void setPro(List<ProBean> pro) {
            this.pro = pro;
        }

        public static class ProBean {
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
}
