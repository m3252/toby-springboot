package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test
@interface UnitTest {
}


class HelloServiceTest {

    @UnitTest
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();

        String ret = simpleHelloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    @FastUnitTest
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("test");

        assertThat(ret).isEqualTo("*test*");
    }

}