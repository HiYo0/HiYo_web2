package hiyoweb.model.entity;

import hiyoweb.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@AllArgsConstructor@NoArgsConstructor@Setter@Getter@Builder@ToString
public class MemberEntity extends BaseTime {//class start

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;    // 회원번호 pk


    @Column( length = 50)
    private String memail;

    @Column( length = 30)
    private String mpassword;

    @Column( length = 20)
    private String mname;

    @Column( name = "mrol" )
    @ColumnDefault("'user'")
    private String mrol;    // 등급

    // 양방향 : 게시물fk      @OneToMany( mappedBy = "fk필드명")
    @OneToMany( mappedBy = "memberEntity" )
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용.
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // 양방향 : 댓글fk
    @OneToMany( mappedBy = "memberEntity" )
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용.
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // - 엔티티를 dto로 변화하는 메소드
    public MemberDto todto(){
        return MemberDto.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mrol(this.mrol)
                .build();
    }
}//class end
