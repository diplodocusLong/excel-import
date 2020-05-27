package com.lianglong.excelimport.dao;

import com.lianglong.excelimport.entity.Infomodel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 信息模型(Infomodel)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-09 22:11:08
 */

@Mapper
public interface InfomodelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param modelId 主键
     * @return 实例对象
     */
    Infomodel queryById(Long modelId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Infomodel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param infomodel 实例对象
     * @return 对象列表
     */
    List<Infomodel> queryAll(Infomodel infomodel);

    /**
     * 新增数据
     *
     * @param infomodel 实例对象
     * @return 影响行数
     */
    int insert(Infomodel infomodel);

    /**
     * 修改数据
     *
     * @param infomodel 实例对象
     * @return 影响行数
     */
    int update(Infomodel infomodel);

    /**
     * 通过主键删除数据
     *
     * @param modelId 主键
     * @return 影响行数
     */
    int deleteById(Long modelId);

    int saveBatch(List<Infomodel> list);

    int infomodelSize();
}