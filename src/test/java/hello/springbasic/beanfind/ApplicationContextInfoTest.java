package hello.springbasic.beanfind;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.springbasic.AppConfig;
import hello.springbasic.member.MemberService;
import hello.springbasic.member.MemberServiceImpl;
import hello.springbasic.order.OrderService;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object  = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {

                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object  = " + bean);
            }
        }
    }

    @Test
    @DisplayName("이름과 타입으로 조회")
    void findBeanByNameAndType() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("타입만으로 조회")
    void findBeanByType() {
        MemberService bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }

    void findBeanByType2() {
        MemberService bean = ac.getBean(MemberServiceImpl.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("없는 이름으로 조회")
    void findBeanByWrongName() {
        assertThatThrownBy(() -> ac.getBean("wrongName", MemberService.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);

    }

    @Test
    @DisplayName("이름과 타입이 일치하지 않는 조회")
    void findBeanByUnmatchedNameAndType() {
        assertThatThrownBy(() -> ac.getBean("memberService", OrderService.class))
                .isInstanceOf(BeanNotOfRequiredTypeException.class);
    }
}
