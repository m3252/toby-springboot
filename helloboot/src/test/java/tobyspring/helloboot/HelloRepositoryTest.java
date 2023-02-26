package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("Moon")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("Moon")).isSameAs(0);

        helloRepository.increaseCount("Moon");
        Assertions.assertThat(helloRepository.countOf("Moon")).isSameAs(1);

        helloRepository.increaseCount("Moon");
        Assertions.assertThat(helloRepository.countOf("Moon")).isSameAs(2);
    }

}