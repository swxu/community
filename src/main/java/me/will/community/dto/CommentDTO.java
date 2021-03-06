package me.will.community.dto;

import me.will.community.model.User;
import lombok.Data;

/**
 * @author will
 */

@Data
public class CommentDTO {

    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
    private Integer commentCount;

}
