package cn.cloudx.importdata.repository.user;

import cn.cloudx.importdata.entity.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author zhanghao
 * @date 2018/06/05
 */
public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByPersonid(String personid);
}
