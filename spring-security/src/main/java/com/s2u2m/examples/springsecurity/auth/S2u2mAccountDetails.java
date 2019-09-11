package com.s2u2m.examples.springsecurity.auth;

import com.s2u2m.examples.springsecurity.auth.constant.LoginType;
import com.s2u2m.examples.springsecurity.user.S2u2mUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class S2u2mAccountDetails extends User {
    private LoginType loginType;
    private S2u2mUser user;

    public S2u2mAccountDetails(
            LoginType loginType, String accountName, S2u2mUser user,
            Collection<? extends GrantedAuthority> authorities) {
        super(accountName, user.getPassword(), authorities);
        this.loginType = loginType;
        this.user = user;
    }
}
