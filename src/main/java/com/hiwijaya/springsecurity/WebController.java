package com.hiwijaya.springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Happy Indra Wijaya
 */
@Controller
public class WebController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("<h1>spring-security demo</h1>");
    }

}
