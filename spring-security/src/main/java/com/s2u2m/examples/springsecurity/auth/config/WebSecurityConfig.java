package com.s2u2m.examples.springsecurity.auth.config;

import com.s2u2m.examples.springsecurity.auth.S2u2mAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic()
                .authenticationDetailsSource(authenticationDetailsSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // config AuthenticationManager for this SecurityFilterChain
        builder.authenticationProvider(new S2u2mAuthenticationProvider());
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) {
//        // config global AuthenticationManager
//    }

    //    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        String finalPassword = "123456";
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//        return manager;
//    }
}
