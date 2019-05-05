package hello.testconfiguration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

abstract class TestConfigurationTestBase {

    protected static class SomeTestBean {
    }

    @Autowired
    private SomeTestBean bean;

    @Test
    public void injected() {
        assertNotNull(bean);
    }
}
