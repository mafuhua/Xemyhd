package com.yuen.xemyhd.bean;

/**
 * Created by Administrator on 2016/5/3.
 */
public class AddresBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"1","user_id":"352","sheng":"上海市","shi":"上海市","qu":"嘉定区","adds":"佳通路1000号","name":"石韧","tel":"1582197261","code":"000000","moren":"1","time":"1"}
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * user_id : 352
     * sheng : 上海市
     * shi : 上海市
     * qu : 嘉定区
     * adds : 佳通路1000号
     * name : 石韧
     * tel : 1582197261
     * code : 000000
     * moren : 1
     * time : 1
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
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
}
