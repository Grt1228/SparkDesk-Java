package com.unfbx.sparkdesk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author cnzbq
 * @since 11/30/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Function {
    /**
     * 列表形式，列表中的元素是json格式
     * 元素中包含name、description、parameters属性
     */
    private List<Text> text;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Text {
        /**
         * function名称	用户输入命中后，会返回该名称
         */
        private String name;
        /**
         * function功能描述	描述function功能即可，越详细越有助于大模型理解该function
         */
        private String description;
        /**
         * function参数列表
         */
        private Parameter parameters;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parameter {
        /**
         * 参数类型
         */
        private String type;
        /**
         * 参数信息描述
         * 该内容由用户定义，命中该方法时需要返回哪些参数
         */
        private Map<String, Properties> properties;
        /**
         * 必须返回的参数列表
         * 该内容由用户定义，命中方法时必须返回的字段
         */
        private List<String> required;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Properties {
        /**
         * 参数类型描述
         * 该内容由用户定义，需要返回的参数是什么类型
         */
        private String type;
        /**
         * 参数详细描述
         * 该内容由用户定义，需要返回的参数的具体描述
         */
        private String description;
    }
}
