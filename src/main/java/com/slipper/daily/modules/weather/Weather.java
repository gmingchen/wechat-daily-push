package com.slipper.daily.modules.weather;

import com.slipper.daily.config.Config;
import com.slipper.daily.utils.HttpUtils;

/**
 * @author gumingchen
 */
public class Weather {

    public static WeatherResponse getWeather() {
        String url = "http://t.weather.itboy.net/api/weather/city/" + Config.getCityCode();
        WeatherResponse response = HttpUtils.doGet(url, WeatherResponse.class);
        if (response.getStatus() == 200) {
            return response;
        } else {
            System.out.println("获取天气异常");
            return null;
        }
    }
}
