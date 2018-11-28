package com.reomor.configuration;

import com.reomor.core.domain.User;
import com.reomor.impl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    // @Lazy prevents from cycle dependency
    public Security(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);
        auth.authenticationProvider(new AuthenticationProvider() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String email = (String) authentication.getPrincipal();
                String providedPassword = (String) authentication.getCredentials();
                User authenticatedUser = userService.findAndAuthenticate(email, providedPassword);
                if (authenticatedUser == null) {
                    throw new BadCredentialsException("Username/Password does not match for " + email);
                }
                if (!authenticatedUser.isEnabled()) {
                    throw new BadCredentialsException("User registration is not confirmed by link in email" + email);
                }
                return new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> aClass) {
                return true;
            }
        });
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers( "/webjars/**", "/js/**", "/css/**", "/images/**/*", "/h2-console");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and().csrf().disable()
                .anonymous().authorities("ROLE_ANONYMOUS")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/").failureForwardUrl("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/registrationConfirm").permitAll()
                .antMatchers("/files/**/*").permitAll()
                .antMatchers(HttpMethod.GET, "/channel").permitAll()
                .antMatchers(HttpMethod.POST, "/channel").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/image").permitAll()
                .antMatchers(HttpMethod.POST, "/channel/thread").permitAll()
                .antMatchers(HttpMethod.GET, "/user").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/user").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
    }
}
