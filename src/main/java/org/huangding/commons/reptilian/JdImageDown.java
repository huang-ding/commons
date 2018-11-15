package org.huangding.commons.reptilian;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JdImageDown {


    /**
     * 获取京东详情图片
     * @param url
     * @param width
     * @param high
     * @return
     * @throws IOException
     */
    public Map<String, String> getJdImgUrl(String url, String width, String high)
        throws IOException {
        String ruler = "s" + width + "x" + high;
        StringBuilder stringBuilder = new StringBuilder();
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        //商品名称
        Elements names = doc.select(".sku-name");
        String name = names.get(0).text();
        Map<String, String> map = new HashMap<>(2);
        map.put("name", name);
        //商品图片
        Elements img = doc.select(".lh img");

        for (Element i : img) {
            String src = i.attr("src");
            if (StringUtils.isNotBlank(src)) {
                String replace = src.replace("n5", "n1").replace("s54x54", ruler);
                stringBuilder.append(",http:").append(replace);
            }
        }
        map.put("imgs", stringBuilder.substring(1));
        return map;

    }

    public static void main(String[] args) throws IOException {
        JdImageDown jdImageDown = new JdImageDown();
        String url = "https://item.jd.com/100000578379.html?cpdad=1DLSUE";
        Map<String, String> jdImgUrl = jdImageDown.getJdImgUrl(url, "450", "450");
    }

}
