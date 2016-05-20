package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/16.
 */
public class TestGouwuche {
    /**
     * code : 0
     * msg : 成功
     * data : [{"shop_title":"家乐福","shop_id":"1","pro":[{"id":"17","user_id":"352","shop_id":"1","pro_id":"50","pro_name":"u嘟嘟符合等你呢","pro_num":"15","pro_price":"21515.00","time":"1463647279","pro_inventory":"155","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201605/1462880105-25814.jpg"},{"id":"18","user_id":"352","shop_id":"1","pro_id":"47","pro_name":"新的一天","pro_num":"1","pro_price":"12.00","time":"1463646722","pro_inventory":"12","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1461650543-56538.jpg"},{"id":"19","user_id":"352","shop_id":"1","pro_id":"26","pro_name":"呵呵","pro_num":"3","pro_price":"1.00","time":"1463646727","pro_inventory":"0","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1461118646-51992.jpg"}]},{"shop_title":"一号店","shop_id":"5","pro":[{"id":"15","user_id":"352","shop_id":"5","pro_id":"2","pro_name":"2","pro_num":"4","pro_price":"4555.00","time":"1463554194","pro_inventory":"10","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460346615-89747.jpg"},{"id":"14","user_id":"352","shop_id":"5","pro_id":"1","pro_name":"1","pro_num":"10","pro_price":"4.00","time":"1463551886","pro_inventory":"10","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460346615-89747.jpg"}]},{"shop_title":"家乐福","shop_id":"6","pro":[{"id":"16","user_id":"352","shop_id":"6","pro_id":"13","pro_name":"3","pro_num":"1","pro_price":"2.01","time":null,"pro_inventory":"2","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1460777662-22766.jpg"}]}]
     */

    private String code;
    private String msg;
    /**
     * shop_title : 家乐福
     * shop_id : 1
     * pro : [{"id":"17","user_id":"352","shop_id":"1","pro_id":"50","pro_name":"u嘟嘟符合等你呢","pro_num":"15","pro_price":"21515.00","time":"1463647279","pro_inventory":"155","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201605/1462880105-25814.jpg"},{"id":"18","user_id":"352","shop_id":"1","pro_id":"47","pro_name":"新的一天","pro_num":"1","pro_price":"12.00","time":"1463646722","pro_inventory":"12","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1461650543-56538.jpg"},{"id":"19","user_id":"352","shop_id":"1","pro_id":"26","pro_name":"呵呵","pro_num":"3","pro_price":"1.00","time":"1463646727","pro_inventory":"0","pro_img":"http://192.168.2.117/xiaoermei/upload/product/201604/1461118646-51992.jpg"}]
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
        public boolean checkTitle;
        private String shop_title;
        private String shop_id;
        /**
         * id : 17
         * user_id : 352
         * shop_id : 1
         * pro_id : 50
         * pro_name : u嘟嘟符合等你呢
         * pro_num : 15
         * pro_price : 21515.00
         * time : 1463647279
         * pro_inventory : 155
         * pro_img : http://192.168.2.117/xiaoermei/upload/product/201605/1462880105-25814.jpg
         */

        private List<ProBean> pro;
        public boolean isCheckTitle() {
            return checkTitle;
        }

        public void setCheckTitle(boolean checkTitle) {
            this.checkTitle = checkTitle;
        }
        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public List<ProBean> getPro() {
            return pro;
        }

        public void setPro(List<ProBean> pro) {
            this.pro = pro;
        }

        public static class ProBean {
            private String id;
            private String user_id;
            private String shop_id;
            private String pro_id;
            private String pro_name;
            private String pro_num;
            private String pro_price;
            private String time;
            private String pro_inventory;
            private String pro_img;
            public boolean checkitem;

            public boolean isCheckitem() {
                return checkitem;
            }

            public void setCheckitem(boolean checkitem) {
                this.checkitem = checkitem;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getPro_id() {
                return pro_id;
            }

            public void setPro_id(String pro_id) {
                this.pro_id = pro_id;
            }

            public String getPro_name() {
                return pro_name;
            }

            public void setPro_name(String pro_name) {
                this.pro_name = pro_name;
            }

            public String getPro_num() {
                return pro_num;
            }

            public void setPro_num(String pro_num) {
                this.pro_num = pro_num;
            }

            public String getPro_price() {
                return pro_price;
            }

            public void setPro_price(String pro_price) {
                this.pro_price = pro_price;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPro_inventory() {
                return pro_inventory;
            }

            public void setPro_inventory(String pro_inventory) {
                this.pro_inventory = pro_inventory;
            }

            public String getPro_img() {
                return pro_img;
            }

            public void setPro_img(String pro_img) {
                this.pro_img = pro_img;
            }
        }
    }


/*
    public String code;
    public String msg;
    public List<DataBean> data;

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
        public String id;
        public String shop_id;
        public String user_id;
        public String pro_id;
        public String name;
        public boolean checkTitle;
        *//**
         * pro_name : 1
         * id : 1
         * num : 1
         * price : 4.00
         * pro_inventory : 12
         *//*

        public List<ProBean> pro;

        public boolean isCheckTitle() {
            return checkTitle;
        }

        public void setCheckTitle(boolean checkTitle) {
            this.checkTitle = checkTitle;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public List<ProBean> getPro() {
            return pro;
        }

        public void setPro(List<ProBean> pro) {
            this.pro = pro;
        }

        public static class ProBean {
            public String pro_name;
            public String id;
            public int num;
            public String price;
            public String shop_id;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String pro_inventory;
            public boolean checkitem;

            public boolean isCheckitem() {
                return checkitem;
            }

            public void setCheckitem(boolean checkitem) {
                this.checkitem = checkitem;
            }

            public String getPro_name() {
                return pro_name;
            }

            public void setPro_name(String pro_name) {
                this.pro_name = pro_name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPro_inventory() {
                return pro_inventory;
            }

            public void setPro_inventory(String pro_inventory) {
                this.pro_inventory = pro_inventory;
            }
        }
    }*/
}
