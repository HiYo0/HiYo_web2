package hiyoweb.service;

import hiyoweb.model.dto.BoardDto;
import hiyoweb.model.dto.MemberDto;
import hiyoweb.model.dto.PageDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
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
                if(name!=null) {
                    System.out.println("name = " + name);
                    // save 빈객체 생성
                    BoardFileEntity saveBoardEntity = boardFileEntityRepository.save(BoardFileEntity.builder()
                            .bimg(name)
                            .build());
                    saveBoardEntity.setBoardEntity(saveBoard);
                }
            }
        }
        if(saveBoard.getBno() >=1 ){// 글쓰기 성공했으면
            return true;
        }

        return false;
    }


    // 2. R
    @Transactional
    public PageDto getBoard(int page , int view){
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
        // 1. pageable 인터페이스 이용한 페이징처리
            // pageable 은 page 순서 0부터 시작하기 때문에 page가 1페이지일때 0페이지 변환하기 위해 -1
        Pageable pageable = PageRequest.of(page-1,view);

            // 1. 페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntitiPage = boardEntityRepository.findAll(pageable);
            // -- List 아닌 page 타입일때 List 동일한 메소드 사용하고 추가 기능
        System.out.println("boardEntitiPage.getTotalPages() = " + boardEntitiPage.getTotalPages());
        int count = boardEntitiPage.getTotalPages();
        System.out.println("boardEntitiPage.getTotalElements() = " + boardEntitiPage.getTotalElements());
            // 2. 엔티티를 DTO 변환
        List<Object> data = boardEntitiPage.stream().map((boardEntity)->{
                    return boardEntity.toDto();
                }).collect(Collectors.toList());

        // 2. 페이지DTO 반환 값 구성
            // 1.
        PageDto pageDto = PageDto.builder()
                .data(data) // 페이징 처리된 레코드들을 대입
                .page(page)
                .count(count)
                .build();
        return pageDto;
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
    public int deleteBoard(int bno){

        MemberDto loginDto = memberService.doLoginInfo();
        if(loginDto == null)return -1;

        // 1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity =
                memberEntityRepository.findById(loginDto.getMno());
        // 2. 찾은 엔티티가 존재하지 않으면 실패
        if(!optionalMemberEntity.isPresent())return -1;

        // 전달받은 식별번호로 보드 entity 가져오기
        Optional<BoardEntity> board =boardEntityRepository.findById(bno);
        if(board.get().getMemberEntity()==optionalMemberEntity.get()){
            // 보드에있는 회원과 로그인한 회원이 같으면 삭제 실행

                // bno로 등록된 이미지 파일 삭제
            List<BoardFileEntity> boardEntityList =boardFileEntityRepository.findByBnoSql(bno);
            for (int i = 0; i < boardEntityList.size(); i++) {
                boardFileEntityRepository.deleteById(boardEntityList.get(i).getBimg());
            }
            List<ReplyEntity> replyEntityList = replyEntityRepository.findByBnoSql(bno);
            for (int i = 0; i < replyEntityList.size(); i++) {
                replyEntityRepository.deleteById(replyEntityList.get(i).getRno());

            }

            boardEntityRepository.deleteById( bno );
            return 1;
        }else {return -1;}
    }


}// class end
