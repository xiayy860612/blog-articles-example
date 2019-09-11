package com.s2u2m.examples.springsecurity.auth;

import com.s2u2m.examples.springsecurity.auth.constant.LoginType;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Getter
public class S2u2mAuthenticationDetails extends WebAuthenticationDetails {
    public static final String LOGIN_TYPE_HEADER_KEY = "login_type";

    private LoginType loginType;

    public S2u2mAuthenticationDetails(HttpServletRequest request) {
        super(request);
        var loginTypeParam = request.getHeader(LOGIN_TYPE_HEADER_KEY);
        this.loginType = StringUtils.isEmpty(loginTypeParam) ? null : LoginType.get(loginTypeParam);
    }
}
