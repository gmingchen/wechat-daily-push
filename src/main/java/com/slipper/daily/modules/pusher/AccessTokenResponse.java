package com.slipper.daily.modules.pusher;

import lombok.Data;

/**
 * 获取接口调用凭证响应参数
 * @author gumingchen
 */
@Data
public class AccessTokenResponse {
    /**
     * 获取到的凭证
     */
    private String access_token;
    /**
     * 凭证有效时间，单位：秒
     */
    private Long expires_in;
    /**
     * 异常代码
     */
    private Integer errcode;
    /**
     * 异常信息
     */
    private Integer errmsg;
}
