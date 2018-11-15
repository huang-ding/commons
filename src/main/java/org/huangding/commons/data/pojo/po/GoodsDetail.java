package org.huangding.commons.data.pojo.po;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangding
 * @description 商品详情
 * @date 2018/11/12 20:41
 */
@Entity(name = "goods_detail")
@Data
@NoArgsConstructor
public class GoodsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /**
     * 商品编号
     */
    private String no;
    /**
     * 商品类型 1= 食品 2=饮料3=日用品 详情见enum
     */
    private Integer type;
    /**
     * 批发价
     */
    @Column(name = "wholesale_price")
    private Integer wholesalePrice;
    /**
     * 零售价
     */
    @Column(name = "retail_price")
    private Integer retailPrice;

    /**
     * 会员价
     */
    @Column(name = "vip_price")
    private Integer vipPrice;
    /**
     * 单位/个、袋。。
     */
    private String unit;
    /**
     * 规格250g*24，250g*24
     */
    private String specifications;
    /**
     * 产地 中国甘肃，美国
     */
    private String area;
    /**
     * 删除状态 0=可用 1=删除
     */
    private Integer deleted = 0;


    @Column(name = "creator_time", updatable = false)
    private LocalDateTime creatorTime;
    @Column(name = "creator_date", updatable = false)
    private LocalDate creatorDate;
    @Column(name = "creator_id", updatable = false)
    private Integer creatorId;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "update_id")
    private Integer updateId;

}
