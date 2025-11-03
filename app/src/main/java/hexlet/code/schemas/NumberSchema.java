package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer min = null;
    private Integer max = null;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int lower, int upper) {
        if (lower > upper) {
            throw new IllegalArgumentException("min should not be greater than max");
        }
        this.min = lower;
        this.max = upper;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null && !required) {
            return true;
        }
        if (value == null) {
            return false;
        }
        if (!(value instanceof Integer)) {
            return false;  // Edge: wrong type
        }
        Integer num = (Integer) value;
        if (positive && num <= 0) {
            return false;
        }
        if (min != null && num < min) {
            return false;
        }
        if (max != null && num > max) {
            return false;
        }
        return true;
    }
}
