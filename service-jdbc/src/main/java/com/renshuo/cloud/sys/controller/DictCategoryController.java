package com.renshuo.cloud.sys.controller;

import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.sys.model.DictCategoryModel;
import com.renshuo.cloud.sys.service.DictCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 字典类别|字典类别|dc控制层
 * @author: renshuo
 * @date: 2024-03-27
 */
@RestController
@RequestMapping(value = "/" + Version.VERSION_V1 + "/sys")
@Api(value = "字典类别|字典类别|dc", description = "字典类别|字典类别|dc", tags = {"字典类别|字典类别|dc"})
@Slf4j
public class DictCategoryController {

    @Autowired
    private DictCategoryService dictCategoryService;


    /**
     * 查询列表信息
     *
     * @param pagerInfo
     * @return
     */
    @PostMapping("/dictCategorys")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(dictCategoryService.list(pagerInfo));
    }

    /**
     * 查询详细信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "详细信息", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/dictCategory/{id}")
    public ResultMsg get(@PathVariable String id) {
        DictCategoryModel dictCategory = dictCategoryService.get(id);
        return ResultMsg.success(dictCategory);
    }

    /**
     * 保存
     *
     * @param model
     * @return
     */
    @PostMapping(value = "/dictCategory")
    @ApiOperation(value = "新增", notes = "新增信息")
    public ResultMsg add(@RequestBody DictCategoryModel model) {
        dictCategoryService.insert(model);
        return ResultMsg.success("保存成功");
    }

    /**
     * 更新
     *
     * @param model
     * @return
     */
    @PutMapping(value = "/dictCategory/{id}")
    @ApiOperation(value = "更新", notes = "编辑更新信息")
    public ResultMsg update(@RequestBody DictCategoryModel model) {
        dictCategoryService.update(model);
        return ResultMsg.success("修改成功");
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/dictCategory/{ids}")
    @ApiOperation(value = "删除", notes = "根据ids批量删除数据信息")
    public ResultMsg update(@PathVariable String ids) {
        int count = dictCategoryService.delete(ids);
        return ResultMsg.success("删除成功");
    }

    /**
     * 多条件批次全部导出
     *
     * @param response
     * @param pagerInfo
     * @return
     */
    @PostMapping("/dictCategory/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        dictCategoryService.exportPage(response, pagerInfo);
    }

    /**
     * 多条件一次性全部导出
     *
     * @param response
     * @param pagerInfo
     * @return
     */
    @PostMapping("/dictCategory/exportAll")
    @ApiOperation(value = "多条件一次性全部导出", notes = "多条件一次性全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        dictCategoryService.exportAll(response, pagerInfo);
    }

}