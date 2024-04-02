package hiyoweb.model.dto;

import hiyoweb.model.entity.BaseTime;
import hiyoweb.model.entity.BoardEntity;
import hiyoweb.model.entity.MemberEntity;
import hiyoweb.model.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor@NoArgsConstructor
@Setter@Getter@ToString
@SuperBuilder
public class BoardDto extends BaseTimeDto {

    private int bno;    // 게시물 번호
    private String bcontent;
    private int bview;
    private int mno_fk;     // (MemberEntity)회원 번호
    private String memail;  // (MemberEntity)회원 이메일


    // 1. 출력용 게시물 이미지 필드 ( 파일이름만 여러개 출력하면 되니까)
    private List<String> bimglist = new ArrayList<>();

    // 2. 등록용 게시물 이미지 필드
    private List<MultipartFile> uploadList = new ArrayList<>();

    // 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }
}
