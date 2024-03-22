package com.renshuo.cloud.user.model;

import lombok.Data;

/**
 * @description: 离线导出条数
 * @author: renshuo
 * @date: 2023/10/16
 */
@Data
public class OffLineExportPageModel {

    /**
     * 总条数
     */
    private int total;

    /**
     * 每页查询条数
     */
    private int pageSize;
    /**
     * 最后一页查询条数
     */
    private int lastPageSize;
    /**
     * 总页数
     */
    private int pageTotal;

    public OffLineExportPageModel(int total) {
        this.total = total;
        if (total >= 10000) {
            this.pageTotal = 20;
            int pageSize = total / pageTotal;
            if (total % pageTotal != 0) {
                pageSize += 1;
            }
            this.lastPageSize = total - (pageSize * (pageTotal - 1));

        } else {
            this.pageTotal = 1;
            this.pageSize = total;
            this.lastPageSize = total;
        }


    }

}
