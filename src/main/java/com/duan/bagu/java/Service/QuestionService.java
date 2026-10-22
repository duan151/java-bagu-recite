package com.duan.bagu.java.Service;

import com.duan.bagu.java.dto.QuestionDto;
import com.duan.bagu.java.entity.Category;
import com.duan.bagu.java.entity.Question;

import java.util.List;

/**
 * 题目 Service
 */
public interface QuestionService {

    /**
     * 滚动加载列表
     *
     * @param lastId     上一批最后一条 id，首次传 0
     * @param categoryId 分类id，null/0 表示全部
     * @param keyword    标题关键词，空表示不过滤
     */
    List<Question> list(Long lastId, Integer size, Long categoryId, String keyword);

    /**
     * 题目详情（含答案）
     */
    Question detail(Long id);

    /**
     * 全部分类
     */
    List<Category> categories();

    /**
     * 新增，返回主键
     */
    Long add(QuestionDto questionDto);

    /**
     * 修改
     */
    void update(QuestionDto questionDto);

    /**
     * 删除
     */
    void delete(Long id);
}
