package com.duan.bagu.java.mapper;

import com.duan.bagu.java.dto.CategoryOptionDto;
import com.duan.bagu.java.dto.QuestionDto;
import com.duan.bagu.java.entity.Category;
import com.duan.bagu.java.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目 Mapper
 */
@Mapper
public interface QuestionMapper {

    /**
     * 滚动加载：按 id 倒序取上一批之后的 size 条
     *
     * @param lastId     上一批最后一条的 id，首次传 0
     * @param size       每批条数
     * @param categoryId 分类id，null/0 表示全部
     * @param keyword    标题关键词，空表示不过滤
     * @param starred    1 表示只看已收藏
     * @param mastered   1 表示只看已掌握
     */
    List<Question> selectList(@Param("lastId") Long lastId,
                              @Param("size") Integer size,
                              @Param("categoryId") Long categoryId,
                              @Param("keyword") String keyword,
                              @Param("starred") Integer starred,
                              @Param("mastered") Integer mastered);

    /**
     * 按主键查询（含答案）
     */
    Question selectById(@Param("id") Long id);

    /**
     * 全部分类
     */
    List<Category> selectCategories();

    /**
     * 新增题目，主键回填
     */
    int insert(QuestionDto question);

    /**
     * 修改题目，只更新非空字段
     */
    int update(QuestionDto question);

    /**
     * 按主键删除
     */
    int deleteById(@Param("id") Long id);

    /**
     * 随机抽题弹窗：按难度/收藏/掌握条件统计各分类题目数
     *
     * @param starred  1 已收藏、-1 未收藏、其他/0 不限
     * @param mastered 1 已掌握、-1 未掌握、其他/0 不限
     */
    List<CategoryOptionDto> countByCategory(@Param("difficulty") Integer difficulty,
                                            @Param("starred") Integer starred,
                                            @Param("mastered") Integer mastered);

    /**
     * 随机抽题：符合条件的总数
     */
    long countRandom(@Param("difficulty") Integer difficulty,
                     @Param("categoryIds") List<Long> categoryIds,
                     @Param("starred") Integer starred,
                     @Param("mastered") Integer mastered);

    /**
     * 随机抽题：按随机 offset 取一条
     */
    Question selectRandom(@Param("difficulty") Integer difficulty,
                          @Param("categoryIds") List<Long> categoryIds,
                          @Param("starred") Integer starred,
                          @Param("mastered") Integer mastered,
                          @Param("offset") long offset);
}
