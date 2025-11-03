package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String contains = null;

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!required && (value == null || (value instanceof String && ((String) value).isEmpty()))) {
            return true;
        }
        if (value == null) {
            return false;
        }
        if (!(value instanceof String)) {
            return false;  // Edge: wrong type
        }
        String str = (String) value;
        if (str.isEmpty()) {
            return false;
        }
        if (minLength != null && str.length() < minLength) {
            return false;
        }
        if (contains != null && !str.contains(contains)) {
            return false;
        }
        return true;
    }
}
