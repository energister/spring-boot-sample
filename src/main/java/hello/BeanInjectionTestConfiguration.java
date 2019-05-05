package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanInjectionTestConfiguration {

    public static final String FACTORY_BEAN_NAME = "factoryBeanName";

    public static final String PRIMARY_BEAN_VALUE = "primary bean";

    @Primary
    @Bean()
    public SimpleBean simpleBean() {
        return new SimpleBean(PRIMARY_BEAN_VALUE);
    }

    @Bean(name = FACTORY_BEAN_NAME)
    public SimpleBeanFactory toolFactory() {
        return new SimpleBeanFactory();
    }
}
