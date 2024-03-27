package com.renshuo.cloud.sys.controller;

import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.constant.Version;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.sys.model.DictModel;
import com.renshuo.cloud.sys.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description: 字典管理|字典管理|d控制层
 * @author: renshuo
 * @date: 2024-03-27
 */
@RestController
@RequestMapping(value = "/" + Version.VERSION_V1 + "/sys")
@Api(value = "字典管理|字典管理|d", description = "字典管理|字典管理|d", tags = {"字典管理|字典管理|d"})
@Slf4j
public class DictController {

    @Autowired
    private DictService dictService;


    /**
     * 查询列表信息
     *
     * @param pagerInfo
     * @return
     */
    @PostMapping("/dicts")
    @ApiOperation(value = "多条件列表查询", notes = "多条件列表查询")
    public ResultMsg list(@RequestBody PagerInfo pagerInfo) {
        return ResultMsg.success(dictService.list(pagerInfo));
    }

    /**
     * 查询详细信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "详细信息", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/dict/{id}")
    public ResultMsg get(@PathVariable String id) {
        DictModel dict = dictService.get(id);
        return ResultMsg.success(dict);
    }

    /**
     * 保存
     *
     * @param model
     * @return
     */
    @PostMapping(value = "/dict")
    @ApiOperation(value = "新增", notes = "新增信息")
    public ResultMsg add(@RequestBody DictModel model) {
        dictService.insert(model);
        return ResultMsg.success("保存成功");
    }

    /**
     * 更新
     *
     * @param model
     * @return
     */
    @PutMapping(value = "/dict/{id}")
    @ApiOperation(value = "更新", notes = "编辑更新信息")
    public ResultMsg update(@RequestBody DictModel model) {
        dictService.update(model);
        return ResultMsg.success("修改成功");
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/dict/{ids}")
    @ApiOperation(value = "删除", notes = "根据ids批量删除数据信息")
    public ResultMsg update(@PathVariable String ids) {
        int count = dictService.delete(ids);
        return ResultMsg.success("删除成功");
    }

    /**
     * 多条件批次全部导出
     *
     * @param response
     * @param pagerInfo
     * @return
     */
    @PostMapping("/dict/exportPage")
    @ApiOperation(value = "多条件批次导出", notes = "多条件批次导出")
    public void exportPage(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        dictService.exportPage(response, pagerInfo);
    }

    /**
     * 多条件一次性全部导出
     *
     * @param response
     * @param pagerInfo
     * @return
     */
    @PostMapping("/dict/exportAll")
    @ApiOperation(value = "多条件一次性全部导出", notes = "多条件一次性全部导出")
    public void exportAll(HttpServletResponse response, @RequestBody PagerInfo pagerInfo) {
        dictService.exportAll(response, pagerInfo);
    }


    /**
     * 获取所有字典项
     *
     */
    @ApiOperation(value = "获取所有字典项" , notes = "获取所有字典项")
    @GetMapping(value = "/dicts/all")
    public ResultMsg findAllDicts(){
        //数据格式： {dictCate的type:[{name:value},{...}]}  map<stirng,list<map<String,object>>>
        Map<String,List<Map<String,Object>>> dict = dictService.findAllDicts();
        return ResultMsg.success(dict);

    }
}