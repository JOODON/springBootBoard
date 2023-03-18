package com.example.springBootBoard.repository;

import com.example.springBootBoard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    //조회수 증가 쿼리는
    //update board_table set board_hits=board_hits+1 where id=?
    //Entity기준 쿼리! native=?룰하면 native 기준으로도 가능함
    @Modifying //update 쿼리문을 실행할때 붙혀주기
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id")Long id);

}
