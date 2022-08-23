package com.slipper.daily.modules.constellation;

import lombok.Data;

/**
 * 星座运势请求参数
 * @author gumingchen
 */
@Data
public class ConstellationParam {
    /**
     * 星座名称
     */
    private String consName;
    /**
     * 运势类型：today,tomorrow,week,month,year
     */
    private String type;
    /**
     * 接口调用Key
     */
    private String key;
}
