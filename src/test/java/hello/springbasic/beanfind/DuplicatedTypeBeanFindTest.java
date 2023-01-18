package hello.springbasic.beanfind;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.springbasic.discount.DiscountPolicy;
import hello.springbasic.discount.FixDiscountPolicy;
import hello.springbasic.discount.RateDiscountPolicy;

public class DuplicatedTypeBeanFindTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 중복되는 타입이 있으면 에러발생")
    void findBeanByDuplicatedType() {
        assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("중복되는 타입이 있으면 빈이름을 넣어주면 됨")
    void findBeanByDuplicatedTypeWithName() {
        DiscountPolicy fixDiscountPolicy = ac.getBean("FixDiscountPolicy", DiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("중복되는 타입의 빈 모두 조회")
    void findAllBeansDuplicatedType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
        }
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public DiscountPolicy FixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        public DiscountPolicy RateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }
}
