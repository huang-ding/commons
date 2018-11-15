package org.huangding.commons.reptilian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.Data;
import org.huangding.commons.data.pojo.po.GoodsJD;
import org.huangding.commons.util.base.DataUtil;
import org.huangding.commons.util.http.Coder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author huangding
 * @description 京东价格爬取
 * @date 2018/11/12 16:32
 */
public class JdPriceDown {


    public static GoodsJD getPriceAndCommit(String keyword) throws IOException {
        /* 网址分析
        keyword:关键词（京东搜索框输入的信息）
         * enc：编码方式（可改动:默认UTF-8）
         * psort=3 //搜索方式  默认按综合查询 不给psort值
         * page=分业（不考虑动态加载时按照基数分业，每一页30条，这里就不演示动态加载）
         * 注意：受京东商品个性化影响，准确率无法保障
         * */
        String url = "https://search.jd.com/Search?keyword=" + Coder.encoder(keyword)
            + "&enc=utf-8&suggest=4.his.0.0&wq=&pvid=7209a689ca0440b3a15124fa1903c8db";
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        //doc获取整个页面的所有数据
        Elements check = doc.select(".check-error");
        if (null != check && check.size() > 0) {
            return null;
        }
        try {
            Elements ulList = doc.select("ul[class='gl-warp clearfix']");
            Elements liList = ulList.select("li[class='gl-item']");
            List<GoodsJD> jdPriceList = new ArrayList<>();
            //循环liList的数据
            int count = 0;
            for (Element item : liList) {
                //排除广告位置
                if (!item.select("span[class='p-promo-flag']").text().trim().equals("广告")) {
                    //如果向存到数据库和文件里请自行更改
                    GoodsJD jdPrice = new GoodsJD();
                    jdPrice.setName(
                        item.select("div[class='p-name p-name-type-2']").select("em").text());
                    jdPrice.setCommit(
                        getCommitInt(item.select("div[class='p-commit']").select("a").text()));
                    jdPrice.setPrice(DataUtil.doubleOfInt(Double
                        .parseDouble(
                            item.select("div[class='p-price']").select("i").text().trim())));
                    jdPriceList.add(jdPrice);
                    count++;
                    if (count == 5) {
                        break;
                    }
                }
            }

            GoodsJD jdPrice = jdPriceList.stream().max(new Comparator<GoodsJD>() {
                @Override
                public int compare(GoodsJD o1, GoodsJD o2) {
                    return o1.getCommit().compareTo(o2.getCommit());
                }
            }).get();
            return jdPrice;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getCommitInt(String commit) {
        if (commit.indexOf("万") != -1) {
            return Integer.parseInt(
                String.valueOf(Double.parseDouble(commit.split("万")[0]) * 10000).split("\\.")[0]);
        }
        return Integer.parseInt(commit.split("\\+")[0]);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getCommitInt("3.5万"));
    }


}
