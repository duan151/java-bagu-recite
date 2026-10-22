package com.duan.bagu.java.common;

import com.duan.bagu.java.enums.ResultEnum;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    String code;
    String info;
    T data;

    /**
     * 成功，无数据
     */
    public static <T> Result<T> success() {
        return build(ResultEnum.SUCCESS, null);
    }

    /**
     * 成功，携带数据
     */
    public static <T> Result<T> success(T data) {
        return build(ResultEnum.SUCCESS, data);
    }

    /**
     * 失败，使用枚举中的状态码和提示
     */
    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return build(resultEnum, null);
    }

    private static <T> Result<T> build(ResultEnum resultEnum, T data) {
        return Result.<T>builder()
                .code(resultEnum.getCode())
                .info(resultEnum.getInfo())
                .data(data)
                .build();
    }
}
