package com.example.testdb.service;

import com.example.testdb.dto.UserBoardDto;
import com.example.testdb.entity.UserBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserBoardServiceTest {

    @Autowired
    UserBoardService service;

    @Test
    public void registerBoard() {
        //given
        UserBoardDto dto = UserBoardDto.builder()
                .title("board service Test4")
                .content("Test3")
                .writer("admin")
                .build();

        //when
        service.register(dto);

        //then

    }

    @Test
    public void modifyBoard(){
        //given
        UserBoardDto dto = UserBoardDto.builder()
                .bno(3L)
                .title("Modify Board Title!")
                .content("Modify Board Content")
                .build();
        //when
        service.modify(dto);

        //then

    }


    @Test
    public void removeBoard(){
        service.remove(3L);
    }

    @Test
    public void getBoard(){

        System.out.println(service.get(3L));
    }

}