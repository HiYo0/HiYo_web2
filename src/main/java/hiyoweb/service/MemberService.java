package hiyoweb.service;

import hiyoweb.model.dto.MemberDto;
import hiyoweb.model.entity.MemberEntity;
import hiyoweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MemberService {//class start
    @Autowired MemberEntityRepository memberEntityRepository;


    // 1. 회원가입
    public boolean doSignUpPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        // -DAO 아닌 엔티티 이용한 레코드 저장하는 방법
            // 1. 엔티티를 만든다
        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity() ); //insert

        // 만약 저장성공시 true 반환 => 성공하면 PK값이 자동생성되기때문에 0이면 실패 1이상이면 성공
        if(savedEntity.getMno()>0)return true;
        return false;
    }

    // * 로그인 했다는 증거/기록
    @Autowired private HttpServletRequest request; // 세션이용

    // 2. 로그인 (세션저장)
    public boolean dologinPost( MemberDto memberDto ){

        // 1. 리포지토리를 통한 모든 회원엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();

        // 2. dto와 동일한 아이디 / 패스워드 찾는다.

        for (int i = 0; i < memberEntityList.size(); i++) {
            MemberEntity m = memberEntityList.get(i);
            // 3. 만약에 아이디가 동일하면 (엔티티와 dto)
            if(m.getMemail().equals(memberDto.getMemail())){
                // 4. 만약에 비밀번호가 동일하면
                if(m.getMpassword().equals(memberDto.getMpassword())){
                    // 5. 세션 저장
                    request.getSession().setAttribute("loginInfo", memberDto);
                    return true;
                }
            }
        }
        return false;
    }
    
    // 3. 로그아웃 (세션 삭제)
    public boolean dologoutGet( MemberDto memberDto ){
        // 세션 지우기
        request.getSession().setAttribute("loginInfo",null);
        return false;
    }

    // 4. 현재 로그인 회원정보 호출 ( 세션 값 반환/호출 )
    public MemberDto doLoginInfo(){
        // 세션호출
        Object object = request.getSession().getAttribute("loginInfo");
        System.out.println("object = " + object);
        if(object!=null){ // 값이있으면
            return (MemberDto)object; // 강제 형변환시켜서 리턴
        }

        return null;
    }


}//class end
