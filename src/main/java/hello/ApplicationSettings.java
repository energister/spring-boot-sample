package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "application")
@Validated
public class ApplicationSettings {

    @Value("${application.test.value}")
    private String testValue;

    public String getTestValue() {
        return testValue;
    }
}
