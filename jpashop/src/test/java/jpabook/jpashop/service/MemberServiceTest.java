package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;
//    @Autowired EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = service.join(member);

        //then
//        em.flush();
        assertEquals(member, repository.findOne(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        service.join(member1);
        try {
            service.join(member2);
        } catch (IllegalStateException e) {
            return;
        }


        //then
        fail("예외가 발생해야한다.");

    }


}