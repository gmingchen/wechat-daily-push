package com.slipper.daily.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gumingchen
 */
@Component
@ConfigurationProperties(prefix = "config")
public class Config {
    /**
     * AppId
     */
    private static String appId;
    /**
     * AppSecret
     */
    private static String appSecret;
    /**
     * 模版ID
     */
    private static String templateId;
    /**
     * 要发送的微信号
     */
    private static String wechatNumber;
    /**
     * 生日
     */
    private static String birthday;
    /**
     * 城市编码
     */
    private static String cityCode;
    /**
     * 星座查询Key
     */
    private static String constellationKey;

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        Config.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        Config.appSecret = appSecret;
    }

    public static String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        Config.templateId = templateId;
    }

    public static String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        Config.wechatNumber = wechatNumber;
    }

    public static String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        Config.birthday = birthday;
    }

    public static String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        Config.cityCode = cityCode;
    }

    public static String getConstellationKey() {
        return constellationKey;
    }

    public void setConstellationKey(String constellationKey) {
        Config.constellationKey = constellationKey;
    }
}
