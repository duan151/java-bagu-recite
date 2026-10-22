package com.duan.bagu.java.common;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    String code;
    String info;
    T data;

}
