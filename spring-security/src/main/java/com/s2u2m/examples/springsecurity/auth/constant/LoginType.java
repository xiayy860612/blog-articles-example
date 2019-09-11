package com.s2u2m.examples.springsecurity.auth.constant;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum LoginType {
    PHONE_PASSWORD,
    ;

    public static LoginType get(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return Arrays.stream(values())
                .filter(loginType -> loginType.name().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }
}
