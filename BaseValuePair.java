public class BaseValuePair {
    private int base;
    private String value;

    public BaseValuePair(int base, String value) {
        this.base = base;
        this.value = value;
    }

    public int getBase() {
        return base;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Base: " + base + ", Value: " + value;
    }
}
