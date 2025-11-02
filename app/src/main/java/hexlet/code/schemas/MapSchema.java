package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer expectedSize = null;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.expectedSize = size;
        return this;
    }

    @Override
    public boolean isValid(Map value) {
        // null валиден только без required
        if (value == null && !required) {
            return true;
        }
        // null невалиден с required
        if (value == null) {
            return false;
        }
        // sizeof проверяется для любого non-null Map
        if (expectedSize != null && value.size() != expectedSize) {
            return false;
        }
        return true;
    }
}
