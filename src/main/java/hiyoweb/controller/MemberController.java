package hiyoweb.controller;

import hiyoweb.model.dto.MemberDto;
import hiyoweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
//@CrossOrigin("http://localhost:3000")// 요청 근원지를 교차 허용
public class MemberController {//class start

    @Autowired private MemberService memberService;

    // 1. 회원가입
    @PostMapping("/signup/post.do")
    public boolean doSignUpPost(@RequestBody MemberDto memberDto){
        System.out.println("MemberController.doSignUpPost");
        System.out.println("memberDto = " + memberDto);

        return memberService.doSignUpPost(memberDto);

    }

    // 2. 로그인
    @PostMapping("/login/post.do")
    public boolean dologinPost( MemberDto memberDto ){
        System.out.println("로그인 memberDto = " + memberDto);

        return memberService.dologinPost(memberDto);
    }

    // 3. 로그아웃
    @GetMapping("/logout/get.do")
    public boolean dologoutGet( MemberDto memberDto ){
        return memberService.dologoutGet(memberDto);
    }

    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }
}//class end
