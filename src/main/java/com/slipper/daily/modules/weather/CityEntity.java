package com.slipper.daily.modules.weather;

import lombok.Data;

/**
 * 天气
 * @author gumingchen
 */
@Data
public class CityEntity {
    /**
     * 城市
     */
    private String city;
    /**
     * 上级城市
     */
    private String parent;
}
