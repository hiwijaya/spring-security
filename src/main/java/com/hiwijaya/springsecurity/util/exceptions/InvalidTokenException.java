package com.hiwijaya.springsecurity.util.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Happy Indra Wijaya
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String message) {
        super(message);
    }

}
