package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.dto.ReplyDto;
import com.example.jobKoreaIt.domain.common.entity.Community;
import com.example.jobKoreaIt.domain.common.entity.Reply;
import com.example.jobKoreaIt.domain.common.repository.CommunityRepository;
import com.example.jobKoreaIt.domain.common.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReplyServiceImpl {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private CommunityRepository communityRepository;


    @Transactional(rollbackFor = Exception.class)
    public boolean addReply(Long cno , String content){
        Reply reply = new Reply();
        reply.setRno(null);
        Optional<Community> communityOptional =  communityRepository.findById(cno);
        if(communityOptional.isEmpty())
            return false;
        reply.setCno(communityOptional.get());
        reply.setRef(0L);
        reply.setDepth(0L);
        reply.setLikecount(0L);
        reply.setUsername(null);
        reply.setContent(content);
        reply.setRegdate(LocalDateTime.now());

        replyRepository.save(reply);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Reply> getAllReplyByCommunityNo(Long communityNo) {
        Optional<Community> communityOptional =  communityRepository.findById(communityNo);
        if(communityOptional.isEmpty())
            return null;
        return  replyRepository.GetReplyByCommunityNoDesc(communityOptional.get());
    }
    @Transactional(rollbackFor = Exception.class)
    public Long  getCountReplyByCommunityNo(Long communityNo) {
        Optional<Community> communityOptional =  communityRepository.findById(communityNo);
        if(communityOptional.isEmpty())
            return null;
       return  replyRepository.GetReplyCountByCommunityNoDesc(communityOptional.get());
    }


}
