package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    public void testBasicValidation() {
        var v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void testPositive() {
        var v = new Validator();
        var schema = v.number().required().positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));   // 0 не положительное
        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(100));
    }

    @Test
    public void testRange() {
        var v = new Validator();
        var schema = v.number().required().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testCombinedRules() {
        var v = new Validator();
        var schema = v.number().required().positive().range(1, 100);

        assertTrue(schema.isValid(50));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(101));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testNonRequiredWithRules() {
        var v = new Validator();
        var schema = v.number().positive().range(10, 20);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(5));
        assertTrue(schema.isValid(15));
    }
}
