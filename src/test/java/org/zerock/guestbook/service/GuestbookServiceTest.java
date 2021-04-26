package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.dto.PageResultDto;
import org.zerock.guestbook.entity.Guestbook;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDto guestbookDto = GuestbookDto.builder()
                .title("Sample Title......")
                .content("Sample Content")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDto));
    }

    @Test
    public void testList(){

        PageRequestDto pageRequestDto = PageRequestDto.builder().page(1).size(10).build();

        PageResultDto<GuestbookDto, Guestbook> resultDto = service.getList(pageRequestDto);

        System.out.println("PREV : " + resultDto.isPrev());
        System.out.println("NEXT : " + resultDto.isNext());
        System.out.println("TOTAL : " + resultDto.getTotalPage());
        System.out.println("------------------------------------");

        for (GuestbookDto guestbookDto : resultDto.getDtoList()) {
            System.out.println(guestbookDto);
        }

        System.out.println("======================================");
        resultDto.getPageList().forEach(i -> System.out.println(i));
    }

}