package com.cvte.ljheee.tree.module.employee.controller;


import com.cvte.ljheee.tree.module.employee.entity.Emp;
import com.cvte.ljheee.tree.module.employee.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/emp")
@RestController
public class EmpController {

    @Autowired
    EmpRepository repository;

    @PostMapping("")
    public Emp addEmp(String empName, Integer deptId ){
        Emp emp = new Emp(empName ,deptId);
        return repository.save(emp);
    }
    @PutMapping("/{id}")
    public void editEmp(){

    }



}
