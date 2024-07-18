package com.example.jobKoreaIt.config.auth;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {

    private UserDto userDto;
    private JobSeekerDto jobSeekerDto;
    private JobOfferDto jobOfferDto;


    //OAUTH2---------------------------------------
    private String accessToken;
    private Map<String,Object> attributes;




    //return 수정
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    @Override
    public String getName() {
        return userDto.getUserid();
    }
    //OAUTH2---------------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDto.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        String role = userDto.getRole();
        if(StringUtils.equals(role,"ROLE_SEEKER")){
            return jobSeekerDto.getUsername();
        } else if (StringUtils.equals(role,"ROLE_OFFER")) {
            return jobOfferDto.getCompanyName();
        }
//        return null;
        return userDto.getUserid();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //추가
    public PrincipalDetails(UserDto userDto, Object o, Object o1, String tokenValue, Map<String, Object> attributes) {
    }

}
