package com.example.testdb.repository;

import com.example.testdb.entity.UserBoard;
import com.example.testdb.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserBoardRepositoryTest {

    @Autowired
    private UserBoardRepository repository;

    @Test
    public void board_insert(){
        UserInfo user = UserInfo.builder().uId("admin2").build();

        UserBoard board = UserBoard.builder()
                .title("보드 테스트")
                .content("테스트")
                .writer(user)
                .build();

        repository.save(board);
    }
}
