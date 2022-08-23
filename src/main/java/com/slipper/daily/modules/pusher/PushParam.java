package com.slipper.daily.modules.pusher;

import lombok.Data;

/**
 * 获取接口调用凭证请求参数
 * @author gumingchen
 */
@Data
public class PushParam {
    /**
     * 用户微信号
     */
    private String touser;
    /**
     * 消息模板ID
     */
    private String template_id;
    /**
     * 顶部颜色
     */
    private String topcolor;
    /**
     * 数据体
     */
    private Body data;
}
