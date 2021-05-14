package com.example.testdb.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBoardDto {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate, modDate;
}
