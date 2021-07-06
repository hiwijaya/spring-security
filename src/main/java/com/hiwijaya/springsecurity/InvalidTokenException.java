package com.hiwijaya.springsecurity;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Happy Indra Wijaya
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String message) {
        super(message);
    }

}
