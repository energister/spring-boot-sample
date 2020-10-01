package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * See
 *    java - Does Spring @Transactional attribute work on a private method? - Stack Overflow
 *    https://stackoverflow.com/questions/4396284/does-spring-transactional-attribute-work-on-a-private-method
 * and
 *    java - Spring @Transaction method call by the method within the same class, does not work? - Stack Overflow
 *    https://stackoverflow.com/questions/3423972/spring-transaction-method-call-by-the-method-within-the-same-class-does-not-wo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTest {
    @Autowired
    ServiceWithTransactionalMethod service;

    @Test
    public void directCall() {
        final boolean executedInsideTransaction = service.transactional();

        // why does it works without @EnableTransactionManagement on application?
        assertTrue(executedInsideTransaction);
    }

    @Test
    public void indirectCall() {
        final boolean executedInsideTransaction = service.indirectTransactional();

        // doesn't work on private methods
        assertFalse(executedInsideTransaction);
    }
}
