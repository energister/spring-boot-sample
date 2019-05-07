package hello.injection2;

public class Injection2Bean {
    private final String value;

    public Injection2Bean(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
