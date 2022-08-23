package com.slipper.daily.modules.constellation;

import lombok.Data;

/**
 * 星座运势响应参数
 * @author gumingchen
 */
@Data
public class ConstellationResponse {
    /**
     * 星座
     */
    private String name;
    /**
     * 速配星座
     */
    private String QFriend;
    /**
     * 幸运色
     */
    private String color;
    /**
     * 总结
     */
    private String summary;
    /**
     * 结果状态码
     */
    private String resultcode;
    /**
     * 状态码
     */
    private Integer error_code;
}
