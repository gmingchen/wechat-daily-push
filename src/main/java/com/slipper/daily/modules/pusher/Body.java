package com.slipper.daily.modules.pusher;

import lombok.Data;

/**
 * 数据对象
 * @author gumingchen
 */
@Data
public class Body {
    /**
     * 生日
     */
    private Value birthday;
    /**
     * 地区
     */
    private Value address;
    /**
     * 今日天气
     */
    private Value todayWeather;
    private Value todayLow;
    private Value todayHigh;
    private Value todayNotice;
    /**
     * 明日天气
     */
    private Value tomorrowWeather;
    private Value tomorrowLow;
    private Value tomorrowHigh;
    private Value tomorrowNotice;
    /**
     * 星座
     */
    private Value constellation;
    /**
     * 速配星座
     */
    private Value constellationFriend;
    /**
     * 幸运色
     */
    private Value constellationColor;
    /**
     * 运势
     */
    private Value constellationSummary;
    /**
     * 彩虹屁
     */
    private Value rainbowFart;
    /**
     * 毒鸡汤
     */
    private Value poisonChickenSoup;
}
