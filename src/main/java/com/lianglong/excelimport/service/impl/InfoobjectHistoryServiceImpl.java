package com.lianglong.excelimport.service.impl;

import com.lianglong.excelimport.entity.InfoobjectHistory;
import com.lianglong.excelimport.dao.InfoobjectHistoryDao;
import com.lianglong.excelimport.service.InfoobjectHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (InfoobjectHistory)表服务实现类
 *
 * @author makejava
 * @since 2020-05-27 17:41:32
 */
@Service("infoobjectHistoryService")
public class InfoobjectHistoryServiceImpl implements InfoobjectHistoryService {
    @Resource
    private InfoobjectHistoryDao infoobjectHistoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public InfoobjectHistory queryById(Long id) {
        return this.infoobjectHistoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<InfoobjectHistory> queryAllByLimit(int offset, int limit) {
        return this.infoobjectHistoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param infoobjectHistory 实例对象
     * @return 实例对象
     */
    @Override
    public InfoobjectHistory insert(InfoobjectHistory infoobjectHistory) {
        this.infoobjectHistoryDao.insert(infoobjectHistory);
        return infoobjectHistory;
    }

    /**
     * 修改数据
     *
     * @param infoobjectHistory 实例对象
     * @return 实例对象
     */
    @Override
    public InfoobjectHistory update(InfoobjectHistory infoobjectHistory) {
        this.infoobjectHistoryDao.update(infoobjectHistory);
        return this.queryById(infoobjectHistory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.infoobjectHistoryDao.deleteById(id) > 0;
    }
}