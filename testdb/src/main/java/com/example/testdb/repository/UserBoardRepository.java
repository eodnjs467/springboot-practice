package com.example.testdb.repository;

import com.example.testdb.entity.UserBoard;
import com.example.testdb.repository.search.SearchBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBoardRepository extends JpaRepository<UserBoard, Long>, SearchBoardRepository {

    @Query("select b, w from UserBoard b left join b.writer w  where b.bno =:bno")
    Object getUserBoardWithWriter(@Param("bno") Long bno);

    @Query("select b, r from UserBoard b left join UserReply r on r.board=b where b.bno=:bno")
    List<Object[]> getUserBoardWithReply(@Param("bno") Long bno);

//    @Query(value = "select b, w, count(r) " +
//            "from Board b " +
//            "left join b.writer w " +
//            "left join Reply r on r.board=b " +
//            "GROUP BY b ",
//            countQuery = "select count(b) from Board b")
//    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w from UserBoard b left join b.writer w left outer join UserReply r on r.board = b where b.bno=:bno")
    Object getUserBoardByBno(@Param("bno") Long bno);
}
