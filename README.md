### Hexlet tests and linter status:
[![Actions Status](https://github.com/TechWizard-SS/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/TechWizard-SS/java-project-78/actions)


[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=TechWizard-SS_java-project-78&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=TechWizard-SS_java-project-78)

# Java Project 78 — Декларативная валидация данных

**Java Project 78** — это мини-библиотека для декларативной валидации данных.  
Она позволяет создавать схемы проверки (`string`, `number`, `map`) и применять к ним гибкие правила.  
Библиотека демонстрирует работу с дженериками, функциональными интерфейсами, лямбдами и коллекциями в Java.  

Проект создан в рамках курса **«Java-разработчик»** от [Hexlet](https://ru.hexlet.io).

---

## Основные возможности

### Проверка строк
```java
string().required()     // обязательное поле
        .minLength(5)   // минимальная длина
        .contains("hex") // содержит подстроку
```

### Проверка чисел
```java
number().required()     // обязательное поле
        .positive()     // положительное
        .range(5, 10)   // диапазон
```

### Проверка карт (Map)
```java
map().required()        // обязательное поле
     .sizeof(2)         // точный размер
     .shape(...)        // валидация вложенных структур
```

> `shape()` позволяет задавать схемы для каждого ключа карты — рекурсивная валидация вложенных объектов.

---

## Установка и запуск

### 1. Клонировать репозиторий
```bash
git clone https://github.com/TechWizard-SS/java-project-78.git
cd java-project-78
```

### 2. Сборка проекта
```bash
./gradlew build
```

### 3. Запуск тестов
```bash
./gradlew test
```

---

## Пример использования

```java
import hexlet.code.Validator;

public class Example {
    public static void main(String[] args) {
        var v = new Validator();

        // Проверка строк
        var stringSchema = v.string().required().minLength(5).contains("hex");
        System.out.println(stringSchema.isValid("hexlet")); // true
        System.out.println(stringSchema.isValid("java"));  // false

        // Проверка чисел
        var numberSchema = v.number().required().positive().range(5, 10);
        System.out.println(numberSchema.isValid(7));  // true
        System.out.println(numberSchema.isValid(-5)); // false

        // Проверка Map
        var mapSchema = v.map().required().sizeof(2);
        Map<String, String> data = Map.of("key1", "value1", "key2", "value2");
        System.out.println(mapSchema.isValid(data)); // true
    }
}
```

---

## Технологии

- **Java 21**
- **Gradle** — система сборки
- **JUnit 5** — тестирование
- **JaCoCo** — измерение покрытия тестами
- **Checkstyle** — проверка стиля кода
- **SonarCloud** — статический анализ кода
