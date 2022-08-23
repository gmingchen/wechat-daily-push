package com.slipper.daily.modules.weather;

import lombok.Data;

/**
 * 天气响应参数
 * @author gumingchen
 */
@Data
public class WeatherResponse {
    /**
     * 城市信息
     */
    private CityEntity cityInfo;
    /**
     * 天气信息
     */
    private Body data;
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 信息
     */
    private String message;
}
