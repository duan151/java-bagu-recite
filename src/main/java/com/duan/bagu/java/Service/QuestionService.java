package com.duan.bagu.java.Service;

import com.duan.bagu.java.dto.CategoryOptionDto;
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
     * @param starred    1 表示只看已收藏
     * @param mastered   1 表示只看已掌握
     */
    List<Question> list(Long lastId, Integer size, Long categoryId, String keyword,
                        Integer starred, Integer mastered);

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

    /**
     * 随机抽题弹窗：按难度/收藏/掌握统计各分类题目数
     */
    List<CategoryOptionDto> randomOptions(Integer difficulty, Integer starred, Integer mastered);

    /**
     * 随机抽取一道题（含答案），没有符合条件时返回 null
     */
    Question random(Integer difficulty, List<Long> categoryIds, Integer starred, Integer mastered);
}
