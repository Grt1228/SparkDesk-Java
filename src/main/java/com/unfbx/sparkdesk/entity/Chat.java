package com.unfbx.sparkdesk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author https://www.unfbx.com/
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {
    /**
     * 取值为[general,generalv2,generalv3]
     * 指定访问的领域,general指向V1.5版本,generalv2指向V2版本,generalv3指向V3版本 。注意：不同的取值对应的url也不一样！
     */
    private String domain;
    /**
     * 取值为[0,1],默认为0.5
     */
    private double temperature;
    /**
     * 模型回答的tokens的最大长度
     * <p>
     * V1.5取值为[1,4096]
     * V2.0取值为[1,8192]，默认为2048。
     * V3.0取值为[1,8192]，默认为2048。
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    /**
     * 取值为[1，6],默认为4
     * 从k个候选中随机选择⼀个（⾮等概率）
     */
    @JsonProperty("top_k")
    private Integer topK;
    /**
     * 需要保障用户下的唯一性
     */
    @JsonProperty("chat_id")
    private String chatId;
}
