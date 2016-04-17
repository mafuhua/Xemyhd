package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/16.
 */
public class TestGouwuche {


    /**
     * code : 0
     * msg : 鎴愬姛
     * data : [{"id":"1","shop_id":"1","user_id":"1","pro_id":"1","name":"1鍙峰簵","pro":[{"pro_name":"1","id":"1","num":1,"price":"4.00","pro_inventory":"12"}]},{"id":"2","shop_id":"5","user_id":"1","pro_id":"5,6","name":"2鍙峰簵","pro":[{"pro_name":"渚垮埄璐�1","id":"5","num":1,"price":"1.00","pro_inventory":"4"},{"pro_name":"渚垮埄璐�2","id":"6","num":1,"price":"2.00","pro_inventory":"5"}]},{"id":"3","shop_id":"6","user_id":"1","pro_id":"9,10,11","name":"3鍙峰簵","pro":[{"pro_name":"Fyrgg 1","id":"9","num":1,"price":"5.00","pro_inventory":"8"},{"pro_name":"Fyrgg 2","id":"10","num":1,"price":"6.00","pro_inventory":"9"},{"pro_name":"Fyrgg 3","id":"11","num":1,"price":"7.00","pro_inventory":"12"}]}]
     */

    public String code;
    public String msg;
    /**
     * id : 1
     * shop_id : 1
     * user_id : 1
     * pro_id : 1
     * name : 1鍙峰簵
     * pro : [{"pro_name":"1","id":"1","num":1,"price":"4.00","pro_inventory":"12"}]
     */

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
        /**
         * pro_name : 1
         * id : 1
         * num : 1
         * price : 4.00
         * pro_inventory : 12
         */

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
    }
}
