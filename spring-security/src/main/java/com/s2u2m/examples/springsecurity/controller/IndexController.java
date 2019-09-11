package com.s2u2m.examples.springsecurity.controller;

import com.s2u2m.examples.springsecurity.auth.S2u2mAuthenticationDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
public class IndexController {

    @GetMapping("/ping")
    public String ping() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var details = (S2u2mAuthenticationDetails) auth.getDetails();
        return MessageFormat.format("User[{0}] LoginType[{1}]", auth.getName(), details.getLoginType());
    }
}
