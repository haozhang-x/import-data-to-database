package cn.cloudx.importdata.repository;

import cn.cloudx.importdata.entity.Groupuser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghao
 * @date 2018/06/05
 */
public interface GroupUserRepository extends JpaRepository<Groupuser, String> {
}
