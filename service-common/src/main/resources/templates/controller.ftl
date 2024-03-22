package com.renshuo.cloud.${domain.module}.controller;

import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.reqbean.PagerInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.renshuo.cloud.common.ResultMsg;
import com.renshuo.cloud.${domain.module}.service.${domain.name}Service;
import com.renshuo.cloud.${domain.module}.model.${domain.name}Model;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
* @description: ${domain.comments}控制层
* @author: renshuo
* @date: ${date}
*/
@RestController
@RequestMapping(value="/"+ Version.VERSION_V1+"/${domain.module}")
@Api(value = "${domain.comments}", description = "${domain.comments}", tags = {"${domain.comments}"})
@Slf4j
public class ${domain.name}Controller {

    @Autowired
    private ${domain.name}Service ${domain.instName}Service;


    /**
    * 查询列表信息
    * @param pagerInfo
    * @return
    */
    @PostMapping("/${domain.instName}s")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(${domain.instName}Service.list(pagerInfo));
    }

    /**
    * 查询详细信息
    * @param id
    * @return
    */
    @ApiOperation(value = "详细信息", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/${domain.instName}/{id}")
    public ResultMsg get(@PathVariable String id){
        ${domain.name}Model ${domain.instName} = ${domain.instName}Service.get(id);
        return ResultMsg.success(${domain.instName});
    }

    /**
    * 保存
    * @param model
    * @return
    */
    @PostMapping(value="/${domain.instName}")
    @ApiOperation(value = "新增", notes = "新增信息")
    public ResultMsg add(@RequestBody ${domain.name}Model model){
        ${domain.instName}Service.insert(model);
        return ResultMsg.success("保存成功");
    }

    /**
    * 更新
    * @param model
    * @return
    */
    @PutMapping(value="/${domain.instName}/{id}")
    @ApiOperation(value = "更新", notes = "编辑更新信息")
    public ResultMsg update(@RequestBody ${domain.name}Model model){
        ${domain.instName}Service.update(model);
        return ResultMsg.success("修改成功");
    }

    /**
    * 删除
    * @param ids
    * @return
    */
    @DeleteMapping(value="/${domain.instName}/{ids}")
    @ApiOperation(value = "删除", notes = "根据ids批量删除数据信息")
    public ResultMsg update(@PathVariable String ids){
        int count = ${domain.instName}Service.delete(ids);
        return ResultMsg.success("删除成功");
    }

    /**
    * 多条件批次全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/${domain.instName}/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        ${domain.instName}Service.exportPage(response, pagerInfo);
    }

    /**
    * 多条件一次性全部导出
    * @param response
    * @param pagerInfo
    * @return
    */
    @PostMapping("/${domain.instName}/exportAll")
    @ApiOperation(value = "多条件一次性全部导出", notes = "多条件一次性全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        ${domain.instName}Service.exportAll(response, pagerInfo);
    }

}