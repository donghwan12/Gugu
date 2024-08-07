package com.example.jobKoreaIt.config;


import com.example.jobKoreaIt.config.auth.PrincipalDetailsOAuth2Service;
import com.example.jobKoreaIt.config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.jobKoreaIt.config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.jobKoreaIt.config.auth.jwt.JwtAuthorizationFilter;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import com.example.jobKoreaIt.config.auth.jwt.JwtTokenProvider;
import com.example.jobKoreaIt.config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.jobKoreaIt.config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.jobKoreaIt.config.auth.logoutHandler.CustomLogoutHandler;
import com.example.jobKoreaIt.config.auth.logoutHandler.CustomLogoutSuccessHandler;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private HikariDataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    //추가
    @Autowired
    private PrincipalDetailsOAuth2Service principalDetailsOAuth2Service;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {

        //CSRF 비활성화
        http.csrf(
                (config)->{ config.disable(); }
        );
        //요청 URL별 접근 제한
        http.authorizeHttpRequests(
                authorize->{
                    authorize.requestMatchers("/js/**","/css/**","/images/**","/templates","/assets/**").permitAll();
                    authorize.requestMatchers("/","/login","/user/join","/seeker/join","/offer/join").permitAll();
                    authorize.requestMatchers("/upload/**").hasAnyRole("SEEKER","OFFER");
                    //SEEKER/OFFER
//                    authorize.requestMatchers("/seeker/**").hasAnyRole("SEEKER"); //임시 모든 URL 허용
//                    authorize.requestMatchers("/offer/**").hasAnyRole("OFFER"); //임시 모든 URL 허용
                    authorize.requestMatchers("/**").permitAll();
                    //COMMUNITY
                    authorize.requestMatchers("/community/**").permitAll(); //임시 모든 URL 허용
                    authorize.anyRequest().authenticated();
                }
        );
        //로그인
        http.formLogin(login->{
            login.permitAll();
            login.usernameParameter("userid");
            login.successHandler(customLoginSuccessHandler());
            login.failureHandler(new CustomAuthenticationFailureHandler());

        });

        //로그아웃
        http.logout(
                (logout)->{
                    logout.permitAll();
                    logout.logoutUrl("/logout");
                    logout.addLogoutHandler(customLogoutHandler());
                    logout.logoutSuccessHandler( customLogoutSuccessHandler() );
                    //JWT Added
                    logout.deleteCookies("JSESSIONID", JwtProperties.COOKIE_NAME);
                    logout.invalidateHttpSession(true);
                }
        );


        //예외처리
        http.exceptionHandling(
                ex->{
                    ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
                    ex.accessDeniedHandler(new CustomAccessDeniedHandler());
                }
        );
        //SESSION INVALIDATE..
        http.sessionManagement(
                httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
        );
        //JWT ADDED
        http.addFilterBefore(
                new JwtAuthorizationFilter(userRepository,jobSeekerRepository,jobOfferRepository,jwtTokenProvider),
                BasicAuthenticationFilter.class
        );

        //RememberMe
        http.rememberMe(
                rm->{
                    rm.key("rememberMeKey");
                    rm.rememberMeParameter("remember-me");
                    rm.alwaysRemember(false);
                    rm.tokenValiditySeconds(3600);  //60*60
                    rm.tokenRepository(tokenRepository());
                }
        );

//        //Oauth2
//        http.oauth2Login(
//                oauth2->{
//                    oauth2.loginPage("/user/login");
//                    oauth2.successHandler(oauth2JwtLoginSuccessHandler());
//                }
//        );

        // 추가
        http.oauth2Login(oauth2 -> {
            oauth2.loginPage("/user/login");
            oauth2.userInfoEndpoint().userService(principalDetailsOAuth2Service);
            oauth2.successHandler(customLoginSuccessHandler());
            oauth2.failureHandler(new CustomAuthenticationFailureHandler());
        });

        return http.build();


    }

    //REMEMBER ME 처리 BEAN
    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        //repo.setCreateTableOnStartup(true);
        repo.setDataSource(dataSource);
        return repo;
    }
    //CUSTOMLOGOUTSUCCESS BEAN
    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }
    //CUSTOMLOGOUTHANDLER BEAN
    @Bean
    public CustomLogoutHandler customLogoutHandler(){
        return new CustomLogoutHandler();
    }

    //CUSTOMLOGINSUCCESSHANDLER BEAN

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    //Oauth2JwtLoginSuccessHandler BEAN


    // BCryptPasswordEncoder Bean 등록 - 패스워드 검증에 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
