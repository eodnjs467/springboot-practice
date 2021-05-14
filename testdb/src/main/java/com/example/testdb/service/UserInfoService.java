package com.example.testdb.service;

import com.example.testdb.dto.UserInfoDto;
import com.example.testdb.entity.UserInfo;

public interface UserInfoService {

    String register(UserInfoDto dto);

    UserInfoDto get(String uId);

    void delete(String uId);

    void modify(UserInfoDto dto);


    default UserInfo dtoToEntity(UserInfoDto dto) {
        UserInfo userInfo = UserInfo.builder()
                .uId(dto.getUId())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
        return userInfo;
    }

    default UserInfoDto EntityToDto(UserInfo entity) {
        UserInfoDto dto = UserInfoDto.builder()
                .name(entity.getName())
                .password(entity.getPassword())
                .uId(entity.getUId())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;

    }
}
