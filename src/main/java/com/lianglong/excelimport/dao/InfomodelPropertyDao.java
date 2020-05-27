package com.lianglong.excelimport.dao;

import com.lianglong.excelimport.entity.Infomodel;
import com.lianglong.excelimport.entity.InfomodelProperty;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 信息模型属性(InfomodelProperty)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-09 22:11:11
 */
public interface InfomodelPropertyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param modelPropertyId 主键
     * @return 实例对象
     */
    InfomodelProperty queryById(Long modelPropertyId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<InfomodelProperty> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param infomodelProperty 实例对象
     * @return 对象列表
     */
    List<InfomodelProperty> queryAll(InfomodelProperty infomodelProperty);

    /**
     * 新增数据
     *
     * @param infomodelProperty 实例对象
     * @return 影响行数
     */
    int insert(InfomodelProperty infomodelProperty);

    /**
     * 修改数据
     *
     * @param infomodelProperty 实例对象
     * @return 影响行数
     */
    int update(InfomodelProperty infomodelProperty);

    /**
     * 通过主键删除数据
     *
     * @param modelPropertyId 主键
     * @return 影响行数
     */
    int deleteById(Long modelPropertyId);

    int saveBatch(List<InfomodelProperty> list);

    @MapKey("shortName")
    Map<String,Map<String, Object>> queryToMap();
}