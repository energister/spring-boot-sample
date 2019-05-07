package hello.testconfiguration;

import hello.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, TestConfigurationOption3Test.TestConfig.class})
public class TestConfigurationOption3Test extends TestConfigurationTestBase {

    public static class TestConfig {

        @Bean
        public SomeTestBean testBean() {
            return new SomeTestBean();
        }
    }
}
