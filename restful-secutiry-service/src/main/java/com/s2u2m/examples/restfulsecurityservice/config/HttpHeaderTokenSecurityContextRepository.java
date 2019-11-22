package com.s2u2m.examples.restfulsecurityservice.config;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpHeaderTokenSecurityContextRepository implements SecurityContextRepository {
    public static final String TOKEN_HEADER = "token";

    private final Map<String, SecurityContext> tokenSecurityContextMap = new LinkedHashMap<>();

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder holder) {
        String token = holder.getRequest().getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            return SecurityContextHolder.createEmptyContext();
        }

        if (!tokenSecurityContextMap.containsKey(token)) {
            return SecurityContextHolder.createEmptyContext();
        }

        return tokenSecurityContextMap.get(token);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String token = response.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            return;
        }

        if (tokenSecurityContextMap.containsKey(token)) {
            return;
        }

        tokenSecurityContextMap.put(token, context);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        return tokenSecurityContextMap.containsKey(token);
    }
}
