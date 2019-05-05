package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    public static final String GREETING = "Greetings from Spring Boot!";

    @RequestMapping("/")
    public String index() {
        return GREETING;
    }

}