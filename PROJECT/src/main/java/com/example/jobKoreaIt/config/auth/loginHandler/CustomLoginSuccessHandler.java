package com.example.jobKoreaIt.config.auth.loginHandler;



import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import com.example.jobKoreaIt.config.auth.jwt.JwtTokenProvider;
import com.example.jobKoreaIt.config.auth.jwt.TokenInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler  {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("[CustomLoginSuccessHandler] onAuthenticationSuccess() authentication " + authentication);

        //--------------------------------------
        //JWT ADD
        //--------------------------------------
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        //추가
        PrincipalDetails principalDetails;
        if (authentication.getPrincipal() instanceof PrincipalDetails) {
            principalDetails = (PrincipalDetails) authentication.getPrincipal();
        } else {
            throw new ClassCastException("Authentication principal is not of type PrincipalDetails");
        }

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        // 쿠키 생성
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, tokenInfo.getAccessToken());
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); // 쿠키의 만료시간 설정
        cookie.setPath("/");
        response.addCookie(cookie);
        //--------------------------------------


        // 카카오API 로그인
        System.out.println("Login with kakao ! " + authentication.getName());
        response.sendRedirect("/");
    }


}

