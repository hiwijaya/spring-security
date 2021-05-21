package com.hiwijaya.springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Happy Indra Wijaya
 */
@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/content")
    public ResponseEntity<String> content() {
        return ResponseEntity.ok("This is protected content.");
    }

    @GetMapping("/restricted")
    public ResponseEntity<String> restricted() {
        return ResponseEntity.ok("Only user with ADMIN role who can access this page.");
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
