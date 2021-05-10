package com.example.testdb.service;

import com.example.testdb.dto.UserReplyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserReplyServiceTest {

    @Autowired
    UserReplyService service;

    @Test
    public void testReply() {
        UserReplyDto dto = UserReplyDto.builder()
                .content("reply~~")
                .writer("admin")
                .content("reply Test!!")
                .bno(2L)
                .build();

        System.out.println(service.register(dto));
    }

    @Test
    public void getReply(){
        System.out.println(service.get(2L));
    }

    @Test
    public void changeReply() {
        UserReplyDto dto = UserReplyDto.builder().rno(1L).content("ModifyReply").build();

        service.modify(dto);
    }

    @Test
    public void removeReply() {
        Long rno = 1L;
        service.remove(rno);
    }



}