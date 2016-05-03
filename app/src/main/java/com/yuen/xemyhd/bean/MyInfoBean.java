package com.yuen.xemyhd.bean;

/**
 * Created by Administrator on 2016/5/3.
 */
public class MyInfoBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"uid":"352","nickname":"","avatar":"","jifen":"0","sex":"1"}
     */

    private String code;
    private String msg;
    /**
     * uid : 352
     * nickname :
     * avatar :
     * jifen : 0
     * sex : 1
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
        private String nickname;
        private String avatar;
        private String jifen;
        private String sex;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        private String img;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
