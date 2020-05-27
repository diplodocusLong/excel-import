package com.lianglong.excelimport.controller;

import com.lianglong.excelimport.entity.Infomodel;
import com.lianglong.excelimport.service.InfomodelService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 信息模型(Infomodel)表控制层
 *
 * @author makejava
 * @since 2020-05-09 22:11:10
 */
@RestController
@RequestMapping("infomodel")
public class InfomodelController {
    /**
     * 服务对象
     */
    @Resource
    private InfomodelService infomodelService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Infomodel selectOne(Long id) {
        return this.infomodelService.queryById(id);
    }

}