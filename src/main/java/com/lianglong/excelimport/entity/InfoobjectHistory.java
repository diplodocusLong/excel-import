package com.lianglong.excelimport.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (InfoobjectHistory)实体类
 *
 * @author makejava
 * @since 2020-05-27 17:41:17
 */

@Accessors(chain = true)
@Data
@RequiredArgsConstructor(staticName = "of")
public class InfoobjectHistory implements Serializable {
    private static final long serialVersionUID = 309341210190887546L;
    /**
    * 主键-递增
    */
    private Long id;
    /**
    * 信息对象id
    */
    private Long objectId;
    /**
    * 信息对象管理设备id
    */
    private String relationObjectId;
    /**
    * 属性id
    */
    private Long propertyId;
    /**
    * 属性名称
    */
    private String propertyName;
    /**
    * 属性编码
    */
    private String propertyCode;
    /**
    * 1:映射属性，2:计算属性
    */
    private Integer propertyType;
    /**
    * 值：格式json
    */
    private String value;
    
    private String storeRule;
    /**
    * 设备采集时间
    */
    private Date deviceTime;
    /**
    * 网关上报时间
    */
    private Date uploadedTime;
    /**
    * 入库时间
    */
    private Date createTime;
    /**
    * iot使用
    */
    private String groupTime;

}