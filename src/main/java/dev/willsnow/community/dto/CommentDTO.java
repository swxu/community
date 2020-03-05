package dev.willsnow.community.dto;

import lombok.Data;

/**
 * @author will
 */

@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
