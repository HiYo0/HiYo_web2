package hiyoweb.service;

import hiyoweb.model.dto.BoardDto;
import hiyoweb.model.dto.MemberDto;
import hiyoweb.model.entity.BoardEntity;
import hiyoweb.model.entity.MemberEntity;
import hiyoweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {//class start
    @Autowired MemberEntityRepository memberEntityRepository;


    // 1. 회원가입
    public int doSignUpPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        // = 중복검사====================================
        // 1. 리포지토리를 통한 모든 회원엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for (int i = 0; i < memberEntityList.size(); i++) {
            // 만약 등록된 리스트중에 지금 입력받은 값이 있으면
            if(memberEntityList.get(i).getMemail().equals(memberDto.getMemail())){
                return -1;
            }
        }
        // = 중복검사 END ====================================

        // -DAO 아닌 엔티티 이용한 레코드 저장하는 방법
            // 1. 엔티티를 만든다
        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity() ); //insert

        // 만약 저장성공시 true 반환 => 성공하면 PK값이 자동생성되기때문에 0이면 실패 1이상이면 성공
        if(savedEntity.getMno()>0)return 1;
        return 0;
    }// 회워가입 END

    // * 로그인 했다는 증거/기록
    @Autowired private HttpServletRequest request; // 세션이용

    // 2. 로그인 (세션저장)
    public boolean dologinPost( MemberDto memberDto ){

//        // 1.
//        MemberEntity result1 =memberEntityRepository.findByMemailAndMpassword(memberDto.getMemail(),memberDto.getMpassword());
//        System.out.println("result1 = " + result1);
//
//        // 2.
//        boolean result2 = memberEntityRepository.existsByMemailAndMpassword(memberDto.getMemail(),memberDto.getMpassword());
//        System.out.println("result2 = " + result2);

        // 3.
        MemberEntity result3 =memberEntityRepository.findByLoginSQL(memberDto.getMemail(),memberDto.getMpassword());
        System.out.println("result3 = " + result3);

        if(result3 == null){return false;}

        // 세션부여
        request.getSession().setAttribute("loginInfo",result3.todto()); // 회원번호(1) , 시큐리티(권한)

        return true;

    }// function end
    
    // 3. 로그아웃 (세션 삭제)
    public boolean dologoutGet( MemberDto memberDto ){
        // 세션 지우기
        request.getSession().setAttribute("loginInfo",null);
        if(request.getSession().getAttribute("loginInfo")==null){
            return true;
        }
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

    // 5. 아이디/이메일 중복검사
    public int getFindMemail(String memail){

        // 1. 모든 엔티티에서 해당 필드의 값을 찾는다.
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for (int i = 0; i < memberEntityList.size(); i++) {
            MemberEntity memberEntity = memberEntityList.get(i);
            if (memberEntity.getMemail().equals(memail)){
                System.out.println("memberEntity = " + memberEntity);
                return 1;
            }
        }

//        // 2. 리포지토리 추상메소드 이용하는 방법
//        memberEntityRepository.findByMemail( memail );
//
//        // 3. 특정 필드의 조건으로 존재여부 검색
//        memberEntityRepository.existsByMemail( memail );
//
//        // 4. 직접 native SQL 지원
//        memberEntityRepository.findByMemailSQL( memail );

        return 0;
    }

    // 6. (로그인) 내가쓴글
    public List<Map<Object,Object>> findByMyBoardList(){


        // 1. 세션에서 로그인된 회원번호 찾는다.
        MemberDto loginDto = doLoginInfo();
        if(loginDto==null) return null;

        // ============ 1. 양방향일때 ============ //
        /*
            // 1. 로그인된 회원번호를 이용한 엔티티찾기
        Optional<MemberEntity> optionalMemberEntity =
            memberEntityRepository.findById( loginDto.getMno());
            // 2. 만약에 엔티티가 존재하면
        if(optionalMemberEntity.isPresent()){ // findById의 결과에 엔티티 존재하면
            // 3. Optional 에서 엔티티 꺼내기
            MemberEntity memberEntity = optionalMemberEntity.get();
            // 4. 내가 쓴글
            List<BoardEntity> result1 = memberEntity.getBoardEntityList();

            // 내가쓴글 엔티티리스트를 -> 내가쓴글 DTO리스트로 변환
            List<BoardDto> boardDtoList = new ArrayList<>();
            result1.forEach( (entity)->{
                boardDtoList.add(entity.toDto());
            });
            return boardDtoList;
        }else {return null;}
        */
        // ============ 2. 단방향일때 ============ //
        List<Map<Object , Object>> result2 =
        memberEntityRepository.findByMyBoardSQL( loginDto.getMno());

        return result2;

    }


}//class end
/*
    Optional 클래스
        - 해당 객체가 null 일수도 있고 아닐수 있다.
        - 검색결과가 없을경우 null 반환될때 패키징
        - 메소드
* */
