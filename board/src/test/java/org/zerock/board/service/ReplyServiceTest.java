package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.ReplyDto;
import org.zerock.board.entity.Reply;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList() {
        Long bno = 100L;

        List<ReplyDto> replyDtoList = replyService.getList(bno);

        replyDtoList.forEach(replyDto -> System.out.println(replyDto));
    }

}