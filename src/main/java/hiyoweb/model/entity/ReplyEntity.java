package hiyoweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "reply")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @ToString @Builder

public class ReplyEntity {//class start

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    private String rcontent;

    // FK필드
    @JoinColumn(name = "bno_fk")
    @ManyToOne
    private BoardEntity boardEntity;

    // FK 필드
    @JoinColumn(name = "mno_fk")
    @ManyToOne
    private MemberEntity memberEntity;

}// class end
