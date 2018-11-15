package org.huangding.commons.data.dao.jpa;

import org.huangding.commons.data.pojo.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangding
 * @description
 * @date 2018/11/12 15:36
 */
public interface UserJapDao extends JpaRepository<User, Long> {

}
