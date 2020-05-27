package com.lianglong.excelimport.service.impl;

import com.lianglong.excelimport.entity.InfomodelProperty;
import com.lianglong.excelimport.dao.InfomodelPropertyDao;
import com.lianglong.excelimport.service.InfomodelPropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 信息模型属性(InfomodelProperty)表服务实现类
 *
 * @author makejava
 * @since 2020-05-09 22:11:11
 */
@Service
public class InfomodelPropertyServiceImpl implements InfomodelPropertyService {
    @Resource
    private InfomodelPropertyDao infomodelPropertyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param modelPropertyId 主键
     * @return 实例对象
     */
    @Override
    public InfomodelProperty queryById(Long modelPropertyId) {
        return this.infomodelPropertyDao.queryById(modelPropertyId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<InfomodelProperty> queryAllByLimit(int offset, int limit) {
        return this.infomodelPropertyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param infomodelProperty 实例对象
     * @return 实例对象
     */
    @Override
    public InfomodelProperty insert(InfomodelProperty infomodelProperty) {
        this.infomodelPropertyDao.insert(infomodelProperty);
        return infomodelProperty;
    }

    /**
     * 修改数据
     *
     * @param infomodelProperty 实例对象
     * @return 实例对象
     */
    @Override
    public InfomodelProperty update(InfomodelProperty infomodelProperty) {
        this.infomodelPropertyDao.update(infomodelProperty);
        return this.queryById(infomodelProperty.getModelPropertyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param modelPropertyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long modelPropertyId) {
        return this.infomodelPropertyDao.deleteById(modelPropertyId) > 0;
    }
}