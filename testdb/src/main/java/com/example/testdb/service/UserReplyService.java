package com.example.testdb.service;

import com.example.testdb.dto.UserReplyDto;
import com.example.testdb.entity.UserBoard;
import com.example.testdb.entity.UserReply;

import java.util.List;

public interface UserReplyService {

    Long register(UserReplyDto dto);

    List<UserReplyDto> get(Long bno);          // List 로 가져오는 이유는 .. 게시글bno 하나 가져올때 있는 댓글 전부 다 가져와야하니까 ㅇㅇ!

    void modify(UserReplyDto dto);

    void remove(Long rno);


    default UserReply dtoToEntity(UserReplyDto dto) {
        UserBoard board = UserBoard.builder().bno(dto.getBno()).build();

        UserReply entity = UserReply.builder()
                .rno(dto.getRno())
                .content(dto.getContent())
                .writer(dto.getWriter())            // getSession 으로 가져올거임 나중에 수정 ㄱ
                .board(board)
                .build();
        return entity;
    }

    default UserReplyDto entityToDto(UserReply entity) {
        UserReplyDto dto = UserReplyDto.builder()
                .rno(entity.getRno())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
