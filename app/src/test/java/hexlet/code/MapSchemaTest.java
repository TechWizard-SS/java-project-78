package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    public void testBasicValidation() {
        var v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null));

    }

    @Test
    public void testBasicValidation1() {
        var v = new Validator();
        var schema = v.map();

        schema.required();

        assertFalse(schema.isValid(null));

    }

    @Test
    public void testBasicValidation2() {
        var v = new Validator();
        var schema = v.map();

        schema.isValid(new HashMap<>());

        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testBasicValidation3() {
        var v = new Validator();
        var schema = v.map();

        var data = new HashMap<String, String>();
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testBasicValidation4() {
        var v = new Validator();
        var schema = v.map();

        var data = new HashMap<String, String>();
        data.put("key2", "value2");
        schema.sizeof(2);

        assertFalse(schema.isValid(data));
    }

    @Test
    public void testBasicValidation5() {
        var v = new Validator();
        var schema = v.map();

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        schema.sizeof(2);

        assertTrue(schema.isValid(data));
    }
}
