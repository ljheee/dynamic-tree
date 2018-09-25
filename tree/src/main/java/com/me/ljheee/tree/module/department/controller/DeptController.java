package com.me.ljheee.tree.module.department.controller;

import com.me.ljheee.tree.module.department.biz.DeptService;
import com.me.ljheee.tree.module.department.entity.Dept;
import com.me.ljheee.tree.module.department.repository.DeptRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "部门模块")
@RestController
public class DeptController {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;

    @ApiOperation(value="获取顶级部门", notes="")
    @GetMapping(value = "/deptRoot")
    public List<Dept> detpRoot(){
        return deptRepository.findByParentId(null);
    }

    @ApiOperation(value="查询其所有的子部门", notes="根据部门id")
    @ApiImplicitParam(name = "deptId", value = "部门ID", required = true, dataType = "Integer")
    @GetMapping(value = "/childDepts/{deptId}")
    public List<Dept> deptList(@PathVariable(value = "deptId") Integer deptId){
        try {
            if (deptId == 0) return  deptRepository.findByParentId(null);
            else return deptRepository.findByParentId(deptId);
        }catch (NumberFormatException e){
        }
        return null;
    }


    @ApiOperation(value = "添加一个部门",notes = "参数：部门名称，父部门id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "上级部门ID", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/dept")
    public Dept deptAdd(@RequestParam("deptName") String deptName,
                        @RequestParam("parentId") Integer parentId){
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setParentId(parentId);
        return deptRepository.save(dept);
    }


    @ApiOperation(value = "更新部门信息（包括拖拽节点移动部门）",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "上级部门ID", required = true, dataType = "Integer")
    })
    @PutMapping(value = "dept/{deptId}")
    public Dept deptUpdate(@PathVariable("deptId") Integer deptId,
                           @RequestParam("deptName") String deptName,
                           @RequestParam("parentId") Integer parentId){
        Dept dept = new Dept();
        dept.setDeptId(deptId);
        dept.setDeptName(deptName);
        dept.setParentId(parentId);

        return deptRepository.save(dept);
    }

    @ApiOperation(value = "删除部门(包括其下子部门)",notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "deptId", value = "部门ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "depts/{deptId}")
    public void deptDeleteByDeptId(@PathVariable("deptId") Integer deptId){
        deptService.deleteByDeptId(deptId);
    }
}
