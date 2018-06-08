package cn.cloudx.importdata.repository;

import cn.cloudx.importdata.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghao
 * @date 2018/06/06
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
