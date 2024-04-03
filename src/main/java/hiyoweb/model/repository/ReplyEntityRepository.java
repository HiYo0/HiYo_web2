package hiyoweb.model.repository;

import hiyoweb.model.entity.BoardFileEntity;
import hiyoweb.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyEntityRepository extends JpaRepository<ReplyEntity , Integer> {

    @Query(value = "select * from reply where bno_fk = :bno",nativeQuery = true)
    List<ReplyEntity> findByBnoSql(int bno);

}
