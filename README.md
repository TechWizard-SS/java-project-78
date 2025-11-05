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

Описание проекта

Java Project 78 — это мини-библиотека для декларативной валидации данных.
Она позволяет создавать схемы проверки (string, number, map) и применять к ним гибкие правила.

Библиотека демонстрирует работу с дженериками, функциональными интерфейсами, лямбдами и коллекциями в Java.
Проект создан в рамках курса «Java-разработчик» от Hexlet.

Основные возможности

Проверка строк: required(), minLength(), contains()

Проверка чисел: required(), positive(), range()

Проверка карт (Map): required(), sizeof(), shape()
(где shape позволяет валидировать вложенные структуры)

## Установка и запуск
### Клонировать репозиторий
git clone https://github.com/TechWizard-SS/java-project-78.git
cd java-project-78

### Сборка и запуск тестов
./gradlew build
./gradlew test

### Пример использования
import hexlet.code.Validator;

var v = new Validator();

// Проверка строк
var stringSchema = v.string().required().minLength(5).contains("hex");
System.out.println(stringSchema.isValid("hexlet")); // true

// Проверка чисел
var numberSchema = v.number().required().positive().range(5, 10);
System.out.println(numberSchema.isValid(7)); // true

// Проверка Map
var mapSchema = v.map().sizeof(2);

### Технологии
Java 21
Gradle
JUnit 5
JaCoCo (покрытие тестами)
Checkstyle
SonarCloud
