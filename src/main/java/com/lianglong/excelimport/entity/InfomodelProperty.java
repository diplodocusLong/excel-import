package com.lianglong.excelimport.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 信息模型属性(InfomodelProperty)实体类
 *
 * @author makejava
 * @since 2020-05-09 22:11:11
 */

@Data
@Accessors(chain = true)
public class InfomodelProperty implements Serializable {
    private static final long serialVersionUID = -99808603201110017L;
    
    private Long modelPropertyId;
    /**
    * 所属模型
    */
    private Long modelId;
    /**
    * 属性序号
    */
    private Long seq;
    /**
    * 属性名称
    */
    private String name;
    
    private String shortName;
    
    private String code;
    /**
    * 单位名称
    */
    private String unitName;
    /**
    * 单位符号
    */
    private String unitMark;
    /**
    * 1=映射属性，2=计算属性
    */
    private Integer type;
    /**
    * 1=String
    */
    private Integer dataType;
    
    private String computeFrequency;
    /**
    * 计算方法 如 sum等
    */
    private String computeMethod;
    /**
    * 计算数据节点范围1=自身 2=子级（全部）3=子级（条件）none=无
    */
    private String computeObjectRange;
    
    private String computeTimeRange;
    
    private String storeRule;
    /**
    * 1=只读,2=读写
    */
    private Integer readWriteType;
    /**
    * 1=数字量,2=模拟量,0=开关量
    */
    private Integer digitalAnalogType;
    
    private String computeRelationCode;
    /**
    * 计算说明
    */
    private String computeDescription;
    /**
    * 解释说明
    */
    private String description;
    /**
    * 存储说明
    */
    private String storeDescription;

}