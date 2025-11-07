package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> fields = null;

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
        this.fields = new HashMap<>(schemas);
        addCheck("shape", map -> {
            for (var entry : fields.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object val = map.get(key);

                boolean valid = ((BaseSchema<Object>) schema).isValid(val);
                if (!valid) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
