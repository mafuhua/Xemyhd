package com.yuen.xemyhd.bean;

/**
 * Created by Administrator on 2016/4/15.
 */
public class LoginBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"uid":"352","email":"","nickname":"","tel":"15821972611","pwd":"123456","avatar":"","birthday":"","sex":"0","address":null,"province":null,"city":null,"intr":null,"phone":null,"fax":null,"qq":null,"login_time":null}
     */

    public String code;
    public String msg;
    /**
     * uid : 352
     * email :
     * nickname :
     * tel : 15821972611
     * pwd : 123456
     * avatar :
     * birthday :
     * sex : 0
     * address : null
     * province : null
     * city : null
     * intr : null
     * phone : null
     * fax : null
     * qq : null
     * login_time : null
     */

    public DataBean data;

    public static class DataBean {
        public String uid;
        public String email;
        public String nickname;
        public String tel;
        public String pwd;
        public String avatar;
        public String birthday;
        public String sex;
        public Object address;
        public Object province;
        public Object city;
        public Object intr;
        public Object phone;
        public Object fax;
        public Object qq;
        public Object login_time;
    }
}
