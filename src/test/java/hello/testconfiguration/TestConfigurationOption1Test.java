package hello.testconfiguration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConfigurationOption1Test extends TestConfigurationTestBase {

    /**
     * Note {@code @TestConfiguration} annotation.
     * Also note that the class is {@code static}.
     *
     * Warning:
     * doesn't work if configuration classes are specified explicitly as
     * (@SpringBootTest(classes = { Application.class }))
     */
    @TestConfiguration
    public static class TestConfig {

        @Bean
        public SomeTestBean testBean() {
            return new SomeTestBean();
        }
    }
}
