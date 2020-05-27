package com.lianglong.excelimport.controller;

import com.lianglong.excelimport.entity.InfomodelProperty;
import com.lianglong.excelimport.service.InfomodelPropertyService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 信息模型属性(InfomodelProperty)表控制层
 *
 * @author makejava
 * @since 2020-05-09 22:11:11
 */
@RestController
@RequestMapping("infomodelProperty")
public class InfomodelPropertyController {
    /**
     * 服务对象
     */
    @Resource
    private InfomodelPropertyService infomodelPropertyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public InfomodelProperty selectOne(Long id) {
        return this.infomodelPropertyService.queryById(id);
    }



}