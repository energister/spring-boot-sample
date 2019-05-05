package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static hello.BeansInjectionTest.TestConfig.EXPLICIT_BEAN_NAME;
import static hello.BeanInjectionTestConfiguration.FACTORY_BEAN_NAME;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * See
 *   java - @Resource vs @Autowired - Stack Overflow
 *   https://stackoverflow.com/questions/4093504/resource-vs-autowired
 * and
 *   Spring Injection with @Resource, @Autowired and @Inject – Source Allies
 *   https://www.sourceallies.com/2011/08/spring-injection-with-resource-and-autowired/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeansInjectionTest {

    @TestConfiguration
    public static class TestConfig {

        public static final String EXPLICIT_BEAN_NAME = "explicitBeanName";

        public static final String EXPLICIT_BEAN_VALUE = "bean name set explicitly by the @Bean, not by the method name";

        @Bean(name = EXPLICIT_BEAN_NAME)
        public SimpleBean simpleBean() {
            return new SimpleBean(EXPLICIT_BEAN_VALUE);
        }
    }

    // Bean that is injected because it is marked as @Primary
    // @Autowired annotation wires by type
    @Autowired
    private SimpleBean bean1;

    // @Resource annotation wires by bean name
    @Resource(name = EXPLICIT_BEAN_NAME)
    private SimpleBean bean2;

    // Bean that is created through the FactoryBean
    // (note that its name is actually a factory's bean name)
    @Resource(name = FACTORY_BEAN_NAME)
    private SimpleBean bean3;

    // The way to inject factory itself
    @Resource(name = "&" + FACTORY_BEAN_NAME)
    private SimpleBeanFactory factory;

    @Test
    public void beansAreResolvedCorrectly() {
        assertNotNull(bean1);
        assertThat(bean1.getValue(), equalTo(BeanInjectionTestConfiguration.PRIMARY_BEAN_VALUE));

        assertNotNull(bean2);
        assertThat(bean2.getValue(), equalTo(TestConfig.EXPLICIT_BEAN_VALUE));

        assertNotNull(bean3);
        assertThat(bean3.getValue(), equalTo(SimpleBeanFactory.FACTORY_CREATED_BEAN_VALUE));

        assertNotNull(factory);
    }
}