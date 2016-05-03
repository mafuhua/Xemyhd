package com.yuen.xemyhd.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class LoginBean {


    /**
     * code : 0
     * msg : 成功
     * data : {"uid":"352","email":"","nickname":"","tel":"15821972611","pwd":"123456","avatar":"","jifen":"0","birthday":"","sex":"1","address":"","province":"","city":"","intr":"","phone":"","fax":"","qq":"0","login_time":"0"}
     */

    private String code;
    private String msg;
    /**
     * uid : 352
     * email :
     * nickname :
     * tel : 15821972611
     * pwd : 123456
     * avatar :
     * jifen : 0
     * birthday :
     * sex : 1
     * address :
     * province :
     * city :
     * intr :
     * phone :
     * fax :
     * qq : 0
     * login_time : 0
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
        private String uid;
        private String email;
        private String nickname;
        private String tel;
        private String pwd;
        private String avatar;
        private String jifen;
        private String birthday;
        private String sex;
        private String address;
        private String province;
        private String city;
        private String intr;
        private String phone;
        private String fax;
        private String qq;
        private String login_time;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIntr() {
            return intr;
        }

        public void setIntr(String intr) {
            this.intr = intr;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }
    }
}
