package hello.injection2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OverrideBeanInTests2 {

    // Note: no @Configuration annotation is required
    public static class TestConfig {

        static final String TEST_BEAN_VALUE = "bean has been instantiated in tests";

        @Bean
        // Note: bean has exact name and type as in the main app OverrideBeanInTestConfiguration
        public Injection2Bean injection2Bean() {
            return new Injection2Bean(TEST_BEAN_VALUE);
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {

        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
        childContext.setId("OverrideBeanInTests2 context");
        childContext.setParent(applicationContext);
        childContext.register(TestConfig.class);
        childContext.refresh();

        Injection2Bean bean = childContext.getBean(Injection2Bean.class);

        assertThat(bean.getValue(), equalTo(OverrideBeanInTest1.TestConfig.TEST_BEAN_VALUE));

        childContext.close();
    }
}
