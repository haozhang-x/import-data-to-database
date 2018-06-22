package cn.cloudx.importdata.repository.item;

import cn.cloudx.importdata.entity.item.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByItemnum(String itemNum);


    List<Inventory> findByLocation(String location);

}
