package hiyoweb.model.entity.샘플;

import jakarta.persistence.*;

@Entity
@Table(name = "survey") // 계량 엔티티
public class SurveyEntity {//class start

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;        // 식별번호

    // 작업번호 ( 작업계획 - FK , 작업식별 , 작업내용(?뭘만드는지 - 레시피 ) , 수량 )
    private int workplan; //

    // 담당자
    private String sname;



}//class end
