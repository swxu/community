package dev.willsnow.community.dto;

import dev.willsnow.community.model.User;
import lombok.Data;

/**
 * @author will
 */

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

    private User user;
}
