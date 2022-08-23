package com.slipper.daily.modules.pusher;

import lombok.Data;

/**
 * 获取接口调用凭证请求参数
 * @author gumingchen
 */
@Data
public class AccessTokenParam {
    /**
     * 获取access_token填写client_credential
     */
    private String grant_type;
    /**
     * 第三方用户唯一凭证
     */
    private String appid;
    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    private String secret;
}
