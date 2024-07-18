package com.example.jobKoreaIt.config.auth.jwt;


import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.properties.DBCONN;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {


    //Key 저장
    private final Key key;

    String url  = DBCONN.URL;
    String username = DBCONN.ID;
    String password  = DBCONN.PW;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

        public JwtTokenProvider() throws Exception {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            pstmt = conn.prepareStatement("select * from signature");
            rs =pstmt.executeQuery();

            if(rs.next())
            {

                byte [] keyByte =  rs.getBytes("signature");                    //DB로 서명Key꺼내옴
                this.key = Keys.hmacShaKeyFor(keyByte);                                    //this.key에 저장
//                .out.println("[JwtTokenProvider] Key : " + this.key );
            }
            else {
                byte[] keyBytes = KeyGenerator.getKeygen();     //난수키값 가져오기
                this.key = Keys.hmacShaKeyFor(keyBytes);        // 생성된 키를 사용하여 HMAC SHA(암호화알고리즘)알고리즘에 기반한 Key 객체 생성
                pstmt = conn.prepareStatement("insert into signature values(?,now())");

                pstmt.setBytes(1, keyBytes);
                pstmt.executeUpdate();
//                .out.println("[JwtTokenProvider] Constructor Key init: " + key);
            }


        }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = principalDetails.getUserDto();

        String accessToken = null;
        if(StringUtils.equals(authorities,"ROLE_SEEKER")){
            JobSeekerDto jobSeekerDto = principalDetails.getJobSeekerDto();
            // Access Token 생성
            Date accessTokenExpiresIn = new Date(now + 60*60*1000);    // 60*60 초후 만료
            accessToken = Jwts.builder()
                    .setSubject(userDto.getUserid())
                    .claim("userid",userDto.getUserid())             //정보저장
                    .claim("password",userDto.getPassword())                //정보저장
                    .claim("role",userDto.getRole())                      //정보저장

                    .claim("email",jobSeekerDto.getEmail())                      //정보저장
                    .claim("tel",jobSeekerDto.getTel())                      //정보저장
                    .claim("username",jobSeekerDto.getUsername())                      //정보저장
                    .claim("zipcode",jobSeekerDto.getZipcode())                      //정보저장
                    .claim("addr1",jobSeekerDto.getAddr1())                      //정보저장
                    .claim("addr2",jobSeekerDto.getAddr2())                      //정보저장


                    .claim("authorities", authorities)                             //정보저장
                    .claim("principal", authentication.getPrincipal())      //정보저장
                    .claim("credentials", authentication.getCredentials())  //정보저장
                    .claim("details", authentication.getDetails())          //정보저장

//                    .claim("provider", userDto.getProvider())               //정보저장
//                    .claim("providerId",userDto.getProviderId())             //정보저장
//                    .claim("accessToken", principalDetails.getAccessToken())//정보저장                      //정보저장

                    .setExpiration(accessTokenExpiresIn)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();


        }else if(StringUtils.equals(authorities,"ROLE_OFFER")){
            JobOfferDto jobOfferDto = principalDetails.getJobOfferDto();

            // Access Token 생성
            Date accessTokenExpiresIn = new Date(now + 60*5*1000);    // 60*5 초후 만료
            accessToken = Jwts.builder()
                    .setSubject(userDto.getUserid())
                    .claim("userid",userDto.getUserid())             //정보저장
                    .claim("password",userDto.getPassword())                //정보저장
                    .claim("role",userDto.getRole())                      //정보저장

                    .claim("companyName",jobOfferDto.getCompanyName())                      //정보저장
                    .claim("companyNumber",jobOfferDto.getCompanyNumber())                      //정보저장
                    .claim("zipcode",jobOfferDto.getZipcode())                      //정보저장
                    .claim("companyAddr1",jobOfferDto.getCompanyAddr1())                      //정보저장
                    .claim("companyAddr2",jobOfferDto.getCompanyAddr2())                      //정보저장

                    .claim("companyEmail",jobOfferDto.getCompanyEmail())                      //정보저장
                    .claim("companyPhone",jobOfferDto.getCompanyPhone())                      //정보저장
                    .claim("companyIndustry",jobOfferDto.getCompanyIndustry())                      //정보저장
                    .claim("companyexplanation",jobOfferDto.getCompanyexplanation())                      //정보저장

                    .claim("authorities", authorities)                             //정보저장
                    .claim("principal", authentication.getPrincipal())      //정보저장
                    .claim("credentials", authentication.getCredentials())  //정보저장
                    .claim("details", authentication.getDetails())          //정보저장

//                    .claim("provider", userDto.getProvider())               //정보저장
//                    .claim("providerId",userDto.getProviderId())             //정보저장
//                    .claim("accessToken", principalDetails.getAccessToken())//정보저장
                    .setExpiration(accessTokenExpiresIn)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }else {
            // Access Token 생성
            Date accessTokenExpiresIn = new Date(now + 60*5*1000);    // 60*5 초후 만료
            accessToken = Jwts.builder()
                    .setSubject(userDto.getUserid())
                    .claim("userid",userDto.getUserid())             //정보저장
                    .claim("password",userDto.getPassword())                //정보저장
                    .claim("role",userDto.getRole())                      //정보저장

                    .claim("authorities", authorities)                             //정보저장
                    .claim("principal", authentication.getPrincipal())      //정보저장
                    .claim("credentials", authentication.getCredentials())  //정보저장
                    .claim("details", authentication.getDetails())          //정보저장

//                    .claim("provider", userDto.getProvider())               //정보저장
//                    .claim("providerId",userDto.getProviderId())             //정보저장
//                    .claim("accessToken", principalDetails.getAccessToken())//정보저장
                    .setExpiration(accessTokenExpiresIn)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();

        }


        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 86400000))    //1일: 24 * 60 * 60 * 1000 = 86400000
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }



    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {

        PrincipalDetails principalDetails = new PrincipalDetails();

        // 토큰 복호화
        Claims claims = parseClaims(accessToken);



        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("authorities").toString().split(","))
                        .map(auth -> new SimpleGrantedAuthority(auth))
                        .collect(Collectors.toList());

        if (authorities == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        String userid = (String)claims.get("userid");
        String password = (String)claims.get("password");
        String role = (String)claims.get("role");
        UserDto userDto = new UserDto();
        userDto.setUserid(userid);
        userDto.setRole(role);
        userDto.setPassword(password);
        principalDetails.setUserDto(userDto);



        if(StringUtils.equals(role,"ROLE_SEEKER")){

            String email = (String)claims.get("email");
            String tel = (String)claims.get("tel");
            String username = (String)claims.get("username");
            String zipcode = (String)claims.get("zipcode");
            String addr1 = (String)claims.get("addr1");
            String addr2 = (String)claims.get("addr2");

            JobSeekerDto jobSeekerDto = new JobSeekerDto();
            jobSeekerDto.setEmail(email);
            jobSeekerDto.setTel(tel);
            jobSeekerDto.setUsername(username);
            jobSeekerDto.setZipcode(zipcode);
            jobSeekerDto.setAddr1(addr1);
            jobSeekerDto.setAddr2(addr2);

            principalDetails.setJobSeekerDto(jobSeekerDto);


        }else if(StringUtils.equals(role,"ROLE_OFFER")){
            String id = (String)claims.get("id");

            String companyName = (String)claims.get("companyName");
            String companyNumber = (String)claims.get("companyNumber");
            String zipcode = (String)claims.get("zipcode");
            String companyAddr1 = (String)claims.get("companyAddr1");
            String companyAddr2 = (String)claims.get("companyAddr2");
            String companyEmail = (String)claims.get("companyEmail");
            String companyPhone = (String)claims.get("companyPhone");
            String companyIndustry = (String)claims.get("companyIndustry");
            String companyexplanation = (String)claims.get("companyexplanation");


            JobOfferDto jobOfferDto = new JobOfferDto();
            jobOfferDto.setCompanyName(companyName);
            jobOfferDto.setCompanyNumber(companyNumber);
            jobOfferDto.setZipcode(zipcode);
            jobOfferDto.setCompanyAddr1(companyAddr1);
            jobOfferDto.setCompanyAddr2(companyAddr2);
            jobOfferDto.setCompanyEmail(companyEmail);
            jobOfferDto.setCompanyPhone(companyPhone);
            jobOfferDto.setCompanyIndustry(companyIndustry);
            jobOfferDto.setCompanyexplanation(companyexplanation);

            principalDetails.setJobOfferDto(jobOfferDto);
//            System.out.println("principalDetails !!!!!: " + principalDetails);
        }


//        System.out.println("JWTTOKENPROVIER GETAUTHENTICATION PRINCIPALDETAILS : " + principalDetails);


        LinkedHashMap principal = (LinkedHashMap)claims.get("principal");
        String credentials = (String)claims.get("credentials");
        LinkedHashMap details = (LinkedHashMap)claims.get("details");


        //OAUTH2
        //String oauthAccessToken = (String)claims.get("accessToken");
        //principalDetails.setAccessToken(oauthAccessToken);   //Oauth AccessToken
        //principalDetails.setAccessToken();//OAUTH2

        //JWT + NO REMEMBERME
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(principalDetails, claims.get("credentials"), authorities);
        usernamePasswordAuthenticationToken.setDetails(details);

        //System.out.println("JWT TOKEN PROVIDER getAuthentication : " + usernamePasswordAuthenticationToken);
        return usernamePasswordAuthenticationToken;

    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        }
        catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            return false;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        } catch(Exception etc){
            log.info("기타예외");
            return false;
        }
        return false;
    }
}