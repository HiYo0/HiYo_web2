package hiyoweb.model.repository;

import hiyoweb.model.entity.BoardEntity;
import hiyoweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 매핑된 테이블의 엔티티/레코드 들을 조작/관리 하는 리모콘/인터페이스 역활
//public interface BoardEntityRepository extends JpaRepository< 조작할테이블명 , PK > {
public interface BoardEntityRepository
        extends JpaRepository< BoardEntity, Integer > {// interface start

    
    
}// interface END

