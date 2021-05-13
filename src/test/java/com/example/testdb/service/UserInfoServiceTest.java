package com.example.testdb.service;

import com.example.testdb.dto.UserInfoDto;
import com.example.testdb.entity.UserInfo;
import com.example.testdb.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoService service;

    @Autowired
    UserInfoRepository repository;

    @Test
    public void testInsert() {
        UserInfoDto dto = UserInfoDto.builder()
                .uId("admin3")
                .password("admin3")
                .name("admin3")
                .build();

        System.out.println(service.register(dto));

    }

    @Test
    public void testGet(){
        String uId = "admin";
        System.out.println(service.get(uId));

        Optional<UserInfo> result = repository.findById(uId);

        System.out.println("---------------------_");
        System.out.println(result);

    }

    @Test
    public void testModify() {

        UserInfoDto dto = UserInfoDto.builder()
                .uId("tester1")
                .name("tester1Modify")
                .password("testerModify")
                .build();

        service.modify(dto);
        service.get(dto.getUId());

    }

    @Test
    public void testDelete(){
        service.delete("tester2");
    }
}