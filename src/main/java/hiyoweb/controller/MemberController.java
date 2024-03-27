package hiyoweb.controller;

import hiyoweb.model.dto.MemberDto;
import hiyoweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000")// 요청 근원지를 교차 허용
public class MemberController {//class start

    @Autowired private MemberService memberService;

    @PostMapping("/signup/post.do")
    public boolean doSignUpPost(@RequestBody MemberDto memberDto){
        System.out.println("MemberController.doSignUpPost");
        System.out.println("memberDto = " + memberDto);

        return memberService.doSignUpPost(memberDto);

    }
}//class end
