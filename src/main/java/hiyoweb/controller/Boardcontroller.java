package hiyoweb.controller;

import hiyoweb.model.dto.BoardDto;
import hiyoweb.model.dto.PageDto;
import hiyoweb.model.entity.BoardEntity;
import hiyoweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board")
@RestController     // @Controller + @ResponseBody( Content-Type: application/json ) : 데이터 주고 받는 REST 역할
public class Boardcontroller {//class start

    @Autowired
    private BoardService boardService;

    @PostMapping("/post.do")
    public boolean postBoard(BoardDto boardDto){
        System.out.println("boardWir  boardDto = " + boardDto);
        return boardService.postBoard(boardDto);
    }
    @GetMapping("/get.do")
    public PageDto getBoard(@RequestParam int page , @RequestParam int view){
        return boardService.getBoard(page,view);
    }
    @PutMapping("/put.do")
    public boolean putBoard(){return boardService.putBoard();}

    @DeleteMapping("/delete.do")
    // 1 삭제성공
    // 0 삭제실패
    // -1 다른사람꺼임 아이디 불일치
    public int deleteBoard(@RequestParam int bno){
        System.out.println("bno = " + bno);

        return boardService.deleteBoard(bno);
    }

}//class End
