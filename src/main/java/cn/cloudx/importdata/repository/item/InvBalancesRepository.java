package cn.cloudx.importdata.repository.item;

import cn.cloudx.importdata.entity.item.Invbalances;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface InvBalancesRepository extends JpaRepository<Invbalances, Integer> {
    Optional<Invbalances> findByItemnum(String itemNum);


    List<Invbalances> findByLocation(String location);
}
