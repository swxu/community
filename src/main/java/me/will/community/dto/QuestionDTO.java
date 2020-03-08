package me.will.community.dto;

import me.will.community.model.User;
import lombok.Data;

/**
 * @author will
 */

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

    private User user;
}
