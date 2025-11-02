package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer expectedSize = null;
    private Map<String, BaseSchema> fields = null;  // Raw BaseSchema для под-схем (StringSchema/NumberSchema)

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.expectedSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {  // Raw для простоты
        this.fields = new HashMap<>(schemas);  // Копируем
        return this;
    }

    @Override
    public boolean isValid(Map value) {  // Raw Map value
        // Базовая логика (твоя, без изменений)
        if (value == null && !required) {
            return true;
        }
        if (value == null) {
            return false;
        }
        if (expectedSize != null && value.size() != expectedSize) {
            return false;
        }
        // Shape: проверяем ожидаемые ключи
        if (fields != null) {
            for (Map.Entry<String, BaseSchema> entry : fields.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object val = value.get(key);  // Может быть String/Integer/null

                @SuppressWarnings("unchecked")
                boolean valid = schema.isValid(val);
                if (!valid) {
                    return false;
                }
            }
        }

        return true;
    }
}
