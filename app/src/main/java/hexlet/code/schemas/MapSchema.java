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
    public boolean isValid(Object value) {  // ← Object (override)
        Map mapValue = (Map) value;  // Cast для Map (safety)
        if (mapValue == null && !required) {
            return true;
        }
        if (mapValue == null) {
            return false;
        }
        if (expectedSize != null && mapValue.size() != expectedSize) {
            return false;
        }

        if (fields != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : fields.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object val = mapValue.get(key);  // Object

                boolean valid = schema.isValid(val);  // ← Без cast/suppress — Object ok
                if (!valid) {
                    return false;
                }
            }
        }
        return true;
    }
}
