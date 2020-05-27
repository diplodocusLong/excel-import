package com.lianglong.excelimport.listenner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lianglong.excelimport.dao.InfomodelPropertyDao;
import com.lianglong.excelimport.entity.InfomodelProperty;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class InfomodelPropertyListener extends AnalysisEventListener<Map<Integer, String>> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */

    private static final int BATCH_COUNT = 500;

    List<InfomodelProperty> list;

    InfomodelPropertyDao infomodelPropertyDao;

    Map<String, Long> foreignKey;

    private static final Map<String, Integer> typeMap;

    private static final Map<String, String> computerMethedMap;


    private Map<String,Map<String,Object>> dbMap;


    static {

        typeMap = new HashMap<>(2);

        typeMap.put("物联获取", 1);

        typeMap.put("计算", 2);


        computerMethedMap = new HashMap<>(6);

        computerMethedMap.put("求和", "sum");
        computerMethedMap.put("期差", "perioddiff");
        computerMethedMap.put("平均", "average");
        computerMethedMap.put("减法", "sub");
        computerMethedMap.put("无", "none");
        computerMethedMap.put("获取","get");


    }




    public InfomodelPropertyListener(Map<String, Long> map, List<InfomodelProperty> properties,InfomodelPropertyDao infomodelPropertyDao) {

        this.list = properties;

        this.foreignKey = map;

        this.infomodelPropertyDao = infomodelPropertyDao;

        this.dbMap = infomodelPropertyDao.queryToMap();
    }

    public InfomodelPropertyListener(List<InfomodelProperty> properties,InfomodelPropertyDao infomodelPropertyDao) {

        this.list = properties;

        this.infomodelPropertyDao = infomodelPropertyDao;

        this.dbMap = infomodelPropertyDao.queryToMap();
    }


    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {

        log.info(data.toString());

        //先默认是第一次导入属性
        String sheetName = context.readSheetHolder().getSheetName();

        Long modelId = null;

        if(foreignKey!=null&&(!foreignKey.isEmpty())){

            modelId = foreignKey.get(sheetName);

        }


        Long modelPropertyId;

        if(modelId!=null){

            modelPropertyId = modelId*10000000000L + context.readRowHolder().getRowIndex();
        }else{

            modelId = (Long)dbMap.get(sheetName).get("modelId");

            Object base = dbMap.get(sheetName).get("base");
            if(base!=null){
                modelPropertyId = (Long)base+context.readRowHolder().getRowIndex();
            }else{
                modelPropertyId = modelId*10000000000L + context.readRowHolder().getRowIndex();

            }

        }
        InfomodelProperty infomodelProperty = new InfomodelProperty()
                .setModelId(modelId)
                .setSeq(Long.valueOf(data.get(0)))
                .setName(data.get(1))
                .setShortName(data.get(1))
                .setType(typeMap.get(data.get(2)))
                .setCode(data.get(4))
                .setModelPropertyId(modelPropertyId);


        for (Map.Entry<String, String> entry : computerMethedMap.entrySet()) {


            if(data.get(5).equals("求平均值")){

                System.out.println(modelPropertyId);
            }

           if(data.get(5).contains(entry.getKey())){



               infomodelProperty.setComputeMethod(entry.getValue());

               break;
           }

        }

        if(infomodelProperty.getComputeMethod()==null){
            infomodelProperty.setComputeMethod(computerMethedMap.get("无"));
        }


        if (data.get(6).contains("自身")) {
            infomodelProperty.setComputeObjectRange("1");
        } else if (data.get(6).contains("子级（全部）")) {
            infomodelProperty.setComputeObjectRange("2");
        } else if (data.get(6).contains("无")) {
            infomodelProperty.setComputeObjectRange("none");
        } else {
            //按条件分
            infomodelProperty.setComputeObjectRange("3");
        }

        if (data.get(7).contains("年内")) {
            infomodelProperty.setComputeTimeRange("1_year");
        } else if (data.get(7).contains("月内")) {
            infomodelProperty.setComputeTimeRange("1_month");
        } else if (data.get(7).contains("日内")) {
            infomodelProperty.setComputeTimeRange("1_day");
        } else if (data.get(7).contains("10分钟内")) {
            infomodelProperty.setComputeTimeRange("10_min");
        } else {
            infomodelProperty.setComputeTimeRange("none");
        }
        infomodelProperty.setComputeRelationCode(data.get(8));

        if (data.get(10).contains("无")) {
            infomodelProperty.setComputeFrequency("none");
        } else if (data.get(10).contains("实时")) {
            infomodelProperty.setComputeFrequency("realtime");
        } else if (data.get(10).contains("日")) {
            infomodelProperty.setComputeFrequency("1_day");
        } else {
            infomodelProperty.setComputeFrequency("10_min");
        }
        infomodelProperty.setStoreDescription(data.get(11));

        if (data.get(12) != null) {
            if (data.get(12).contains("日")) {
                infomodelProperty.setStoreRule("day");
            } else if (data.get(12).contains("月")) {
                infomodelProperty.setStoreRule("month");
            } else if (data.get(12).contains("年")) {
                infomodelProperty.setStoreRule("year");
            } else if (data.get(12).contains("分")) {
                infomodelProperty.setStoreRule("ten_minutes");
            } else if (data.get(12).contains("点位") || data.get(12).contains("变化")) {
                infomodelProperty.setStoreRule("point_value");
            } else if (data.get(12).contains("全部存储")) {
                infomodelProperty.setStoreRule("all");
            }
        } else {
            infomodelProperty.setStoreRule("none");
        }
        if (data.get(14) != null) {
            if (data.get(14).contains("数字量")) {
                infomodelProperty.setDigitalAnalogType(1);
            } else if (data.get(14).contains("模拟量")) {
                infomodelProperty.setDigitalAnalogType(2);
            } else if (data.get(14).contains("开关量")) {
                infomodelProperty.setDigitalAnalogType(0);
            }
        }

        if (data.get(15).contains("REAL")) {
            infomodelProperty.setDataType(1);
        } else {
            infomodelProperty.setDataType(0);
        }
        if (data.get(16).contains("读写")) {
            infomodelProperty.setReadWriteType(2);
        } else if (data.get(16).contains("写")) {
            infomodelProperty.setReadWriteType(3);
        } else if (data.get(16).contains("只读")) {
            infomodelProperty.setReadWriteType(1);
        }
        infomodelProperty.setUnitName(data.get(17));
        infomodelProperty.setUnitMark(data.get(18));
        infomodelProperty.setDescription(data.get(19));
        list.add(infomodelProperty);


     /*   if (list.size() >= BATCH_COUNT) {

            saveData(list);


            list.clear();


        }*/

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

/*

        if (list.size() == 0) {
            return;
        }
        saveData(list);


        list.clear();
*/


    }


    /**
     * 加上存储数据库
     */

    private void saveData(List<InfomodelProperty> list) {

        log.info("{}条数据，开始存储数据库！", list.size());

        infomodelPropertyDao.saveBatch(list);

        log.info("存储数据库成功！");

    }

}
