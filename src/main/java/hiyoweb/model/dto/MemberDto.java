package hiyoweb.model.dto;

import hiyoweb.model.entity.BoardEntity;
import hiyoweb.model.entity.MemberEntity;
import hiyoweb.model.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter@Builder@ToString
public class MemberDto {//class start

    private int mno;    // 회원번호 pk
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;    // 등급

    // - dto를 엔티티로 변환하는 메소드
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mrol(this.mrol)
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

}//class end
