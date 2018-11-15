package org.huangding.commons;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@SpringBootApplication(scanBasePackages = "org.huangding.commons")
//mybatis
@MapperScan("com.saopay.apiyouzan.data.dao.mapper")
@EnableSwagger2
//缓存
@EnableCaching
public class CommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsApplication.class, args);
    }
}
