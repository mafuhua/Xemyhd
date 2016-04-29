package com.yuen.xemyhd.utils;

/**
 * Created by Administrator on 2016/4/15.
 */
public class ContactURL {
    /**
     * BaseURL
     */
    public static String BASE_URL = "http://192.168.2.131/xiaoermei/shopapi/";
    /**
     * 登陆
     */
    public static String LOGIN_URL = BASE_URL + "login";
    /**
     * 注册
     */
    public static String Rigester_URL = BASE_URL + "register";
    /**
     * 短信
     */
    public static String Duanxin_URL = BASE_URL + "duanxin";
    /**
     * 忘记密码确定
     */
    public static String FoegetPSW_URL = BASE_URL + "xiu_register";
    /**
     * 获取超市列表
     */
    public static String GetMarketList_URL = BASE_URL + "dingwei";
    /**
     * 获取商品列表
     */
    public static String GetShopList_URL = BASE_URL + "product/shop_user_id/";
    /**
     * 获取商品二级列表
     */
    public static String GetShopListTitle_URL = BASE_URL + "product2/shop_user_id/";
    /**
     * 获取商品二级商品列表
     */
    public static String GetShopListContent_URL = BASE_URL + "product3/shop_user_id/";
}
