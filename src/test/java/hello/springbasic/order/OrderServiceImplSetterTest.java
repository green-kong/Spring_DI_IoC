package hello.springbasic.order;

import org.junit.jupiter.api.Test;

import hello.springbasic.discount.RateDiscountPolicy;
import hello.springbasic.member.MemoryMemberRepository;

public class OrderServiceImplSetterTest {
    @Test
    void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());

        orderService.createOrder(1L, "itemA", 10000);
    }
}
