package hiyoweb.service;

import hiyoweb.model.dto.MemberDto;
import hiyoweb.model.entity.MemberEntity;
import hiyoweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MemberService {//class start
    @Autowired MemberEntityRepository memberEntityRepository;

    public boolean doSignUpPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        // -DAO 아닌 엔티티 이용한 레코드 저장하는 방법
            // 1. 엔티티를 만든다
        memberEntityRepository.save(memberDto.toEntity() ); //insert

        return false;
    }

}//class end
