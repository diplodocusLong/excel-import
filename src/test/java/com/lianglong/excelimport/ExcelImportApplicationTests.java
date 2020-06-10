package com.lianglong.excelimport;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.analysis.ExcelReadExecutor;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.lianglong.excelimport.dao.InfomodelDao;
import com.lianglong.excelimport.dao.InfomodelPropertyDao;
import com.lianglong.excelimport.dao.InfoobjectHistoryDao;
import com.lianglong.excelimport.entity.Infomodel;
import com.lianglong.excelimport.entity.InfomodelProperty;
import com.lianglong.excelimport.entity.InfoobjectHistory;
import com.lianglong.excelimport.listenner.DataListener;
import com.lianglong.excelimport.listenner.InfomodelPropertyListener;
import com.lianglong.excelimport.service.InfomodelService;
import com.lianglong.excelimport.service.InfoobjectHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@SpringBootTest
class ExcelImportApplicationTests {


    @Autowired
    InfomodelService infomodelService;

    @Autowired
    InfomodelPropertyDao infomodelPropertyDao;

    @Autowired
    InfomodelDao infomodelDao;

    @Autowired
    InfoobjectHistoryDao infoobjectHistoryDao;

    @Autowired
    InfoobjectHistoryService infoobjectHistoryService;

    static Map<String, Long> map = new HashMap<>();

    /**
     * 有信息模型目录 使用此方法
     */
    @Test
    public void test() {

        long start  = System.currentTimeMillis();

        File file = new File("/home/nevermore/Desktop/流量计（自供电）0521.xlsx");

        List<Infomodel> list = new ArrayList<>(100);

        EasyExcel.read(file, new DataListener(map,list,infomodelDao)).sheet().doRead();

        List<InfomodelProperty> properties = new ArrayList<>(1000);

        ExcelReader reader = EasyExcel.read(file,new InfomodelPropertyListener(map,properties,infomodelPropertyDao)).headRowNumber(2).build();

        ExcelReadExecutor excelReadExecutor = reader.excelExecutor();

        List<ReadSheet> readSheets = excelReadExecutor.sheetList();

        readSheets.remove(0);

        reader.read(readSheets);

        boolean b = infomodelService.saveBatch(list, properties);

        System.out.println(b);

        long end = System.currentTimeMillis();

        System.out.println(end-start);

    }

    /**
     * 无信息模型目录 使用此方法
     */
    @Test
    public void hell(){


        File file = new File("/home/nevermore/Desktop/流量计（自供电）0521.xlsx");

        List<InfomodelProperty> list = new ArrayList<>(50);

        EasyExcel.read(file,new InfomodelPropertyListener(list,infomodelPropertyDao)).headRowNumber(2).doReadAll();


        int i = infomodelPropertyDao.saveBatch(list);


        System.out.println("总共插入"+i+"条数据");


    }

    @Test
    public void insertTest(){

        List<InfoobjectHistory> infoobjectHistories = new ArrayList<>(500_0000);

        LocalDateTime start = LocalDateTime.now().minusMonths(3);

        for (int i = 0; i < 500_0000; i++) {

            if(i<50000){

                ZoneId zoneId = ZoneId.systemDefault();
                ZonedDateTime zdt = start.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
                Date date = Date.from(zdt.toInstant());

                InfoobjectHistory.of().setDeviceTime(date)
                        .setObjectId(460145857262653440L);

            }

        }

       int row  = infoobjectHistoryDao.insertBatch(infoobjectHistories);


    }


}
