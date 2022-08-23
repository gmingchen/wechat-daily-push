package com.slipper.daily.modules.weather;

import lombok.Data;

import java.util.List;

/**
 * @author gumingchen
 */
@Data
public class Body {
    /**
     * 最近15天天气
     */
    private List<WeatherEntity> forecast;
    /**
     * 昨日天气
     */
    private WeatherEntity yesterday;
}
