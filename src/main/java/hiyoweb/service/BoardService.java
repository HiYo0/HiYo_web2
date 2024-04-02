package hiyoweb.service;

import hiyoweb.model.dto.BoardDto;
import hiyoweb.model.dto.MemberDto;
import hiyoweb.model.entity.BoardEntity;
import hiyoweb.model.entity.BoardFileEntity;
import hiyoweb.model.entity.MemberEntity;
import hiyoweb.model.entity.ReplyEntity;
import hiyoweb.model.repository.BoardEntityRepository;
import hiyoweb.model.repository.BoardFileEntityRepository;
import hiyoweb.model.repository.MemberEntityRepository;
import hiyoweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {// class start

    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private MemberService memberService;
    @Autowired private FileService fileService;
    @Autowired private BoardFileEntityRepository boardFileEntityRepository;


    // 1. C
    @Transactional
    public boolean postBoard(BoardDto boardDto){
        MemberDto loginDto = memberService.doLoginInfo();
        if(loginDto == null)return false;

        // 1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity =
                memberEntityRepository.findById(loginDto.getMno());
        // 2. 찾은 엔티티가 존재하지 않으면 실패
        if(!optionalMemberEntity.isPresent())return false;


        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();

            // - 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save(boardDto.toEntity());
            // - FK 대입
        if(saveBoard.getBno() >=1 ){// 글쓰기 성공했으면
            saveBoard.setMemberEntity(memberEntity);
//            return true;
        }



        if(!boardDto.getUploadList().isEmpty()){// 등록된 파일 있으면 실행

            for (int i = 0; i < boardDto.getUploadList().size(); i++) {
                MultipartFile file = boardDto.getUploadList().get(i);
                String name = fileService.fileupload(file);
                System.out.println("name = " + name);
                // save 빈객체 생성
                BoardFileEntity saveBoardEntity = boardFileEntityRepository.save(BoardFileEntity.builder()
                                .bimg(name)
                                .build());
                saveBoardEntity.setBoardEntity(saveBoard);

            }
        }
        if(saveBoard.getBno() >=1 ){// 글쓰기 성공했으면
            return true;
        }

        return false;
    }


    // 2. R
    @Transactional
    public List<BoardDto> getBoard(){
        // =================== 1 ====================== //
//        // 1. 리포지토리를 이용한 모든 엔티티( 테이블에 메핑 하기전 엔티티 )를 호출
//        List<BoardEntity> result = boardEntityRepository.findAll();
//        // 2. Entity ----> Dto 변환한다
//        List<BoardDto> boardDtoList = new ArrayList<>();
//            // 1. 꺼내온 entity 를 순회한다
//        for (int i = 0; i < result.size(); i++) {
//                // 2. 하나씩 entity 꺼낸다.
//            BoardEntity boardEntity =result.get(i);
//                // 3. 해당 엔티티를 dto로 변환한다.
//            BoardDto boardDto = boardEntity.toDto();
//                // 4. 변환된 dto를 리스트에 담는다.
//            boardDtoList.add(boardDto);
//        }
//        System.out.println("boardDtoList = " + boardDtoList);
//
//        return boardDtoList;
        // =================== 1 END ====================== //
        // =================== 1 ====================== //
        return boardEntityRepository.findAll().stream().map((boardEntity)->{
            return boardEntity.toDto();
        }).collect(Collectors.toList());
        // =================== 2 END ====================== //
    }


    // 3. U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }


    // 4. D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById( 1 );
        return false;
    }


}// class end
