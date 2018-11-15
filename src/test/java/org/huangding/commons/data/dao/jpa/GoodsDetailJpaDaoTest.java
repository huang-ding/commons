package org.huangding.commons.data.dao.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.huangding.commons.data.pojo.po.GoodsDetail;
import org.huangding.commons.enums.GoodsTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huangding
 * @description
 * @date 2018/11/12 20:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsDetailJpaDaoTest {

    @Autowired
    private GoodsDetailJpaDao goodsDetailJpaDao;
//
//    @Test
//    public void save() {
//        GoodsDetail goodsDetail = new GoodsDetail();
//        goodsDetail.setName("测试");
//        goodsDetail.setArea("中国");
//        goodsDetail.setNo("100010");
//        goodsDetail.setRetailPrice(1000);
//        goodsDetail.setWholesalePrice(100);
//        goodsDetail.setSpecifications("250g*24");
//        goodsDetail.setUnit("袋");
//        goodsDetail.setType(1);
//        goodsDetail.setCreatorDate(LocalDate.now());
//        goodsDetail.setCreatorTime(LocalDateTime.now());
//        goodsDetail.setUpdateTime(LocalDateTime.now());
//        goodsDetail.setDeleted(1);
//        GoodsDetail save = goodsDetailJpaDao.save(goodsDetail);
//        log.info("current add id :{}", save.getId());
//        Assert.assertEquals(save.getId() > 0, true);
//
//    }
//
//    @Test
//    public void testFindByTypeNot() {
//        List<GoodsDetail> byTypeNot = goodsDetailJpaDao.findByTypeNot(GoodsTypeEnum.FOOD.getCode());
//        log.info("size:{}",byTypeNot.size());
//    }

}
