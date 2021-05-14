package com.example.testdb.repository;

import com.example.testdb.entity.UserBoard;
import com.example.testdb.entity.UserReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReplyRepository extends JpaRepository<UserReply, Long> {

    @Query("select b, r from UserBoard b left join UserReply r on r.board = b where b.bno=:bno")
    List<Object[]> getUserBoardWithReply(@Param("bno") Long bno);

    @Modifying
    @Query("delete from UserReply r where r.board.bno =: bno")
    void deleteByBno(Long bno);

    List<UserReply> getUserRepliesByBoardOrderByRno(UserBoard board);


}
