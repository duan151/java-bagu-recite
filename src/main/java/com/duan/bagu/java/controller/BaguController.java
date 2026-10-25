package com.duan.bagu.java.controller;

import com.duan.bagu.java.Service.QuestionService;
import com.duan.bagu.java.common.Result;
import com.duan.bagu.java.dto.CategoryOptionDto;
import com.duan.bagu.java.dto.QuestionDto;
import com.duan.bagu.java.entity.Category;
import com.duan.bagu.java.entity.Question;
import com.duan.bagu.java.enums.ResultEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bagu/")
public class BaguController {

    @Resource
    private QuestionService questionService;

    /**
     * 滚动加载列表，可按分类筛选、按标题搜索
     *
     * @param lastId     上一批最后一条的 id，首次传 0
     * @param size       每批条数
     * @param categoryId 分类id，不传或 0 表示全部
     * @param keyword    标题关键词，不传表示不过滤
     * @param starred    1 表示只看已收藏
     * @param mastered   1 表示只看已掌握
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<List<Question>> list(@RequestParam(defaultValue = "0") Long lastId,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(required = false) Long categoryId,
                                       @RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) Integer starred,
                                       @RequestParam(required = false) Integer mastered) {
        return Result.success(questionService.list(lastId, size, categoryId, keyword, starred, mastered));
    }

    /**
     * 题目详情（含答案）
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Result<Question> detail(@RequestParam Long id) {
        Question question = questionService.detail(id);
        if (question == null) {
            return Result.fail(ResultEnum.DATA_NOT_FOUND);
        }
        return Result.success(question);
    }

    /**
     * 全部分类（新增/修改时的下拉框）
     */
    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public Result<List<Category>> categories() {
        return Result.success(questionService.categories());
    }

    /**
     * 新增题目
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result<Long> addBagu(QuestionDto questionDto) {
        if (questionDto == null || isBlank(questionDto.getTitle())) {
            return Result.fail(ResultEnum.PARAM_INVALID);
        }
        return Result.success(questionService.add(questionDto));
    }

    /**
     * 修改题目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result<Long> update(QuestionDto questionDto) {
        if (questionDto == null || questionDto.getId() == null || isBlank(questionDto.getTitle())) {
            return Result.fail(ResultEnum.PARAM_INVALID);
        }
        questionService.update(questionDto);
        return Result.success(questionDto.getId());
    }

    /**
     * 收藏 / 已掌握 标记：只传需要修改的字段
     */
    @RequestMapping(value = "mark", method = RequestMethod.POST)
    public Result<Long> mark(QuestionDto questionDto) {
        if (questionDto == null || questionDto.getId() == null
                || (questionDto.getStarred() == null && questionDto.getMastered() == null)) {
            return Result.fail(ResultEnum.PARAM_INVALID);
        }
        questionService.update(questionDto);
        return Result.success(questionDto.getId());
    }

    /**
     * 随机抽题弹窗：各分类题目数量（按难度/收藏/掌握过滤）
     */
    @RequestMapping(value = "random/options", method = RequestMethod.GET)
    public Result<List<CategoryOptionDto>> randomOptions(@RequestParam(required = false) Integer difficulty,
                                                         @RequestParam(required = false) Integer starred,
                                                         @RequestParam(required = false) Integer mastered) {
        return Result.success(questionService.randomOptions(difficulty, starred, mastered));
    }

    /**
     * 随机抽一道题（含答案）
     */
    @RequestMapping(value = "random", method = RequestMethod.GET)
    public Result<Question> random(@RequestParam(required = false) Integer difficulty,
                                   @RequestParam(required = false) List<Long> categoryIds,
                                   @RequestParam(required = false) Integer starred,
                                   @RequestParam(required = false) Integer mastered) {
        Question question = questionService.random(difficulty, categoryIds, starred, mastered);
        if (question == null) {
            return Result.fail(ResultEnum.NO_QUESTION);
        }
        return Result.success(question);
    }

    /**
     * 删除题目
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result<Void> delete(@RequestParam Long id) {
        if (id == null) {
            return Result.fail(ResultEnum.PARAM_INVALID);
        }
        questionService.delete(id);
        return Result.success();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
