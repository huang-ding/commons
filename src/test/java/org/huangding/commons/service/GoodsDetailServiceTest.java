package org.huangding.commons.service;

import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.huangding.commons.enums.GoodsTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huangding
 * @description
 * @date 2018/11/13 10:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsDetailServiceTest {
    @Autowired
    private GoodsDetailService goodsDetailService;

//    @Test
//    public void testImportExcel() throws FileNotFoundException {
//        goodsDetailService.importExcel(23,0, GoodsTypeEnum.CUTLERY);
//    }
//
//    @Test
//    public void testImportJdData(){
//        goodsDetailService.importJdData();
//    }
}
