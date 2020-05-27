package com.lianglong.excelimport.listenner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lianglong.excelimport.dao.InfomodelDao;
import com.lianglong.excelimport.entity.Infomodel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DataListener extends AnalysisEventListener<Map<Integer, String>> {


    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */

    private static final int BATCH_COUNT = 500;

    List<Infomodel> list;

    InfomodelDao infomodelDao;

    private int baseInt;

    Map<String, Long> foreignKey;
    private static final Map<String, Integer> map;

    static {
        map = new HashMap<>();
        // 10=企业，20=系统，30-设备群，40=设备
        map.put("企业级", 10);
        map.put("系统级", 20);
        map.put("设备群级", 30);
        map.put("设备级", 40);
    }



    public DataListener(Map<String, Long> map, List<Infomodel> list,InfomodelDao infomodelDao) {


        this.list = list;
        this.infomodelDao = infomodelDao;
        this.foreignKey = map;

        this.baseInt = infomodelDao.infomodelSize();
    }


    @Override

    public void invoke(Map<Integer, String> data, AnalysisContext context) {

        log.info(data.toString());

        Infomodel infomodel = new Infomodel()
                .setName(data.get(1))
                .setCode(data.get(2))
                .setNodeType(map.get(data.get(3)))
                .setSubstanceSystem(data.get(4))
                .setSubstanceDeviceGroup(data.get(5))
                .setSubstanceDevice(data.get(6))
                .setRelationModelId("0");

        if (data.get(1).contains("信息模型")) {
            String shortName = data.get(1).substring(0, data.get(1).indexOf("信息模型"));
            infomodel.setShortName(shortName);
        } else {
            infomodel.setShortName(data.get(1));
        }

        Integer rowIndex = context.readRowHolder().getRowIndex();


        infomodel.setModelId((long) 10000000 * (baseInt + 10 + rowIndex));

        list.add(infomodel);

     /*   if (list.size() >= BATCH_COUNT) {

            saveData(list);

            list.clear();

        }*/

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {


        //  saveData(list);

        for (Infomodel infomodel : list) {

            foreignKey.put(infomodel.getShortName(),infomodel.getModelId());

        }

    }


    /**
     * 加上存储数据库
     */

    private void saveData(List<Infomodel> list) {

        log.info("{}条数据，开始存储数据库！", list.size());


        infomodelDao.saveBatch(list);

        log.info("存储数据库成功！");

    }
}
