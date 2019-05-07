package hello.injection1;

public class SimpleBean {
    private String value;

    public SimpleBean(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
