package com.me.ljheee.tree.module.employee.repository;

import com.me.ljheee.tree.module.employee.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
    //根据部门id来查找
    List<Emp> findByDeptId(Integer deptId);

}
