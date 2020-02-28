package dev.willsnow.community.model;


import lombok.Data;

/**
 * @author will
 */

/* lombok @Data 注解自动生成类中各字段的 Getter Setter toString HashCode 等方法 */

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
