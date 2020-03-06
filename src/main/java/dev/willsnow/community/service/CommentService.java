package dev.willsnow.community.service;

import dev.willsnow.community.enums.CommentTypeEnum;
import dev.willsnow.community.exception.CustomErrorCode;
import dev.willsnow.community.exception.CustomException;
import dev.willsnow.community.mapper.CommentMapper;
import dev.willsnow.community.mapper.QuestionExtMapper;
import dev.willsnow.community.mapper.QuestionMapper;
import dev.willsnow.community.model.Comment;
import dev.willsnow.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author will
 */

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomException(CustomErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomException(CustomErrorCode.TYPE_PARAM_ERROR);
        }

        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            /* 回复评论 */

            // 判断该评论所在问题是否有评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            /* 回复问题 */

            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            // 该问题的回复数增加1
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }

    }
}


