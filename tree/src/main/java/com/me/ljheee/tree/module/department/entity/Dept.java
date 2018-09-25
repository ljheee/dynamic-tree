package com.me.ljheee.tree.module.department.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dept {

    @Id
    @GeneratedValue
    private Integer deptId;

    private String deptName;

    private Integer parentId;

    public Dept() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
