package com.slipper.daily.modules.weather;

import lombok.Data;

/**
 * 天气
 * @author gumingchen
 */
@Data
public class WeatherEntity {
    /**
     * 年月日
     */
    private String ymd;
    /**
     * 最高温度
     */
    private String high;
    /**
     * 最低温度
     */
    private String low;
    /**
     * 星期
     */
    private String week;
    /**
     * 日出时间
     */
    private String sunrise;
    /**
     * 日落时间
     */
    private String sunset;
    /**
     * 天气情况
     */
    private String type;
    /**
     * 提示
     */
    private String notice;
}
