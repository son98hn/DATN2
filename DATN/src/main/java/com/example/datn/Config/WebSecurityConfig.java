package com.example.datn.Config;

import com.example.datn.service.IUserService;
import com.example.datn.service.impl.CustomOAuth2UserService;
import com.example.datn.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oauth2UserService;
    private final IUserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthenticationSuccessHandler authenticationSuccessHandler, CustomOAuth2UserService oauth2UserService,@Lazy IUserService userService) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.oauth2UserService = oauth2UserService;
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/trang-chu", "/login/**", "/register", "/template/**", "/nhom-bai-viet/**", "/bai-viet/**", "/public/**", "/forgotPassword**", "/resetPassword**").permitAll()
                .antMatchers("/admin**").hasAnyAuthority("admin")
                .antMatchers("/profile**").authenticated()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login**").permitAll()
                .successHandler(authenticationSuccessHandler)
                .failureUrl("/login?message=fail")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().formLogin().loginPage("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/403").and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        userService.processOAuthPostLogin(oauthUser.getName(), oauthUser.getEmail());
                        response.sendRedirect("/trang-chu");
                    }
                });
//        http.authorizeRequests().and().logout().logoutUrl("/trang-chu").logoutSuccessUrl("/");

//                .and().logout().logoutUrl("/trang-chu").logoutSuccessUrl("/trang-chu");
        // Cấu hình Remember Me.
//        http.authorizeRequests().and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

}