package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class AddressBean {

    /**
     * code : 0
     * data : [{"adds":"佳通路1000号","code":"000000","id":"1","moren":"1","name":"石韧","qu":"嘉定区","sheng":"上海市","shi":"上海市","tel":"1582197261","time":"1","user_id":"352"},{"adds":"佳通路1000号","code":"000000","id":"2","moren":"0","name":"123","qu":"嘉定区","sheng":"上海市","shi":"上海市","tel":"1582197261","time":"2","user_id":"352"},{"adds":"佳通路1000号","code":"000000","id":"3","moren":"0","name":"456","qu":"嘉定区","sheng":"上海市","shi":"上海市","tel":"1582197261","time":"3","user_id":"352"}]
     * msg : 成功
     * num : 3
     */

    private String code;
    private String msg;
    private int num;
    /**
     * adds : 佳通路1000号
     * code : 000000
     * id : 1
     * moren : 1
     * name : 石韧
     * qu : 嘉定区
     * sheng : 上海市
     * shi : 上海市
     * tel : 1582197261
     * time : 1
     * user_id : 352
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String adds;
        private String code;
        private String id;
        private String moren;
        private String name;
        private String qu;
        private String sheng;
        private String shi;
        private String tel;
        private String time;
        private String user_id;

        public String getAdds() {
            return adds;
        }

        public void setAdds(String adds) {
            this.adds = adds;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQu() {
            return qu;
        }

        public void setQu(String qu) {
            this.qu = qu;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
