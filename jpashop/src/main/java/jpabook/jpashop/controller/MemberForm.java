package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NonNull
    private String name;

    private String city;
    private String street;
    private String zipcode;


}
