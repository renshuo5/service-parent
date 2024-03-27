package com.renshuo.cloud.sys.controller;

import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.sys.model.RoleModel;
import com.renshuo.cloud.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
* @description: 角色管理|角色管理|role控制层
* @author: renshuo
* @date: 2024-03-27
*/
@RestController
@RequestMapping(value="/"+ Version.VERSION_V1+"/sys")
@Api(value = "角色管理|角色管理|role", description = "角色管理|角色管理|role", tags = {"角色管理|角色管理|role"})
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
    * 查询列表信息
    * @param pagerInfo
    * @return
    */
    @PostMapping("/roles")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(roleService.list(pagerInfo));
    }

    /**
    * 查询详细信息
    * @param id
    * @return
    */
    @ApiOperation(value = "详细信息", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/role/{id}")
    public ResultMsg get(@PathVariable String id){
        RoleModel role = roleService.get(id);
        return ResultMsg.success(role);
    }

    /**
    * 保存
    * @param model
    * @return
    */
    @PostMapping(value="/role")
    @ApiOperation(value = "新增", notes = "新增信息")
    public ResultMsg add(@RequestBody RoleModel model){
        roleService.insert(model);
        return ResultMsg.success("保存成功");
    }

    /**
    * 更新
    * @param model
    * @return
    */
    @PutMapping(value="/role/{id}")
    @ApiOperation(value = "更新", notes = "编辑更新信息")
    public ResultMsg update(@RequestBody RoleModel model){
        roleService.update(model);
        return ResultMsg.success("修改成功");
    }

    /**
    * 删除
    * @param ids
    * @return
    */
    @DeleteMapping(value="/role/{ids}")
    @ApiOperation(value = "删除", notes = "根据ids批量删除数据信息")
    public ResultMsg update(@PathVariable String ids){
        int count = roleService.delete(ids);
        return ResultMsg.success("删除成功");
    }

    /**
    * 多条件批次全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/role/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        roleService.exportPage(response, pagerInfo);
    }

    /**
    * 多条件一次性全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/role/exportAll")
    @ApiOperation(value = "多条件一次性全部导出", notes = "多条件一次性全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        roleService.exportAll(response, pagerInfo);
    }

}