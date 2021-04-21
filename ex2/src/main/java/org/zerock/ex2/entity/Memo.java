package org.zerock.ex2.entity;

import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_memo")
@ToString
@Getter
@Builder    // 객체를 생성할 수 있게해줌.
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;




}
