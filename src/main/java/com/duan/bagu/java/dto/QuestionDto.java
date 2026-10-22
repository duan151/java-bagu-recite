package com.duan.bagu.java.dto;

import lombok.Data;

/**
 * 题目新增 / 修改入参
 */
@Data
public class QuestionDto {

    /** 主键（修改时必填） */
    private Long id;
    /** 分类id */
    private Long categoryId;
    /** 题目 */
    private String title;
    /** 题目补充说明（Markdown） */
    private String contentMd;
    /** 答案（Markdown） */
    private String answerMd;
    /** 难度：1简单 2中等 3困难 */
    private Integer difficulty;
    /** 是否收藏：0否 1是 */
    private Integer starred;
    /** 是否已掌握：0否 1是 */
    private Integer mastered;
}
