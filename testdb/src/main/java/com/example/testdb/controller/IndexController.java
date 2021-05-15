package com.example.testdb.controller;

import com.example.testdb.dto.PageRequestDto;
import com.example.testdb.dto.UserBoardDto;
import com.example.testdb.service.UserBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Log4j2
@RequestMapping("/testdb/")
@RequiredArgsConstructor
public class IndexController {

    private final UserBoardService userBoardService;

    @GetMapping("/index")
    public void index() {
        log.info("Index Page");

//        return "/index";
    }

    @GetMapping("/event")
    public void event() {
        log.info("Event Page");
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto) {
        log.info("List Page");
    }

    @GetMapping("/board")
    public void board(Model model) {
        log.info("Board Page");
    }

    @GetMapping("/intro")
    public void intro() {
        log.info("intro");
    }

    @GetMapping("/deadline")
    public void deadline() {
        log.info("deadline");

    }

    @GetMapping("/register")
    public void register(){
        log.info("register Page");
    }

    @PostMapping("/register")
    public String registerPost(UserBoardDto dto, RedirectAttributes redirectAttributes) {
        log.info(" 글 등록 ..");

        Long bno = userBoardService.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/testdb/list";
    }
}
