package com.renshuo.cloud.sys.controller;

import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.sys.model.MenuInfoModel;
import com.renshuo.cloud.sys.service.MenuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
* @description: 控制层
* @author: renshuo
* @date: 2024-03-18
*/
@RestController
@RequestMapping(value="/"+ Version.VERSION_V1+"/sys")
@Api(value = "系统管理-菜单", description = "系统管理-菜单", tags = {"系统管理-菜单"})
@Slf4j
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;


    /**
    * 查询列表信息
    * @param pagerInfo
    * @return
    */
    @PostMapping("/menuInfos")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(menuInfoService.list(pagerInfo));
    }
    /**
    * 查询列表前台转树形菜单
    * @return
    */
    @GetMapping("/menuInfos")
    @ApiOperation(value = "查询列表", notes = "查询列表")
    public ResultMsg list() {
        return ResultMsg.success(menuInfoService.listAll());
    }
    /**
    * 查询列表前台转树形菜单
    * @return
    */
    @PostMapping("/menuInfosNoBut")
    @ApiOperation(value = "下拉框查询", notes = "下拉框查询")
    public ResultMsg listMenu() {
        return ResultMsg.success(menuInfoService.getMenuAll());
    }

    /**
    * 查询详细信息
    * @param id
    * @return
    */
    @ApiOperation(value = "详细信息", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/menuInfo/{id}")
    public ResultMsg get(@PathVariable String id){
        MenuInfoModel menuInfo = menuInfoService.get(id);
        return ResultMsg.success(menuInfo);
    }

    /**
    * 保存
    * @param model
    * @return
    */
    @PostMapping(value="/menuInfo")
    @ApiOperation(value = "新增", notes = "新增信息")
    public ResultMsg add(@RequestBody MenuInfoModel model){
        menuInfoService.insert(model);
        return ResultMsg.success("保存成功");
    }

    /**
    * 更新
    * @param model
    * @return
    */
    @PutMapping(value="/menuInfo/{id}")
    @ApiOperation(value = "更新", notes = "编辑更新信息")
    public ResultMsg update(@PathVariable String id,@RequestBody MenuInfoModel model){
        log.info("id:{}",id);
        menuInfoService.update(model);
        return ResultMsg.success("修改成功");
    }

    /**
    * 删除
    * @param ids
    * @return
    */
    @DeleteMapping(value="/menuInfo/{ids}")
    @ApiOperation(value = "删除", notes = "根据ids批量删除数据信息")
    public ResultMsg update(@PathVariable String ids){
        int count = menuInfoService.delete(ids);
        return ResultMsg.success("删除成功");
    }

    /**
    * 多条件批次全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/menuInfo/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        menuInfoService.exportPage(response, pagerInfo);
    }

    /**
    * 多条件一次性全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/menuInfo/exportAll")
    @ApiOperation(value = "多条件一次性全部导出", notes = "多条件一次性全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        menuInfoService.exportAll(response, pagerInfo);
    }

}