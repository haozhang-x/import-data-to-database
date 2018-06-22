package cn.cloudx.importdata.repository.item;

import cn.cloudx.importdata.entity.item.Invtrans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public interface InvTransRepository extends JpaRepository<Invtrans, Integer> {
    Optional<Invtrans> findByItemnum(String itemNum);

    List<Invtrans> findAllBySiteid(String siteId);
}
