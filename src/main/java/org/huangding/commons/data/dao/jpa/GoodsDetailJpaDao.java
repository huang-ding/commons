package org.huangding.commons.data.dao.jpa;

import java.util.List;
import org.huangding.commons.data.pojo.po.GoodsDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangding
 * @description
 * @date 2018/11/12 20:53
 */
public interface GoodsDetailJpaDao extends JpaRepository<GoodsDetail, Long> {

    /**
     * 查询非指定type
     */
    List<GoodsDetail> findByTypeNot(Integer notType);
}
