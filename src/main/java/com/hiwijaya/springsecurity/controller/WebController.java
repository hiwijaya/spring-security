package com.hiwijaya.springsecurity.controller;

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

    @GetMapping("/public")
    public String publicPage(Model model) {
        model.addAttribute("title", "PUBLIC");
        model.addAttribute("message", "Everyone can access this page.");
        return "content";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        model.addAttribute("title", "PROFILE");
        model.addAttribute("message", "Welcome! You have authority to access this restricted page.");
        return "content";
    }

    @GetMapping("/config")
    public String configPage(Model model) {
        model.addAttribute("title", "CONFIG");
        model.addAttribute("message", "Welcome! You have authority to access this restricted page.");
        return "content";
    }

    @GetMapping("/config/reset")
    public String resetPage(Model model) {
        model.addAttribute("title", "RESET");
        model.addAttribute("message", "Welcome super-admin! only SUPER_ADMIN role who can access this page.");
        return "content";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}