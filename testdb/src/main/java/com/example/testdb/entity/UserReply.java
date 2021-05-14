package com.example.testdb.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "board")
public class UserReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String content;

    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserBoard board;

    public void changeContent(String content) {
        this.content = content;
    }

}
