package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Базовый класс для всех схем валидации.
 * Содержит набор проверок в виде предикатов и метод isValid для их выполнения.
 *
 * @param <T> тип данных, с которым работает схема
 */
public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    /**
     * Добавляет новое правило проверки.
     *
     * @param name имя проверки (для идентификации)
     * @param validate предикат проверки
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Делает схему обязательной (null недопустим).
     *
     * @return текущий объект схемы
     */
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    /**
     * Проверяет значение по всем предикатам.
     * Принимает <T> для совместимости с shape (val from map.get).
     *
     * @param value значение для проверки
     * @return true, если все проверки пройдены
     */
    public final boolean isValid(T value) {
        if (value == null && !required) {
            return true;
        }
        if (value == null) {
            return false;
        }

        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
