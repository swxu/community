package me.will.community.mapper;

import me.will.community.model.Question;

import java.util.List;

/**
 * @author will
 */

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);
}
