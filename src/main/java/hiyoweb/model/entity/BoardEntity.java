package hiyoweb.model.entity;

import hiyoweb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table( name = "board")
@AllArgsConstructor@NoArgsConstructor@Setter@Getter@ToString@Builder
public class BoardEntity extends BaseTime{//class start

    @Id // primary key == not null
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto_increment
    private int bno;    // 게시물 번호

    @Column( columnDefinition = "longtext")
    private String bcontent;

    @Column
    @ColumnDefault("0")
    private int bview;

    // FK필드
    @JoinColumn(name = "mno_fk" )//fk 필드명
    @ManyToOne // 해당 필드를 참조
    private MemberEntity memberEntity;

    // 양방향 : 댓글fk
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용.
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // 양방향 설정
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    // 게시물 출력
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno_fk(memberEntity.getMno())
                .memail(memberEntity.getMemail())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .bimglist(
                        this.boardFileEntityList.stream().map(
                                (imgEntity)->{ return imgEntity.getBimg();}
                        ).collect(Collectors.toList())
                )
                .build();
    }
    



}//class end
/*
    create table BoardEntitiy{
        bno int
        btitle varcghar(255)
    }
*/