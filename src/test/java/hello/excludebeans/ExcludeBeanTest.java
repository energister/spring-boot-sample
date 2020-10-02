package hello.excludebeans;

import hello.SimpleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TypeExcludeFilters(SimpleServiceExcludeFilter.class)
public class ExcludeBeanTest {

    @Autowired(required = false)
    private SimpleService service;

    @Test
    public void beanIsFilteredOut() {
        assertNull(service);
    }
}
