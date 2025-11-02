package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String contains = null;


    @Override
    public StringSchema required() {  // для сохранения типа, иначе возвращается BaseSchema<String>
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
    public boolean isValid(String value) {
        // null или empty валидны только без required
        if (!required && (value == null || value.isEmpty())) {
            return true;
        }
        // null или empty невалидны с required
        if (value == null || value.isEmpty()) {
            return false;
        }
        // Правила применяются для non-null non-empty
        if (minLength != null && value.length() < minLength) {
            return false;
        }
        if (contains != null && !value.contains(contains)) {
            return false;
        }
        return true;
    }
}
