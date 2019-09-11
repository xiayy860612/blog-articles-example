package com.s2u2m.examples.springsecurity.auth;

import com.s2u2m.examples.springsecurity.auth.constant.LoginType;
import com.s2u2m.examples.springsecurity.auth.service.PhonePasswordAccountDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;

public class S2u2mAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        // check if login info is correct
        var details = (S2u2mAuthenticationDetails) token.getDetails();
        var service = this.getDetailService(details.getLoginType());
        service.checkAccount((S2u2mAccountDetails) userDetails, token);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var details = (S2u2mAuthenticationDetails) authentication.getDetails();
        if (ObjectUtils.isEmpty(details.getLoginType())) {
//            // support original username/password check
//            var orig = new DaoAuthenticationProvider();
//            return orig.authenticate(authentication);
            throw new BadCredentialsException("missing header parameter[login_type]");
        }

        return super.authenticate(authentication);
    }

    @Override
    protected UserDetails retrieveUser(
            String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        var details = (S2u2mAuthenticationDetails) token.getDetails();
        var service = this.getDetailService(details.getLoginType());
        if (ObjectUtils.isEmpty(service)) {
            throw new InternalAuthenticationServiceException(
                    MessageFormat.format("Missing DetailService for {0}", details.getLoginType()));
        }

        S2u2mAccountDetails accountDetails = service.loadAccount(username);
        if (ObjectUtils.isEmpty(accountDetails)) {
            throw new UsernameNotFoundException(
                    MessageFormat.format("no user[{0}] for {1}", username, details.getLoginType()));
        }
        return accountDetails;
    }

    private S2u2mAccountDetailsService getDetailService(LoginType type) {
        return new PhonePasswordAccountDetailsService();
    }

}
