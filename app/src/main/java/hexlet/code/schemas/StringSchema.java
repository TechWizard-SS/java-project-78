package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        addCheck("required", s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", s -> s == null || s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", s -> s != null && s.contains(substring));
        return this;
    }
}
