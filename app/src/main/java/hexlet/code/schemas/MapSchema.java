package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer expectedSize = null;
    private Map<String, BaseSchema<?>> fields = null;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.expectedSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.fields = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        // Сначала null-логика (специальный случай)
        if (value == null && !required) {
            return true;
        }
        if (value == null) {
            return false;
        }

        // Потом type-check + cast (для non-null)
        if (!(value instanceof Map)) {
            return false;  // Edge: wrong type
        }
        Map mapValue = (Map) value;

        // Базовая логика для non-null Map
        if (expectedSize != null && mapValue.size() != expectedSize) {
            return false;
        }

        // Shape для non-null Map
        if (fields != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : fields.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object val = mapValue.get(key);

                boolean valid = schema.isValid(val);
                if (!valid) {
                    return false;
                }
            }
        }
        return true;
    }
}
