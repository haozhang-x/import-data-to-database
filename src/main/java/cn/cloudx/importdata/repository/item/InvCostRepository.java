package cn.cloudx.importdata.repository.item;

import cn.cloudx.importdata.entity.item.Invcost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface InvCostRepository extends JpaRepository<Invcost, Integer> {
    List<Invcost> findByItemnum(String itemNum);

    List<Invcost> findByLocation(String location);
}
