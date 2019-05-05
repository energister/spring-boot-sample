package hello;

import org.springframework.beans.factory.FactoryBean;

public class SimpleBeanFactory implements FactoryBean<SimpleBean> {

    public static final String FACTORY_CREATED_BEAN_VALUE = "bean created by the factory";

    @Override
    public SimpleBean getObject() throws Exception {
        return new SimpleBean(FACTORY_CREATED_BEAN_VALUE);
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleBean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
