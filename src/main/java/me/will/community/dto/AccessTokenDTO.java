package me.will.community.dto;

import lombok.Data;

/**
 * Access token data transfer object in network.
 *
 * @author will
 */

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;
}
