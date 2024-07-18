package com.example.jobKoreaIt.config.auth;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PrincipalDetails principalDetails = new PrincipalDetails();

        Optional<User> userOptional =  userRepository.findById(username);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException(username);

        User user = userOptional.get();
        UserDto userDto = new UserDto();
        userDto.setUserid(user.getUserid());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        principalDetails.setUserDto(userDto);


        if(StringUtils.equals(userOptional.get().getRole(),"ROLE_SEEKER")){
            JobSeeker jobSeeker =  jobSeekerRepository.findByUser(user);
            JobSeekerDto jobSeekerDto = new JobSeekerDto();
            jobSeekerDto.setUsername(jobSeeker.getUsername());
            jobSeekerDto.setId(jobSeeker.getId());
            jobSeekerDto.setTel(jobSeeker.getTel());
            jobSeekerDto.setZipcode(jobSeeker.getZipcode());
            jobSeekerDto.setAddr1(jobSeeker.getAddr1());
            jobSeekerDto.setAddr2(jobSeeker.getAddr2());
            jobSeekerDto.setEmail(jobSeeker.getEmail());
            principalDetails.setJobSeekerDto(jobSeekerDto);

        }else if(StringUtils.equals(userOptional.get().getRole(),"ROLE_OFFER")){
            JobOffer jobOffer =  jobOfferRepository.findByUser(user);

            JobOfferDto jobOfferDto = new JobOfferDto();
            jobOfferDto.setCompanyName(jobOffer.getCompanyName());
            jobOfferDto.setId(jobOffer.getId());
            jobOfferDto.setZipcode(jobOffer.getZipcode());
            jobOfferDto.setCompanyAddr1(jobOffer.getCompanyAddr1());
            jobOfferDto.setCompanyAddr2(jobOffer.getCompanyAddr2());
            jobOfferDto.setCompanyEmail(jobOffer.getCompanyEmail());
            jobOfferDto.setCompanyIndustry(jobOffer.getCompanyIndustry());
            jobOfferDto.setCompanyNumber(jobOffer.getCompanyNumber());
            jobOfferDto.setCompanyPhone(jobOffer.getCompanyPhone());
            jobOfferDto.setCompanyexplanation(jobOffer.getCompanyexplanation());
            jobOfferDto.setCompanyName(jobOffer.getCompanyName());
            principalDetails.setJobOfferDto(jobOfferDto);

        }

        System.out.println("PrincipalDetailsService loadUserByUsername principalDetails : " + principalDetails);
        return principalDetails;
    }

}
