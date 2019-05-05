package hello;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * This test demonstrates how
 * {@link Before} annotation
 * as long as {@link ClassRule} and {@link Rule} annotations
 * work in JUnit 4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnit4InitializationTest {

    /*
    also take a look at SpringClassRule class
     */

    /*
    AbstractTestExecutionListener

@TestExecutionListeners
https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#testexecutionlisteners

junit4 - What is the difference between @BeforeClass and Spring @TestExecutionListener beforeTestClass() - Stack Overflow
https://stackoverflow.com/questions/12404636/what-is-the-difference-between-beforeclass-and-spring-testexecutionlistener-be

Enhancing Spring Test Framework with beforeClass and afterClass setup - DZone Java
https://dzone.com/articles/enhancing-spring-test
http://saltnlight5.blogspot.com/2012/09/enhancing-spring-test-framework-with.html
     */

    @Autowired
    private static SimpleService staticAutowired;
    private static boolean initializedOnce = false;
    private static Integer beforeEachCallsCounter = 0;
    private static SimpleService staticService;
    private static Object staticContext = null;

    @Autowired
    private SimpleService autowiredService;

    private Object instanceContext = null;

    /**
     * Methods annotated with {@link BeforeClass}
     * as well as fields annotated with the {@link ClassRule}
     * have to be static
     * so they have no access to non-static context (autowired fields)
    */
    @ClassRule
    public static ExternalResource beforeClass = new ExternalResource() {
        @Override
        protected void before() {
            // has no access to autowired properties
            // because they are non-static

            assertFalse(initializedOnce);
            initializedOnce = true;

            // is called before `beforeEach`
            assertNull(staticService);
        }
    };

    /**
     * set up & validate {@link #staticService}
     */
    @Rule
    public ExternalResource beforeEach = new ExternalResource() {
        @Override
        protected void before() {
            // autowired properties has been initialized
            assertNotNull(autowiredService);

            if (staticService == null) {
                staticService = autowiredService;
            }
        }
    };

    /**
     * Set up & validate contexts.
     *
     * {@link Before} is expected to be called before every test
     */
    @Before
    public void initializeOnce() {
        // is called after `beforeEach`
        assertNotNull(staticService);

        beforeEachCallsCounter++;
        if (beforeEachCallsCounter == 1) {
            assertNull(staticContext);
            assertNull(instanceContext);

            staticContext = new Object();
            instanceContext = new Object();
        }
    }

    @Test
    public void A_firstRun() {
        // @Autowired has no effect on static
        assertNull(staticAutowired);

        // `beforeClass` has been called
        assertTrue(initializedOnce);

        // `beforeEach` has been called
        assertNotNull(staticService);

        // initializeOnce() has been called
        assertNotNull(staticContext);

        assertNotNull(autowiredService);
    }

    @Test
    public void B_secondRun() {
        assertNotNull(staticContext);

        // instance variable is empty
        // because new instance of the whole test class is instantiated
        // for every @Test
        assertNull(instanceContext);

        // on every call autowired services are just the same
        assertSame(staticService, autowiredService);
    }
}
