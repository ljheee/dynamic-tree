package com.me.ljheee.tree.module.department.biz;

import com.me.ljheee.tree.module.department.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Transactional
    public void deleteByDeptId(@PathVariable("deptId") Integer deptId){
        deptRepository.delete(deptRepository.findByParentId(deptId));
        deptRepository.delete(deptId);
    }


}
