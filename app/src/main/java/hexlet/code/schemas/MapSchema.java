package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", m -> m.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object value = map.get(key);
                boolean valid = ((BaseSchema<Object>) schema).isValid(value);
                if (!valid) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
