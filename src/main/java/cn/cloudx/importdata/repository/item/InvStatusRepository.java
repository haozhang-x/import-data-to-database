package cn.cloudx.importdata.repository.item;

import cn.cloudx.importdata.entity.item.Invstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface InvStatusRepository extends JpaRepository<Invstatus, Integer> {
    Optional<Invstatus> findByItemnum(String itemNum);

    List<Invstatus> findByLocation(String location);
}
