package me.will.community.dto;

import lombok.Data;

/**
 * @author will
 */

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private Long outerid;
    private String outerTitle;
    private Integer type;
    private String typeName;
}
