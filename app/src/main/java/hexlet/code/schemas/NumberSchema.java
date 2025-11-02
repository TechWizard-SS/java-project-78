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

    public NumberSchema range(int lower, int upper) {  // ← Переименуй для Checkstyle
        if (lower > upper) {
            throw new IllegalArgumentException("min should not be greater than max");
        }
        this.min = lower;
        this.max = upper;
        return this;
    }

    @Override
    public boolean isValid(Object value) {  // ← Object (override)
        if (value == null && !required) {
            return true;
        }
        if (value == null) {
            return false;
        }
        if (!(value instanceof Integer)) {  // ← Edge: wrong type → false
            return false;
        }
        Integer num = (Integer) value;  // Cast после check

        if (positive && num <= 0) {
            return false;
        }
        if (this.min != null && num < this.min) {
            return false;
        }
        if (this.max != null && num > this.max) {
            return false;
        }
        return true;
    }
}
