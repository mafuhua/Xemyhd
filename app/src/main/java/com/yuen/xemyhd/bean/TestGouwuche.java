package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/16.
 */
public class TestGouwuche {


    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"1","shop_id":"1","user_id":"1","pro_id":"1","name":"1号店","pro":[{"pro_name":"1","id":"1","num":1,"price":"4.00","pro_inventory":"12"}]},{"id":"2","shop_id":"5","user_id":"1","pro_id":"5,6","name":"2号店","pro":[{"pro_name":"便利贴1","id":"5","num":1,"price":"1.00","pro_inventory":"4"},{"pro_name":"便利贴2","id":"6","num":1,"price":"2.00","pro_inventory":"5"}]},{"id":"3","shop_id":"6","user_id":"1","pro_id":"9,10,11","name":"3号店","pro":[{"pro_name":"Fyrgg 1","id":"9","num":1,"price":"5.00","pro_inventory":"8"},{"pro_name":"Fyrgg 2","id":"10","num":1,"price":"6.00","pro_inventory":"9"},{"pro_name":"Fyrgg 3","id":"11","num":1,"price":"7.00","pro_inventory":"12"}]}]
     */

    public String code;
    public String msg;
    /**
     * id : 1
     * shop_id : 1
     * user_id : 1
     * pro_id : 1
     * name : 1号店
     * pro : [{"pro_name":"1","id":"1","num":1,"price":"4.00","pro_inventory":"12"}]
     */

    public List<DataBean> data;

    public static class DataBean {
        public String id;
        public String shop_id;
        public String user_id;
        public String pro_id;
        public String name;
        /**
         * pro_name : 1
         * id : 1
         * num : 1
         * price : 4.00
         * pro_inventory : 12
         */

        public List<ProBean> pro;

        public static class ProBean {
            public String pro_name;
            public String id;
            public int num;
            public String price;
            public String pro_inventory;
        }
    }
}
