package cn.cloudx.importdata.repository.user;

import cn.cloudx.importdata.entity.user.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghao
 * @date 2018/06/05
 */
public interface PhoneRepository extends JpaRepository<Phone, String> {
}
