package com.yuen.xemyhd.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MarketListBean {

    /**
     * code : 0
     * data : [{"id":"1","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"南翔镇","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"家乐福","user_id":"1"},{"id":"2","img":"http://192.168.2.117/xiaoermei/upload/user/201604/1460428785-92315.jpg","shop_adds":"平城路","shop_img":"user/201604/1460428785-92315.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"一号店","user_id":"5"},{"id":"10","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"真南路","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"四号店","user_id":"11"},{"id":"11","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"八号店","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"八号店","user_id":"14"},{"id":"12","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"七号","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"七号店","user_id":"13"},{"id":"13","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"十号店","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"十号店","user_id":"16"},{"id":"14","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"三号","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"三号店","user_id":"10"},{"id":"15","img":"http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg","shop_adds":"二号","shop_img":"user/201605/1462877695-34178.jpg","shop_qu":"嘉定区","shop_sheng":"上海市","shop_shi":"上海市","shop_title":"二号店","user_id":"9"}]
     * msg : 成功
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * img : http://192.168.2.117/xiaoermei/upload/user/201605/1462877695-34178.jpg
     * shop_adds : 南翔镇
     * shop_img : user/201605/1462877695-34178.jpg
     * shop_qu : 嘉定区
     * shop_sheng : 上海市
     * shop_shi : 上海市
     * shop_title : 家乐福
     * user_id : 1
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String img;
        private String shop_adds;
        private String shop_img;
        private String shop_qu;
        private String shop_sheng;
        private String shop_shi;
        private String shop_title;
        private String user_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getShop_adds() {
            return shop_adds;
        }

        public void setShop_adds(String shop_adds) {
            this.shop_adds = shop_adds;
        }

        public String getShop_img() {
            return shop_img;
        }

        public void setShop_img(String shop_img) {
            this.shop_img = shop_img;
        }

        public String getShop_qu() {
            return shop_qu;
        }

        public void setShop_qu(String shop_qu) {
            this.shop_qu = shop_qu;
        }

        public String getShop_sheng() {
            return shop_sheng;
        }

        public void setShop_sheng(String shop_sheng) {
            this.shop_sheng = shop_sheng;
        }

        public String getShop_shi() {
            return shop_shi;
        }

        public void setShop_shi(String shop_shi) {
            this.shop_shi = shop_shi;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
