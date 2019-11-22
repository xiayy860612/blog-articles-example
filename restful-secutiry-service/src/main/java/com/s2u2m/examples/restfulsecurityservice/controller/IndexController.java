package com.s2u2m.examples.restfulsecurityservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.s2u2m.examples.restfulsecurityservice.config.HttpHeaderTokenSecurityContextRepository.TOKEN_HEADER;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/{ping}")
    public String pong(@PathVariable("ping") String ping) {
        return ping;
    }

    @PostMapping("/session")
    public String session(HttpServletResponse response) {
        String token = UUID.randomUUID().toString().replace("-", "");
        response.setHeader(TOKEN_HEADER, token);
        return token;
    }
}
