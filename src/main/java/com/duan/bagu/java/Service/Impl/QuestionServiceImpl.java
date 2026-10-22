package com.duan.bagu.java.Service.Impl;

import com.duan.bagu.java.Service.QuestionService;
import com.duan.bagu.java.dto.QuestionDto;
import com.duan.bagu.java.entity.Category;
import com.duan.bagu.java.entity.Question;
import com.duan.bagu.java.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    /** 单批最大条数，防止前端传过大 */
    private static final int MAX_SIZE = 50;

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<Question> list(Long lastId, Integer size, Long categoryId, String keyword) {
        long cursor = lastId == null ? 0L : lastId;
        int limit = size == null || size <= 0 ? 10 : Math.min(size, MAX_SIZE);
        String searchKeyword = keyword == null ? null : keyword.trim();
        return questionMapper.selectList(cursor, limit, categoryId, searchKeyword);
    }

    @Override
    public Question detail(Long id) {
        return questionMapper.selectById(id);
    }

    @Override
    public List<Category> categories() {
        return questionMapper.selectCategories();
    }

    @Override
    public Long add(QuestionDto questionDto) {
        // 分类允许为空，0 表示未分类（后续可能不只 Java 方向）
        if (questionDto.getCategoryId() == null) {
            questionDto.setCategoryId(0L);
        }
        if (questionDto.getDifficulty() == null) {
            questionDto.setDifficulty(2);
        }
        if (questionDto.getStarred() == null) {
            questionDto.setStarred(0);
        }
        if (questionDto.getMastered() == null) {
            questionDto.setMastered(0);
        }
        questionMapper.insert(questionDto);
        return questionDto.getId();
    }

    @Override
    public void update(QuestionDto questionDto) {
        questionMapper.update(questionDto);
    }

    @Override
    public void delete(Long id) {
        questionMapper.deleteById(id);
    }
}
