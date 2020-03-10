package me.will.community.dto;

import lombok.Data;

/**
 * @author will
 */

@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
