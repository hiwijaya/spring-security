package com.hiwijaya.springsecurity.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Happy Indra Wijaya
 */
@Data
@Builder
public class LoginResponse {

    private String username;
    private String token;

}
