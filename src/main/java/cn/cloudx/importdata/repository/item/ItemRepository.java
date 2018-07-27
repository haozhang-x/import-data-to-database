package cn.cloudx.importdata.repository.item;

import cn.cloudx.importdata.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByItemnum(String itemNum);

    @Query("select max(itemnum) from Item where  classStructureId=?1")
    String findMaxItemNum(String classStructureId);
}
