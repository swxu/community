package dev.willsnow.community.service;

import dev.willsnow.community.dto.CommentDTO;
import dev.willsnow.community.enums.CommentTypeEnum;
import dev.willsnow.community.exception.CustomErrorCode;
import dev.willsnow.community.exception.CustomException;
import dev.willsnow.community.mapper.CommentMapper;
import dev.willsnow.community.mapper.QuestionExtMapper;
import dev.willsnow.community.mapper.QuestionMapper;
import dev.willsnow.community.mapper.UserMapper;
import dev.willsnow.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomException(CustomErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomException(CustomErrorCode.TYPE_PARAM_ERROR);
        }

        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            /* 回复评论 */

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

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        // 获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>(commentators);

        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 转换 Comment 为 CommentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}


