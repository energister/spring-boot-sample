package hello.testconfiguration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfigurationOption2Test.TestConfig.class)
public class TestConfigurationOption2Test extends TestConfigurationTestBase {

    public static class TestConfig {

        @Bean
        public TestBean testBean() {
            return new TestBean();
        }
    }
}
