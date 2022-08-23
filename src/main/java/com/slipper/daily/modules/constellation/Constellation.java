package com.slipper.daily.modules.constellation;

import com.slipper.daily.config.Config;
import com.slipper.daily.utils.DateUtils;
import com.slipper.daily.utils.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

public class Constellation {

    public static final String[] CONSTELLATIONS = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
    public static final int[] CONSTELLATION_EDGE_DAY = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };

    private static String getConstellationString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.stringToDate(Config.getBirthday() + " 00:00:00"));
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < CONSTELLATION_EDGE_DAY[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return CONSTELLATIONS[month];
        }
        return CONSTELLATIONS[11];
    }

    public static ConstellationResponse getConstellation() {
        String url = "http://web.juhe.cn/constellation/getAll";
        ConstellationParam param = new ConstellationParam();
        param.setKey(Config.getConstellationKey());
        try {
            param.setConsName(URLEncoder.encode(getConstellationString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        param.setType("today");
        ConstellationResponse response = HttpUtils.doGet(url, param, ConstellationResponse.class);
        if (response.getError_code() == 0) {
            return response;
        } else {
            System.out.println("获取星座运势异常");
            return null;
        }
    }

}
