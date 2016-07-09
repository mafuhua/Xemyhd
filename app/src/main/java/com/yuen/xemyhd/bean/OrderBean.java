package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class OrderBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"31","order_id":"1464177694873355","pro_id":"50","name":"u嘟嘟符合等你呢","price":"21515.00","image":"product/201605/1462880105-25814.jpg","num":"1","time":"1464177694"},{"id":"32","order_id":"1464177694873355","pro_id":"47","name":"新的一天","price":"12.00","image":"product/201604/1461650543-56538.jpg","num":"1","time":"1464177694"},{"id":"33","order_id":"1464177694873355","pro_id":"26","name":"呵呵","price":"1.00","image":"product/201604/1461118646-51992.jpg","num":"1","time":"1464177694"},{"id":"34","order_id":"1464177694873355","pro_id":"28","name":"你好","price":"13.00","image":"product/201604/1461119066-43217.jpg","num":"1","time":"1464177694"},{"id":"35","order_id":"1464177694873355","pro_id":"27","name":"感觉","price":"58.00","image":"product/201604/1461118832-28003.jpg","num":"1","time":"1464177694"}]
     * add_data : {"id":"10","user_id":"352","sheng":"重庆","shi":"重庆","qu":"开县","adds":"它记录了哦啦啦啦啦啦","name":"，bal","tel":"1528866","code":"1288","moren":"1","time":"1462434889"}
     * order_list : {"order_id":"1464177694873355","user_id":"352","shop_id":"1","adds_id":"10","price":"0.01","type":"1","time":"1464177694","fa_time":"1464253432"}
     */

    private String code;
    private String msg;
    /**
     * id : 10
     * user_id : 352
     * sheng : 重庆
     * shi : 重庆
     * qu : 开县
     * adds : 它记录了哦啦啦啦啦啦
     * name : ，bal
     * tel : 1528866
     * code : 1288
     * moren : 1
     * time : 1462434889
     */

    private AddDataBean add_data;
    /**
     * order_id : 1464177694873355
     * user_id : 352
     * shop_id : 1
     * adds_id : 10
     * price : 0.01
     * type : 1
     * time : 1464177694
     * fa_time : 1464253432
     */

    private OrderListBean order_list;
    /**
     * id : 31
     * order_id : 1464177694873355
     * pro_id : 50
     * name : u嘟嘟符合等你呢
     * price : 21515.00
     * image : product/201605/1462880105-25814.jpg
     * num : 1
     * time : 1464177694
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

    public AddDataBean getAdd_data() {
        return add_data;
    }

    public void setAdd_data(AddDataBean add_data) {
        this.add_data = add_data;
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

    public static class AddDataBean {
        private String id;
        private String user_id;
        private String sheng;
        private String shi;
        private String qu;
        private String adds;
        private String name;
        private String tel;
        private String code;
        private String moren;
        private String time;

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

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getQu() {
            return qu;
        }

        public void setQu(String qu) {
            this.qu = qu;
        }

        public String getAdds() {
            return adds;
        }

        public void setAdds(String adds) {
            this.adds = adds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class OrderListBean {
        private String order_id;
        private String user_id;
        private String shop_id;
        private String adds_id;
        private String price;
        private String type;
        private String time;
        private String fa_time;

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

        public String getAdds_id() {
            return adds_id;
        }

        public void setAdds_id(String adds_id) {
            this.adds_id = adds_id;
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

        public String getFa_time() {
            return fa_time;
        }

        public void setFa_time(String fa_time) {
            this.fa_time = fa_time;
        }
    }

    public static class DataBean {
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
