package com.example.testdb.service;

import com.example.testdb.dto.PageRequestDto;
import com.example.testdb.dto.PageResultDto;
import com.example.testdb.dto.UserBoardDto;
import com.example.testdb.dto.UserReplyDto;
import com.example.testdb.entity.UserBoard;
import com.example.testdb.entity.UserInfo;

public interface UserBoardService {

    Long register(UserBoardDto dto);

    UserBoardDto get(Long bno);

    PageResultDto<UserBoardDto, Object[]> getList(PageRequestDto pageRequestDto);

    void modify(UserBoardDto dto);

    void remove(Long bno);

    default UserBoard dtoToEntity(UserBoardDto dto) {

        UserInfo user = UserInfo.builder().uId(dto.getWriter()).build();

        UserBoard userBoard = UserBoard.builder()
                .bno(dto.getBno())
                .content(dto.getContent())
                .writer(user)
                .title(dto.getTitle())
                .build();
        return userBoard;
    }

    default UserBoardDto entityToDto(UserBoard entity, UserInfo user) {
        UserBoardDto dto = UserBoardDto.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .writer(user.getName())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())       // 생략가능하면 해야딩~
                .build();

        return dto;
    }




}
