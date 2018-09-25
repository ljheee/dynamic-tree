package com.me.ljheee.tree.module.department.repository;

import com.me.ljheee.tree.module.department.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept,  Integer> {
    //根据父级部门id来查找部门
    List<Dept> findByParentId(Integer parentId);
}
