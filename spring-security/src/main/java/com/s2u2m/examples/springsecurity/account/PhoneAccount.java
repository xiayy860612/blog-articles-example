package com.s2u2m.examples.springsecurity.account;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhoneAccount {
    private String phone;
    private String password;
}
