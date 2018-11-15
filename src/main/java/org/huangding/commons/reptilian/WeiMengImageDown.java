package org.huangding.commons.reptilian;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * @author huangding
 * @description
 * @date 2018/11/9 9:17
 */
public class WeiMengImageDown {


    private static String weiMengCookie() {
        String cookie =
            BaseUtil.returnCookies("http://100000063048.retail.n.weimob.com")+"; saas.express.session=s%3A_EILevV6ncORaEvdxkhr8WXsTKMy3wOn.AGwSzOLiDd7ybz%2Bg095E8FxfAwbWVPqsULjLx3o1tHQ; rprm_cuid=154172861616464; rprm_appshow=-15417286164000.01586946812327794; rprm_pv="+System.currentTimeMillis()+"-goodsdetail";
        return cookie;
    }


    public static String goWeiMenPostHttp(String url, Map<String, String> map) {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0";

        try {
            Connection conn = Jsoup.connect(
                "http://100000063048.retail.n.weimob.com/api3/ec/navigation/goods/getGoodsDetail");

//            String cookie = BaseUtil.returnCookies("http://100000063048.retail.n.weimob.com");

//            conn.cookie("__DAYU_PP", cookie.split("=")[1]);
//            conn.cookie("rprm_appshow", "-15417286164000.01586946812327794");
//            conn.cookie("rprm_cuid", "154172861616464");
//            conn.cookie("rprm_pv", System.currentTimeMillis()+"-goodsdetail");
//            conn.cookie("saas.express.session",
//                "s:_EILevV6ncORaEvdxkhr8WXsTKMy3wOn.AGwSzOLiDd7ybz+g095E8FxfAwbWVPqsULjLx3o1tHQ");

            conn.userAgent(userAgent);
            conn.data(map);
            conn.header("Accept", "application/json");
            conn.header("Accept-Encoding", "gzip, deflate");
            conn.header("Accept-Language",
                "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            conn.header("Cache-Control", "no-cache");
            conn.header("Connection", "keep-alive");
            conn.header("Content-Length", "133");
            conn.header("Content-Type", "application/json; charset=utf-8");
            conn.header("Cookie", weiMengCookie());
            String origin = url.substring(0, url.indexOf("/s"));
            String host = origin.substring(7);
            conn.header("Host", host);
            conn.header("Origin", origin);
            conn.header("Pragma", "no-cache");
            conn.header("Referer", url);
            conn.header("User-Agent", userAgent);
            conn.method(Method.POST);
            conn.followRedirects(false);
            conn.ignoreContentType(true);
            Response response = conn.execute();
            String body = response.body();

            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        String url = "http://100000063048.retail.n.weimob.com/saas/retail/100000063048/2248648/goods/detail?id=1788240148";
        String[] split = url.split("/");

        String pid = split[5];
        String storeId = split[6];
        String goodsId = split[8].split("=")[1];

        Map<String, String> map = new HashMap<>();
        map.put("pid", pid);
        map.put("storeId", storeId);
        map.put("wid", "-15417256964086428");
        map.put("source", "2");
        map.put("goodsId", goodsId);
        map.put("activityId", "");
        map.put("activityType", "");
        goWeiMenPostHttp(url, map);
    }
}
