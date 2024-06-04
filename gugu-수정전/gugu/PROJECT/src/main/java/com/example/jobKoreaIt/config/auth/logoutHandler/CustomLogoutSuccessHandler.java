package com.example.jobKoreaIt.config.auth.logoutHandler;


import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Arrays;


public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)  {
        System.out.println("[CustomLogoutSuccessHandler] onLogoutSuccess()");

    }
}
