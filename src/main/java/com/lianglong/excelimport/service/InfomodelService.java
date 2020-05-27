package com.lianglong.excelimport.service;

import com.lianglong.excelimport.entity.Infomodel;
import com.lianglong.excelimport.entity.InfomodelProperty;

import java.util.List;

/**
 * 信息模型(Infomodel)表服务接口
 *
 * @author makejava
 * @since 2020-05-09 22:11:09
 */
public interface InfomodelService {

    /**
     * 通过ID查询单条数据
     *
     * @param modelId 主键
     * @return 实例对象
     */
    Infomodel queryById(Long modelId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Infomodel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param infomodel 实例对象
     * @return 实例对象
     */
    Infomodel insert(Infomodel infomodel);

    /**
     * 修改数据
     *
     * @param infomodel 实例对象
     * @return 实例对象
     */
    Infomodel update(Infomodel infomodel);

    /**
     * 通过主键删除数据
     *
     * @param modelId 主键
     * @return 是否成功
     */
    boolean deleteById(Long modelId);

    /**
     * 保存所有excel读到的数据
     * @param list
     * @param properties
     * @return
     */
    boolean saveBatch(List<Infomodel> list, List<InfomodelProperty> properties);

}