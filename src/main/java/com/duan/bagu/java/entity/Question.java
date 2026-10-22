package com.duan.bagu.java.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 面试题（八股题）
 */
@Data
public class Question {

    /** 主键 */
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
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
}
