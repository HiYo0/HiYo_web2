package hiyoweb.model.dto;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString
public class BaseTimeDto {//class start

    public LocalDateTime cdate;
    public LocalDateTime udate;

}// class end
/*
    상속 : 여러곳에서 공통적인 멤버들
*/
