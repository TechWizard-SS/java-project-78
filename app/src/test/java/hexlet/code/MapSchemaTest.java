//package hexlet.code;
//
//import hexlet.code.schemas.BaseSchema;
//import org.junit.jupiter.api.Test;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class MapSchemaTest {
//
//    @Test
//    public void testValidNullWithoutRequired() {
//        var v = new Validator();
//        var schema = v.map();
//
//        assertTrue(schema.isValid(null));
//    }
//
//    @Test
//    public void testInvalidNullWithRequired() {
//        var v = new Validator();
//        var schema = v.map().required();
//
//        assertFalse(schema.isValid(null));
//    }
//
//    @Test
//    public void testValidEmptyMapWithoutRequired() {
//        var v = new Validator();
//        var schema = v.map();
//
//        assertTrue(schema.isValid(new HashMap<>()));
//    }
//
//    @Test
//    public void testValidNonEmptyMapWithoutRequired() {
//        var v = new Validator();
//        var schema = v.map();
//
//        var data = new HashMap<String, String>();
//        data.put("key1", "value1");
//        assertTrue(schema.isValid(data));
//    }
//
//    @Test
//    public void testInvalidSizeWithSizeof() {
//        var v = new Validator();
//        var schema = v.map().sizeof(2);
//
//        var data = new HashMap<String, String>();
//        data.put("key1", "value1");
//        assertFalse(schema.isValid(data));
//    }
//
//    @Test
//    public void testValidSizeWithSizeof() {
//        var v = new Validator();
//        var schema = v.map().sizeof(2);
//
//        var data = new HashMap<String, String>();
//        data.put("key1", "value1");
//        data.put("key2", "value2");
//        assertTrue(schema.isValid(data));
//    }
//
//    @Test
//    public void testShapeBasicExamples() {
//        var v = new Validator();
//
//        Map<String, BaseSchema<?>> schemas = new HashMap<>();
//        schemas.put("firstName", v.string().required());
//        schemas.put("lastName", v.string().required().minLength(2));
//
//        var schema = v.map().shape(schemas);
//
//        Map<String, String> human1 = new HashMap<>();
//        human1.put("firstName", "John");
//        human1.put("lastName", "Smith");
//        assertTrue(schema.isValid(human1));
//
//        Map<String, String> human2 = new HashMap<>();
//        human2.put("firstName", "John");
//        human2.put("lastName", null);
//        assertFalse(schema.isValid(human2));
//
//        Map<String, String> human3 = new HashMap<>();
//        human3.put("firstName", "Anna");
//        human3.put("lastName", "B");
//        assertFalse(schema.isValid(human3));
//    }
//
//    @Test
//    public void testShapeWithNumberSchema() {
//        var v = new Validator();
//
//        Map<String, BaseSchema<?>> schemas = new HashMap<>();
//        schemas.put("name", v.string().required());
//        schemas.put("age", v.number().positive().range(18, 65));
//
//        var schema = v.map().shape(schemas);
//
//        Map<String, Object> human = new HashMap<>();
//        human.put("name", "John");
//        human.put("age", 30);
//        assertTrue(schema.isValid(human));
//
//        human.put("age", 10);
//        assertFalse(schema.isValid(human));
//    }
//
//    @Test
//    public void testShapeCombinedWithSizeof() {
//        var v = new Validator();
//
//        Map<String, BaseSchema<?>> schemas = new HashMap<>();  // ← Фикс: wildcard ?
//        schemas.put("firstName", v.string().required());
//        schemas.put("lastName", v.string().required());
//
//        var schema = v.map().required().sizeof(2).shape(schemas);
//
//        Map<String, String> human = new HashMap<>();
//        human.put("firstName", "John");
//        human.put("lastName", "Smith");
//        assertTrue(schema.isValid(human));
//
//        human.put("age", "30");
//        assertFalse(schema.isValid(human));
//    }
//
//    @Test
//    public void testShapeWithMissingAndExtraKeys() {
//        var v = new Validator();
//
//        Map<String, BaseSchema<?>> schemas = new HashMap<>();  // ← Фикс: wildcard ?
//        schemas.put("firstName", v.string().required());
//
//        var schema = v.map().shape(schemas);
//
//        Map<String, String> human = new HashMap<>();
//        human.put("lastName", "Smith");
//        assertFalse(schema.isValid(human));
//
//        human.put("firstName", "John");
//        human.put("extra", "ignored");
//        assertTrue(schema.isValid(human));
//    }
//
//    @Test
//    public void testRequiredCombinedWithSizeof() {
//        var v = new Validator();
//        var schema = v.map().required().sizeof(0);
//
//        assertTrue(schema.isValid(new HashMap<>()));
//
//        var data = new HashMap<String, String>();
//        data.put("key1", "value1");
//        assertFalse(schema.isValid(data));
//
//        schema.sizeof(1);
//        assertTrue(schema.isValid(data));
//    }
//
//    // Опционально: тест на override shape (для полного покрытия)
//    @Test
//    public void testShapeOverride() {
//        var v = new Validator();
//        var schema = v.map();
//
//        Map<String, BaseSchema<?>> schemas1 = new HashMap<>();
//        schemas1.put("key1", v.string().required());
//        schema.shape(schemas1);
//
//        Map<String, BaseSchema<?>> schemas2 = new HashMap<>();
//        schemas2.put("key2", v.string().minLength(3));
//        schema.shape(schemas2);  // Override
//
//        Map<String, String> data = new HashMap<>();
//        data.put("key1", "a");  // extra, ignored
//        data.put("key2", "abc");
//        assertTrue(schema.isValid(data));
//
//        data.put("key2", "ab");  // <3
//        assertFalse(schema.isValid(data));
//    }
//}