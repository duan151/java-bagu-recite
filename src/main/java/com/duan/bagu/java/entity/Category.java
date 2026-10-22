package com.duan.bagu.java.entity;

import lombok.Data;

/**
 * 题目分类
 */
@Data
public class Category {

    /** 主键 */
    private Long id;
    /** 分类名称 */
    private String name;
    /** 排序 */
    private Integer sort;
}
