package dev.willsnow.community.dto;

import lombok.Data;

/**
 * @author will
 */

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
