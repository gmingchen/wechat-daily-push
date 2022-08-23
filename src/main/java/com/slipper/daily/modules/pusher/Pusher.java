package com.slipper.daily.modules.pusher;

import com.slipper.daily.config.Config;
import com.slipper.daily.modules.constellation.Constellation;
import com.slipper.daily.modules.constellation.ConstellationResponse;
import com.slipper.daily.modules.weather.Weather;
import com.slipper.daily.modules.weather.WeatherResponse;
import com.slipper.daily.utils.DateUtils;
import com.slipper.daily.utils.HttpUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author gumingchen
 */
@Component
@EnableScheduling
public class Pusher {
    /**
     * 获取接口调用凭证
     * @return
     */
    private static String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        AccessTokenParam param = new AccessTokenParam();
        param.setGrant_type("client_credential");
        param.setAppid(Config.getAppId());
        param.setSecret(Config.getAppSecret());
        AccessTokenResponse response = HttpUtils.doGet(url, param, AccessTokenResponse.class);
        if (response.getErrcode() == null || response.getErrcode() == 0) {
            return response.getAccess_token();
        } else {
            System.out.println("获取接口调用凭证异常");
            return null;
        }
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public static void push() {
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        PushParam param = new PushParam();
        param.setTemplate_id(Config.getTemplateId());
        param.setTouser(Config.getWechatNumber());
        param.setTopcolor("#FF0000");

        Body body = new Body();
        // 设置生日
        setBirthday(body);
        // 设置城市、天气
        setWeather(body);
        // 设置星座 运势
        setConstellation(body);

        param.setData(body);
        HttpUtils.doPost(url, param);
    }

    /***
     * 设置生日剩余天数
     * @param body
     */
    private static void setBirthday(Body body) {
        int days = 0;
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar todayCalendar = Calendar.getInstance(); // 存今天
            Calendar birthdayCalendar = Calendar.getInstance(); // 存生日
            birthdayCalendar.setTime(myFormatter.parse(Config.getBirthday())); // 设置生日
            birthdayCalendar.set(Calendar.YEAR, todayCalendar.get(Calendar.YEAR)); // 修改为本年
            if (birthdayCalendar.get(Calendar.DAY_OF_YEAR) < todayCalendar.get(Calendar.DAY_OF_YEAR)) {
                // 生日已经过了，要算明年的了
                days = todayCalendar.getActualMaximum(Calendar.DAY_OF_YEAR) - todayCalendar.get(Calendar.DAY_OF_YEAR);
                days += birthdayCalendar.get(Calendar.DAY_OF_YEAR);
            } else {
                // 生日还没过
                days = birthdayCalendar.get(Calendar.DAY_OF_YEAR) - todayCalendar.get(Calendar.DAY_OF_YEAR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Value birthday = new Value();
        birthday.setValue(days + "");
        birthday.setColor("#FF0000");
        body.setBirthday(birthday);
    }

    /**
     * 设置城市、天气
     * @param body
     */
    private static void setWeather(Body body) {
        WeatherResponse response = Weather.getWeather();
        // 设置地区
        Value address = new Value();
        address.setValue(response.getCityInfo().getParent() + "-" + response.getCityInfo().getCity());
        address.setColor("#FF7F00");
        body.setAddress(address);
        // 设置今日天气
        Value todayWeather = new Value();
        todayWeather.setValue(response.getData().getForecast().get(0).getType());
        todayWeather.setColor("#00FF00");
        body.setTodayWeather(todayWeather);
        // 设置今日最低温
        Value todayLow = new Value();
        todayLow.setValue(response.getData().getForecast().get(0).getLow().split(" ")[1]);
        todayLow.setColor("#0000FF");
        body.setTodayLow(todayLow);
        // 设置今日最高温
        Value todayHigh = new Value();
        todayHigh.setValue(response.getData().getForecast().get(0).getHigh().split(" ")[1]);
        todayHigh.setColor("#0000FF");
        body.setTodayHigh(todayHigh);
        // 设置今日提醒
        Value todayNotice = new Value();
        todayNotice.setValue(response.getData().getForecast().get(0).getNotice());
        todayNotice.setColor("#00FFFF");
        body.setTodayNotice(todayNotice);

        // 设置明日天气
        Value tomorrowWeather = new Value();
        tomorrowWeather.setValue(response.getData().getForecast().get(1).getType());
        tomorrowWeather.setColor("#00FF00");
        body.setTomorrowWeather(tomorrowWeather);
        // 设置明日最低温
        Value tomorrowLow = new Value();
        tomorrowLow.setValue(response.getData().getForecast().get(1).getLow().split(" ")[1]);
        tomorrowLow.setColor("#0000FF");
        body.setTomorrowLow(tomorrowLow);
        // 设置明日最高温
        Value tomorrowHigh = new Value();
        tomorrowHigh.setValue(response.getData().getForecast().get(1).getHigh().split(" ")[1]);
        tomorrowHigh.setColor("#0000FF");
        body.setTomorrowHigh(tomorrowHigh);
        // 设置明日提醒
        Value tomorrowNotice = new Value();
        tomorrowNotice.setValue(response.getData().getForecast().get(1).getNotice());
        tomorrowNotice.setColor("#00FFFF");
        body.setTomorrowNotice(tomorrowNotice);

    }

    /**
     * 设置星座 运势
     * @param body
     */
    private static void setConstellation(Body body) {
        ConstellationResponse response = Constellation.getConstellation();
        // 设置星座
        Value constellation = new Value();
        constellation.setValue(response.getName());
        constellation.setColor("#8B00FF");
        body.setConstellation(constellation);
        // 设置速配星座
        Value constellationFriend = new Value();
        constellationFriend.setValue(response.getQFriend());
        constellationFriend.setColor("#FF0000");
        body.setConstellationFriend(constellationFriend);
        // 设置幸运色
        Value constellationColor = new Value();
        constellationColor.setValue(response.getColor());
        constellationColor.setColor("#FF7F00");
        body.setConstellationColor(constellationColor);
        // 设置运势
        Value constellationSummary = new Value();
        constellationSummary.setValue(response.getSummary());
        constellationSummary.setColor("#0000FF");
        body.setConstellationSummary(constellationSummary);
    }
}
