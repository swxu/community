package dev.willsnow.community.dto;

import lombok.Data;

/**
 * @author will
 */

@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
