package com.s2u2m.examples.springsecurity.auth.service;

import com.s2u2m.examples.springsecurity.auth.S2u2mAccountDetails;
import com.s2u2m.examples.springsecurity.auth.S2u2mAccountDetailsService;
import com.s2u2m.examples.springsecurity.auth.constant.LoginType;
import com.s2u2m.examples.springsecurity.user.S2u2mUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class PhonePasswordAccountDetailsService implements S2u2mAccountDetailsService {

    @Override
    public S2u2mAccountDetails loadAccount(String account) throws UsernameNotFoundException {
        var user = S2u2mUser.builder()
                .id("123456")
                .password("123456")
                .build();
        return new S2u2mAccountDetails(LoginType.PHONE_PASSWORD, account, user, Collections.emptyList());
    }

    @Override
    public void checkAccount(S2u2mAccountDetails accountDetails, UsernamePasswordAuthenticationToken token) {
        if (token.getCredentials() == null) {
            throw new BadCredentialsException("no credentials provided");
        }

        String presentedPassword = token.getCredentials().toString();
        if (!presentedPassword.equals(accountDetails.getPassword())) {
            throw new BadCredentialsException("password does not match stored value");
        }
    }
}
