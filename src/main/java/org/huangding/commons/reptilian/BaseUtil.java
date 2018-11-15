package org.huangding.commons.reptilian;

import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * @author huangding
 * @description
 * @date 2018/11/8 16:54
 */
public class BaseUtil {


    /**
     * 获取cookie
     */
    public static final String returnCookies(String url) {
        try {
            Connection conn = Jsoup.connect(url);
            conn.method(Method.GET);
            conn.followRedirects(false);
            Response response;
            response = conn.execute();
            Map<String, String> getCookies = response.cookies();
            String Cookie = getCookies.toString();
            Cookie = Cookie.substring(Cookie.indexOf("{") + 1, Cookie.lastIndexOf("}"));
            Cookie = Cookie.replaceAll(",", ";");
            return Cookie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
