package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", n -> n != null && n >= min && n <= max);
        return this;
    }
}
