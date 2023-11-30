package com.unfbx.sparkdesk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cnzbq
 * @since 11/30/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionCall {
    /**
     * 客户在请求体中定义的参数及参数值
     */
    private String arguments;
    /**
     * 客户在请求体中定义的方法名称
     */
    private String name;
}
