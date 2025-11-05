package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Базовый класс для всех схем валидации.
 * Содержит общие методы и флаг обязательности значения.
 *
 * @param <T> тип данных, с которым работает схема
 */
public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    /**
     * Добавляет новое правило проверки в схему.
     *
     * @param name имя проверки
     * @param validate предикат для проверки значения
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Проверяет значение по всем добавленным условиям.
     * Принимает Object, чтобы можно было вызывать isValid на BaseSchema<?>.
     *
     * @param value проверяемое значение
     * @return true, если все проверки пройдены
     */
    @SuppressWarnings("unchecked")
    public final boolean isValid(Object value) {
        if (value == null && !required) {
            return true;
        }
        if (value == null) {
            return false;
        }

        T val = (T) value;

        for (Predicate<T> check : checks.values()) {
            if (!check.test(val)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Помечает схему как обязательную.
     *
     * @return этот экземпляр схемы для объединения методов в цепочку
     */
    public BaseSchema<T> required() {
        required = true;
        return this;
    }
}
