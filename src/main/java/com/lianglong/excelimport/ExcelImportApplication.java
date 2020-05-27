package com.lianglong.excelimport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(value = "com.lianglong.excelimport.dao")
@EnableTransactionManagement
public class ExcelImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelImportApplication.class, args);
    }

}
