package hello.testconfiguration;

import hello.SimpleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

abstract class TestConfigurationTestBase {

    protected static class SomeTestBean {
    }

    @Autowired
    private SomeTestBean bean;

    @Autowired
    private SimpleService service;

    @Test
    public void injected() {
        assertNotNull(bean);
        assertNotNull(service);
    }
}
