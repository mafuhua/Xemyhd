package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/29.
 */
public class ShopListEmptyBean {

    /**
     * code : 1
     * msg : 失败
     * data :
     * t2_data : [{"id":"265","nav_name":"厨具","parent_id":"264"},{"id":"266","nav_name":"家装建材","parent_id":"264"},{"id":"267","nav_name":"家纺","parent_id":"264"},{"id":"268","nav_name":"家具","parent_id":"264"},{"id":"269","nav_name":"灯具","parent_id":"264"},{"id":"270","nav_name":"生活日用","parent_id":"264"},{"id":"271","nav_name":"家装软饰","parent_id":"264"},{"id":"272","nav_name":"宠物生活","parent_id":"264"}]
     */

    private String code;
    private String msg;
    private String data;
    /**
     * id : 265
     * nav_name : 厨具
     * parent_id : 264
     */

    private List<T2DataBean> t2_data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<T2DataBean> getT2_data() {
        return t2_data;
    }

    public void setT2_data(List<T2DataBean> t2_data) {
        this.t2_data = t2_data;
    }


}
