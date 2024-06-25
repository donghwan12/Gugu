package com.example.jobKoreaIt.config.auth.jwt;


import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * JWT를 이용한 인증
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private  UserRepository userRepository;
    private  JobSeekerRepository jobSeekerRepository;
    private  JobOfferRepository jobOfferRepository;
    private  JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(
            UserRepository userRepository,
            JobSeekerRepository jobSeekerRepository,
            JobOfferRepository jobOfferRepository,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException, IOException {
        String token = null;
        try{
            if(token==null) {
                // cookie 에서 JWT token을 가져옵니다.
                token = Arrays.stream(request.getCookies())
                        .filter(c -> c.getName().equals(JwtProperties.COOKIE_NAME)).findFirst()
                        .map(c -> c.getValue())
                        .orElse(null);
            }
        } catch (Exception ignored) {
            //일반적으로 접근하는 요청 URI에 대한 쿠키 예외는 무시한다..
        }

        if (token != null) {
            try {
                if(jwtTokenProvider.validateToken(token)) {

                    Authentication authentication = getUsernamePasswordAuthenticationToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{

                }
            } catch (ExpiredJwtException e)     //토큰만료시 예외처리(쿠키 제거)
            {
                //System.out.println("[JWTAUTHORIZATIONFILTER] : ...ExpiredJwtException ...."+e.getMessage());
                //토큰 만료시 처리(Refresh-token으로 갱신처리는 안함-쿠키에서 제거)
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }catch(Exception e2){

            }
        }
        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
     * User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        Optional<User> user = userRepository.findById(authentication.getName()); // 유저를 유저명으로 찾습니다.
        //System.out.println("JwtAuthorizationFilter.getUsernamePasswordAuthenticationToken...authenticationToken : " +authentication );
        if(user!=null)
        {
            return authentication;
        }
        return null; // 유저가 없으면 NULL
    }

}