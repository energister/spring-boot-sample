package hello.injection2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * See also:
 *    Spring boot 2.1 bean override vs. Primary
 *    https://stackoverflow.com/questions/53139244/spring-boot-2-1-bean-override-vs-primary
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OverrideBeanInTest {

    @TestConfiguration
    public static class TestConfig {

        static final String TEST_BEAN_VALUE = "bean has been instantiated in tests";

        @Primary
        @Bean
        public Injection2Bean newInjection2Bean() {
            return new Injection2Bean(TEST_BEAN_VALUE);
        }
    }

    @Autowired
    private Injection2Bean bean;

    @Test
    public void test() {
        assertThat(bean.getValue(), equalTo(TestConfig.TEST_BEAN_VALUE));
    }
}
