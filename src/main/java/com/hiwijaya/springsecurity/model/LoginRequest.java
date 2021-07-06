package com.hiwijaya.springsecurity.model;

import lombok.Data;

/**
 * @author Happy Indra Wijaya
 */
@Data
public class LoginRequest {

    private String username;
    private String password;

}
