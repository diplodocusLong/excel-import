package com.lianglong.excelimport.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 信息模型(Infomodel)实体类
 *
 * @author makejava
 * @since 2020-05-09 22:11:06
 */

@Data
@Accessors(chain = true)
public class Infomodel implements Serializable {
    private static final long serialVersionUID = -65692499352727948L;
    
    private Long modelId;

    private String name;
    
    private String shortName;

    private String code;
    /**
    * 10=企业，20=系统，30-设备群，40=设备
    */
    private Integer nodeType;
    /**
    * 关联的产品模型
    */
    private String relationModelId;
    /**
    * 所属系统
    */
    private String substanceSystem;
    /**
    * 所属设备群
    */
    private String substanceDeviceGroup;
    /**
    * 所属设备
    */
    private String substanceDevice;
    /**
    * 默认组态图地址
    */
    private String dashboardName;

}