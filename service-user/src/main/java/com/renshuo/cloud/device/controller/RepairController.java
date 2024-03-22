package com.renshuo.cloud.device.controller;

import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.reqbean.PagerInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.device.service.RepairService;
import com.renshuo.cloud.device.model.RepairModel;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
* @description: 设备维修记录表控制层
* @author: renshuo
* @date: 2023-11-10
*/
@RestController
@RequestMapping(value="/"+ Version.VERSION_V1+"/device")
@Api(value = "设备维修记录表", description = "设备维修记录表", tags = {"设备维修记录表"})
@Slf4j
public class RepairController {

    @Autowired
    private RepairService repairService;


    /**
    * 查询列表信息
    * @param pagerInfo
    * @return
    */
    @PostMapping("/repairs")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(repairService.list(pagerInfo));
    }

    /**
    * 查询详细信息
    * @param id
    * @return
    */
    @ApiOperation(value = "详细信息", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/repair/{id}")
    public ResultMsg get(@PathVariable String id){
        RepairModel repair = repairService.get(id);
        return ResultMsg.success(repair);
    }

    /**
    * 保存
    * @param model
    * @return
    */
    @PostMapping(value="/repair")
    @ApiOperation(value = "新增", notes = "新增信息")
    public ResultMsg add(@RequestBody RepairModel model){
        repairService.insert(model);
        return ResultMsg.success("保存成功");
    }

    /**
    * 更新
    * @param model
    * @return
    */
    @PutMapping(value="/repair/{id}")
    @ApiOperation(value = "更新", notes = "编辑更新信息")
    public ResultMsg update(@RequestBody RepairModel model){
        repairService.update(model);
        return ResultMsg.success("修改成功");
    }

    /**
    * 删除
    * @param ids
    * @return
    */
    @DeleteMapping(value="/repair/{ids}")
    @ApiOperation(value = "删除", notes = "根据ids批量删除数据信息")
    public ResultMsg update(@PathVariable String ids){
        int count = repairService.delete(ids);
        return ResultMsg.success("删除成功");
    }

    /**
    * 多条件批次全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/repair/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        repairService.exportPage(response, pagerInfo);
    }

    /**
    * 多条件一次性全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/repair/exportAll")
    @ApiOperation(value = "多条件一次性全部导出", notes = "多条件一次性全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        repairService.exportAll(response, pagerInfo);
    }

}