package org.huangding.commons.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.huangding.commons.reptilian.JdImageDown;
import org.huangding.commons.util.file.ExportUtil;
import org.huangding.commons.util.http.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangding
 * @description
 * @date 2018/11/8 15:22
 */
@RestController
@RequestMapping("jd")
@Slf4j
@Api("京东素材下载")
public class JdDownloadController {

    @Autowired
    private JdImageDown jdImageDown;

    /**
     * http://img14.360buyimg.com/n1/s54x54_jfs/t1/859/17/13600/337853/5bd94037E83dc47e4/e4791d93f0ba399d.jpg
     */
    @ApiOperation(value = "下载京东详情页轮播图")
    @GetMapping("img")
    public void download(@ApiParam(value = "商品详情页url") @RequestParam String url,
        @ApiParam(value = "商品图片宽度,默认值450", defaultValue = "450") @RequestParam(defaultValue = "450") String width,
        @ApiParam(value = "商品图片高度,默认值450", defaultValue = "450") @RequestParam(defaultValue = "450") String high,
        HttpServletResponse response,
        HttpServletRequest request) throws Exception {
        if (StringUtils.isNotBlank(url)) {
            Map<String, String> jdImgUrl = jdImageDown.getJdImgUrl(url, width, high);
            String[] pathArr = jdImgUrl.get("imgs").split(",");
            List<String> urls = Arrays.asList(pathArr);
            //京东商品编号
            String commodityNo = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
            String name = jdImgUrl.get("name");
            StringBuilder exportFileName = new StringBuilder();
            exportFileName.append(commodityNo).append("-").append(name).append(".zip");
            ExportUtil.packageHttpFileToZip(exportFileName.toString(), urls, request, response);
        }
    }

    @ApiOperation(value = "查看京东详情页轮播图")
    @GetMapping("showImg")
    public JsonResult showImg(@ApiParam(value = "商品详情页url") @RequestParam String url,
        @ApiParam(value = "商品图片宽度,默认值450", defaultValue = "450") @RequestParam(defaultValue = "450") String width,
        @ApiParam(value = "商品图片高度,默认值450", defaultValue = "450") @RequestParam(defaultValue = "450") String high)
        throws Exception {
        Map<String, String> jdImgUrl = jdImageDown.getJdImgUrl(url, width, high);
        String[] pathArr = jdImgUrl.get("imgs").split(",");
        List<String> urls = Arrays.asList(pathArr);
        return JsonResult.DATA(urls);
    }
}
