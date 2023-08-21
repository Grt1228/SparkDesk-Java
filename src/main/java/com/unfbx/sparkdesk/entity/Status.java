package com.unfbx.sparkdesk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会话状态，取值为[0,1,2]；0代表首次结果；1代表中间结果；2代表最后一个结果
 *
 * @author https://www.unfbx.com/
 */
@Getter
public enum Status {
    START(0),
    ING(1),
    END(2),
    ;

    Status(int value) {
        this.value = value;
    }

    private final int value;
}
