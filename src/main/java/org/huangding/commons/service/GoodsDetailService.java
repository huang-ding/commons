package org.huangding.commons.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.huangding.commons.data.dao.jpa.GoodsDetailJpaDao;
import org.huangding.commons.data.dao.jpa.GoodsJDJpaDao;
import org.huangding.commons.data.pojo.po.GoodsDetail;
import org.huangding.commons.data.pojo.po.GoodsJD;
import org.huangding.commons.enums.GoodsTypeEnum;
import org.huangding.commons.reptilian.JdPriceDown;
import org.huangding.commons.util.base.DataUtil;
import org.huangding.commons.util.file.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huangding
 * @description
 * @date 2018/11/13 9:18
 */
@Service
public class GoodsDetailService {

    @Autowired
    private GoodsDetailJpaDao goodsDetailJpaDao;
    @Autowired
    private GoodsJDJpaDao goodsJDJpaDao;

    @Transactional(rollbackFor = Exception.class)
    public void importExcel(int sheet, int rowStartIndex, GoodsTypeEnum goodsTypeEnum)
        throws FileNotFoundException {
        File file = new File("C:\\Users\\24020\\Desktop\\报价单(2).xls");
        InputStream inputStream = new FileInputStream(file);
        List<List<String>> excelInfoStr = ExcelUtil
            .getExcelInfoStrWithNull(inputStream, ExcelUtil.isExcel2003(file.getName()), sheet,
                rowStartIndex);
        List<String> dataParameterNames = excelInfoStr.get(0);
        List<GoodsDetail> excelDatas = new ArrayList<>();
        for (int i = 1; i < excelInfoStr.size(); i++) {
            List<String> datas = excelInfoStr.get(i);
            GoodsDetail goodsDetail = new GoodsDetail();
            goodsDetail.setNo(ExcelUtil.getDataStr(datas, "自编码", dataParameterNames));
            goodsDetail.setName(ExcelUtil.getDataStr(datas, "品名", dataParameterNames));
            //TODO type传入
            goodsDetail.setType(goodsTypeEnum.getCode());

            Double wholesalePrice = ExcelUtil.getDataDouble(datas, "批发价", dataParameterNames);

            goodsDetail.setWholesalePrice(DataUtil.doubleOfInt(wholesalePrice));

            Double retailPrice = ExcelUtil.getDataDouble(datas, "零售价", dataParameterNames);
            goodsDetail.setRetailPrice(DataUtil.doubleOfInt(retailPrice));

            Double vipPrice = ExcelUtil.getDataDouble(datas, "会员价", dataParameterNames);
            goodsDetail.setVipPrice(DataUtil.doubleOfInt(vipPrice));

            goodsDetail.setUnit(ExcelUtil.getDataStr(datas, "单位", dataParameterNames));

            goodsDetail.setSpecifications(ExcelUtil.getDataStr(datas, "规格", dataParameterNames));

            goodsDetail.setArea(ExcelUtil.getDataStr(datas, "产地", dataParameterNames));
            LocalDateTime now = LocalDateTime.now();
            goodsDetail.setUpdateTime(now);
            goodsDetail.setCreatorTime(now);
            goodsDetail.setCreatorDate(LocalDate.now());
            excelDatas.add(goodsDetail);

        }
        goodsDetailJpaDao.saveAll(excelDatas);
    }

    public void importJdData() {
//        List<GoodsDetail> all = goodsDetailJpaDao.findByTypeNot(GoodsTypeEnum.FOOD.getCode());
        List<GoodsDetail> all = goodsDetailJpaDao.findAll();
        List<GoodsJD> goodsJDS = new ArrayList<>(all.size());
        all.forEach(goodsDetail -> {
            StringBuilder keyword = new StringBuilder();
            keyword.append(goodsDetail.getArea()).append(" ").append(goodsDetail.getName())
                .append(" ").append(goodsDetail.getUnit());
            try {
                GoodsJD priceAndCommit = JdPriceDown.getPriceAndCommit(keyword.toString());
                if (null != priceAndCommit) {
                    priceAndCommit.setGoods_id(goodsDetail.getId());
                    goodsJDS.add(priceAndCommit);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        goodsJDJpaDao.saveAll(goodsJDS);
    }
}
