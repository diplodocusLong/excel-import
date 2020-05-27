package com.lianglong.excelimport.service;

import com.lianglong.excelimport.entity.InfomodelProperty;
import java.util.List;

/**
 * 信息模型属性(InfomodelProperty)表服务接口
 *
 * @author makejava
 * @since 2020-05-09 22:11:11
 */
public interface InfomodelPropertyService {

    /**
     * 通过ID查询单条数据
     *
     * @param modelPropertyId 主键
     * @return 实例对象
     */
    InfomodelProperty queryById(Long modelPropertyId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<InfomodelProperty> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param infomodelProperty 实例对象
     * @return 实例对象
     */
    InfomodelProperty insert(InfomodelProperty infomodelProperty);

    /**
     * 修改数据
     *
     * @param infomodelProperty 实例对象
     * @return 实例对象
     */
    InfomodelProperty update(InfomodelProperty infomodelProperty);

    /**
     * 通过主键删除数据
     *
     * @param modelPropertyId 主键
     * @return 是否成功
     */
    boolean deleteById(Long modelPropertyId);

}