package com.example.testdb.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReplyDto {

    private Long rno;

    private String content;

    private String writer;

    private Long bno;

    private LocalDateTime regDate, modDate;

}
