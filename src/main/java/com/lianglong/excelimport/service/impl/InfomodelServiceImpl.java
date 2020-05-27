package com.lianglong.excelimport.service.impl;

import com.lianglong.excelimport.dao.InfomodelPropertyDao;
import com.lianglong.excelimport.entity.Infomodel;
import com.lianglong.excelimport.dao.InfomodelDao;
import com.lianglong.excelimport.entity.InfomodelProperty;
import com.lianglong.excelimport.service.InfomodelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 信息模型(Infomodel)表服务实现类
 *
 * @author makejava
 * @since 2020-05-09 22:11:09
 */
@Service
@Slf4j
public class InfomodelServiceImpl implements InfomodelService {
    @Resource
    private InfomodelDao infomodelDao;

    @Resource
    private InfomodelPropertyDao infomodelPropertyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param modelId 主键
     * @return 实例对象
     */
    @Override
    public Infomodel queryById(Long modelId) {
        return this.infomodelDao.queryById(modelId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Infomodel> queryAllByLimit(int offset, int limit) {
        return this.infomodelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param infomodel 实例对象
     * @return 实例对象
     */
    @Override
    public Infomodel insert(Infomodel infomodel) {
        this.infomodelDao.insert(infomodel);
        return infomodel;
    }

    /**
     * 修改数据
     *
     * @param infomodel 实例对象
     * @return 实例对象
     */
    @Override
    public Infomodel update(Infomodel infomodel) {
        this.infomodelDao.update(infomodel);
        return this.queryById(infomodel.getModelId());
    }

    /**
     * 通过主键删除数据
     *
     * @param modelId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long modelId) {
        return this.infomodelDao.deleteById(modelId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(List<Infomodel> list, List<InfomodelProperty> properties) {

        int i = infomodelDao.saveBatch(list);

        int j = infomodelPropertyDao.saveBatch(properties);

        log.info("模板保存了{}条", i);
        log.info("属性保存了{}条", j);
        return true;
    }
}