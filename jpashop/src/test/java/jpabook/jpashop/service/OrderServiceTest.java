package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService service;

    @Autowired
    OrderRepository repository;

    @Test
    public void 상품주문() throws Exception{        //주문상태, 주문종류 수 , 총가격, 재고수량
        //given
        Member member = createMember();

        Item item = createBook("Spring JPA", 10000, 10);

        //when
        int orderCount = 2;
        Long orderId = service.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = repository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(8, item.getStockQuantity());

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("Spring JPA", 10000, 10);

        int orderCount = 10;

        //when
        try {
            service.order(member.getId(), item.getId(), orderCount);
        } catch (NotEnoughStockException e) {
            System.out.println("재고 수량이 부족합니다.");
        }

        //then
        fail("재고 수량 부족 예외가 발생해야함");

    }



    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("Spring JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = service.order(member.getId(), item.getId(), orderCount);

        //when
        service.cancel(orderId);

        //then
        Order getOrder = repository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getStockQuantity());

    }










    private Book createBook(String name, int orderPrice, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(orderPrice);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}
