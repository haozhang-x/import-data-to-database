package cn.cloudx.importdata.repository;

import cn.cloudx.importdata.entity.Invcost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface InvCostRepository extends JpaRepository<Invcost,Integer> {
}
