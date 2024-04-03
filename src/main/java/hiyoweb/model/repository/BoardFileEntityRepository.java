package hiyoweb.model.repository;

import hiyoweb.model.entity.BoardEntity;
import hiyoweb.model.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardFileEntityRepository extends JpaRepository<BoardFileEntity , String > {
    @Query(value = "select * from boardfile where bno_fk = :bno",nativeQuery = true)
    List<BoardFileEntity> findByBnoSql(int bno);

}
