package com.jicg.jman.config;

import com.jicg.jman.config.security.*;
import com.jicg.jman.config.security.verify.IVerifyCodeFailureHandle;
import com.jicg.jman.config.security.verify.IVerifyCodeService;
import com.jicg.jman.config.security.verify.ImageVerifyService;
import com.jicg.jman.config.security.verify.VerifyCodeFailureHandleImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.Filter;
import javax.sql.DataSource;


/**
 * @author jicg on 2020/4/18
 */
@Slf4j
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/", "index", "/login", "/login-error", "/401", "/css/**", "/js/**", "/lib/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/login_p")
                .loginProcessingUrl("/api/login")
//                .usernameParameter("username").passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/")
                .permitAll()
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .tokenValiditySeconds(7 * 24 * 60 * 60)
        ;
        http.headers().frameOptions().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers(
                "/res/**",
                "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**",
                "/login.html", "/verify","/error"
        );
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动创建数据库表，使用一次后注释掉，不然会报错
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public IVerifyCodeService verifyCodeService() {
        return new ImageVerifyService();
    }

    @Bean
    @ConditionalOnMissingBean
    public IVerifyCodeFailureHandle verifyCodeFailureHandle() {
        return new VerifyCodeFailureHandleImpl();
    }

    @Bean
    public ValidateCodeFilter validateCodeFilter() {
        return new ValidateCodeFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            @Override
            public String encode(CharSequence charSequence) {
                return bCryptPasswordEncoder.encode(charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return bCryptPasswordEncoder.matches(charSequence, s);
            }
        };
    }

//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)
//            throws Exception {
//        authenticationManagerBuilder
//                .authenticationProvider(daoAuthenticationProvider());
//    }
}
