package org.huangding.commons.data.dao.jpa;

import lombok.extern.slf4j.Slf4j;
import org.huangding.commons.data.pojo.po.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huangding
 * @description
 * @date 2018/11/12 20:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserJpaDaoTest {

    @Autowired
    private UserJapDao userJapDao;

//    @Test
//    public void save() {
//        User user = new User();
//        user.setName("张三");
//        user.setPhone("17858449952");
//        user.setSex(1);
//        User save = userJapDao.save(user);
//        log.info("current add id :{}", save.getId());
//        Assert.assertEquals(save.getId() > 0, true);
//    }

}
