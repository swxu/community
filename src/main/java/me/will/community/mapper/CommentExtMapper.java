package me.will.community.mapper;

import me.will.community.model.Comment;

/**
 * @author will
 */

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
