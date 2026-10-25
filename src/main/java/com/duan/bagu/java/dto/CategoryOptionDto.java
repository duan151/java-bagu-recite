package com.duan.bagu.java.dto;

import lombok.Data;

/**
 * 随机抽题弹窗里的分类选项（带题目数量）
 */
@Data
public class CategoryOptionDto {

    /** 分类id，0 表示未分类 */
    private Long id;
    /** 分类名称 */
    private String name;
    /** 当前筛选条件下的题目数量 */
    private Long count;
}
