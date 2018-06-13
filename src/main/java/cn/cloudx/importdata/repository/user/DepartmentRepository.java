package cn.cloudx.importdata.repository.user;

import cn.cloudx.importdata.entity.user.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghao
 * @date 2018/06/06
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
