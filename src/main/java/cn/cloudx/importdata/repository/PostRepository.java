package cn.cloudx.importdata.repository;

import cn.cloudx.importdata.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghao
 * @date 2018/06/06
 */
public interface PostRepository extends JpaRepository<Post,Integer> {
}
