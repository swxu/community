package dev.willsnow.community.mapper;

import dev.willsnow.community.model.Question;

/**
 * @author will
 */

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}
