package org.zerock.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDto;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

//    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll...........");
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMemberDto) {
        log.info("exMember................");

        log.info("-------------------------");
        log.info(clubAuthMemberDto);

    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin() {
        log.info("Admin.............");
    }
//
//    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq \"user95@zerock.org\"")
//    @GetMapping("/exOnly")
//    public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMember) {
//
//        log.info("exMemberOnly");
//        log.info(clubAuthMember);
//
//        return "/sample/admin";
//    }
}
