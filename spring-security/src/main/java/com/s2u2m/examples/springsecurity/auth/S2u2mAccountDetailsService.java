package com.s2u2m.examples.springsecurity.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface S2u2mAccountDetailsService {

    S2u2mAccountDetails loadAccount(String account) throws UsernameNotFoundException;

    void checkAccount(S2u2mAccountDetails accountDetails, UsernamePasswordAuthenticationToken token);
}
