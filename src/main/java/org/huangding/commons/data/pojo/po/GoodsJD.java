package org.huangding.commons.data.pojo.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author huangding
 * @description 京东商品
 * @date 2018/11/13 10:44
 */
@Entity(name = "goods_jd")
@Data
public class GoodsJD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_id")
    private Long goods_id;

    private String name;

    private Integer price;

    private Integer commit;

}
