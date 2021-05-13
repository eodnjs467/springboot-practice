package com.example.testdb.repository;

import com.example.testdb.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoRepositoryTest {

    @Autowired
    UserInfoRepository repository;

    @Test
    public void insert(){
        UserInfo userInfo = UserInfo.builder()
                .uId("admin")
                .password("admin")
                .name("관리자")
                .build();
        System.out.println(repository.save(userInfo));

    }

}