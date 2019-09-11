package com.s2u2m.examples.springsecurity.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class S2u2mUser {
    private String id;
    private String password;
}
