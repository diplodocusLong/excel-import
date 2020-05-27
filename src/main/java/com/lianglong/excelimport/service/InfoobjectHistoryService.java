package com.lianglong.excelimport.service;

import com.lianglong.excelimport.entity.InfoobjectHistory;
import java.util.List;

/**
 * (InfoobjectHistory)表服务接口
 *
 * @author makejava
 * @since 2020-05-27 17:41:17
 */
public interface InfoobjectHistoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InfoobjectHistory queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<InfoobjectHistory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param infoobjectHistory 实例对象
     * @return 实例对象
     */
    InfoobjectHistory insert(InfoobjectHistory infoobjectHistory);

    /**
     * 修改数据
     *
     * @param infoobjectHistory 实例对象
     * @return 实例对象
     */
    InfoobjectHistory update(InfoobjectHistory infoobjectHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}