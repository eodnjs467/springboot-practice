package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BookForm {

    private Long id;    // 상품 수정 때문에 id 값 받아야함
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
