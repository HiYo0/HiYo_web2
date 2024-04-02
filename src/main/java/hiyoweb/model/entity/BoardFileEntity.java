package hiyoweb.model.entity;

import hiyoweb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "boardfile")
@AllArgsConstructor@NoArgsConstructor@Setter@Getter@ToString@Builder
public class BoardFileEntity extends BaseTime{//class start

    @Id // primary key == not null
    private String bimg;

    // FK필드
    @JoinColumn(name = "bno_fk" )//fk 필드명
    @ManyToOne // 해당 필드를 참조
    private BoardEntity boardEntity;


}//class end
