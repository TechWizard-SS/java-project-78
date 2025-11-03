package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer expectedSize = null;
    private Map<String, BaseSchema> fields = null;  // ← Raw BaseSchema (для совместимости с typed тестами)

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.expectedSize = size;
        return this;
    }

    @SuppressWarnings("unchecked")  // ← Suppress unchecked для raw + typed Map
    public MapSchema shape(Map<String, BaseSchema> schemas) {  // ← Raw BaseSchema (принимает BaseSchema<String> из теста)
        this.fields = new HashMap<>(schemas);
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
        if (!(value instanceof Map)) {
            return false;
        }
        Map mapValue = (Map) value;
        if (expectedSize != null && mapValue.size() != expectedSize) {
            return false;
        }
        if (fields != null) {
            for (Map.Entry<String, BaseSchema> entry : fields.entrySet()) {  // Raw loop
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();  // Raw
                Object val = mapValue.get(key);
                boolean valid = schema.isValid(val);  // Object ok
                if (!valid) {
                    return false;
                }
            }
        }
        return true;
    }
}
