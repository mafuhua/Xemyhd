package com.yuen.xemyhd.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class LoginBean {

    /**
     * code : 0
     * data : {"address":"","avatar":"avatar/201605/1462333026-45735.jpg","birthday":"","city":"","email":"","fax":"","img":"http://192.168.2.131/xiaoermei/upload/avatar/201605/1462333026-45735.jpg","intr":"","jifen":"0","login_time":"1462436686","nickname":"懒猫啊啦啊？黑龙江咯","phone":"","province":"","pwd":"123456","qq":"0","sex":"0","tel":"15821972611","token":"dc5ac7ad62617dcfb12c118c3fccdce47e636e4d","uid":"352"}
     * msg : 成功
     */

    private String code;
    /**
     * address :
     * avatar : avatar/201605/1462333026-45735.jpg
     * birthday :
     * city :
     * email :
     * fax :
     * img : http://192.168.2.131/xiaoermei/upload/avatar/201605/1462333026-45735.jpg
     * intr :
     * jifen : 0
     * login_time : 1462436686
     * nickname : 懒猫啊啦啊？黑龙江咯
     * phone :
     * province :
     * pwd : 123456
     * qq : 0
     * sex : 0
     * tel : 15821972611
     * token : dc5ac7ad62617dcfb12c118c3fccdce47e636e4d
     * uid : 352
     */

    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String address;
        private String avatar;
        private String birthday;
        private String city;
        private String email;
        private String fax;
        private String img;
        private String intr;
        private String jifen;
        private String login_time;
        private String nickname;
        private String phone;
        private String province;
        private String pwd;
        private String qq;
        private String sex;
        private String tel;
        private String token;
        private String uid;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIntr() {
            return intr;
        }

        public void setIntr(String intr) {
            this.intr = intr;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
