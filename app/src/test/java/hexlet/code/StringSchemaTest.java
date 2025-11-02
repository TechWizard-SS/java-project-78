package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    @Test
    public void testBasicValidation() {
        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    public void testMinLength() {
        var v = new Validator();
        var schema = v.string().required().minLength(5);

        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    public void testContains() {
        var v = new Validator();
        var schema = v.string().required().contains("hex");

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("let"));
        assertFalse(schema.isValid("HEXLET"));
    }

    @Test
    public void testChainingAndOverride() {
        var v = new Validator();
        var schema = v.string().minLength(10).minLength(4);

        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    public void testAllRulesTogether() {
        var v = new Validator();
        var schema = v.string().required().minLength(6).contains("let");

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("hello world"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }
}
