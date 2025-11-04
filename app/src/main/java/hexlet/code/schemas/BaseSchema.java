package hexlet.code.schemas;

/**
 * Базовый класс для всех схем валидации.
 * Содержит общие методы и флаг обязательности значения.
 *
 * @param <T> тип данных, с которым работает схема
 */
public abstract class BaseSchema<T> {
    protected boolean required = false;

    /**
     * Делает поле обязательным для проверки.
     *
     * @return текущий объект схемы
     */
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    /**
     * Проверяет корректность значения в зависимости от типа схемы.
     *
     * @param value значение для проверки
     * @return true, если значение корректно, иначе false
     */
    public abstract boolean isValid(Object value);
}