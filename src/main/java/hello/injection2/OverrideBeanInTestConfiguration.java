package hello.injection2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OverrideBeanInTestConfiguration {

    @Bean
    public Injection2Bean injection2Bean() {
        return new Injection2Bean("main code value");
    }
}
