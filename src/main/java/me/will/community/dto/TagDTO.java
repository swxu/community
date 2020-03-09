package me.will.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author will
 */

@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
