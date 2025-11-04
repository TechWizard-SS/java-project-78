package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<String, Object>> {

    private Map<String, BaseSchema<?>> fields;

    @Override
    public MapSchema required() {
        super.required();
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", m -> m == null || m.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.fields = new HashMap<>(schemas);
        addCheck("shape", map -> {
            if (map == null) {
                return true;
            }
            for (var entry : fields.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object val = map.get(key);
                if (!schema.isValid(val)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
