# Калькулятор отпускных — Инструкция пользователя

[Перейти к подробному описанию проекта и техническому отчету (README.md)](./README.md)

---

## 1. Требования к окружению

Перед запуском убедитесь, что на вашем компьютере установлены:
- **Java 11**
- **Maven** (или другой совместимый сборщик)

### Как установить Java 11
- Скачайте JDK 11 с сайта: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- Установите JDK, следуя инструкции установщика
- Добавьте JAVA_HOME в переменные среды (для Windows, если не добавилось автоматически)
- Проверьте установку:
  ```sh
  java -version
  ```

### Как установить Maven
- Скачайте с https://maven.apache.org/download.cgi или используйте `sudo apt install maven` в Linux
- Распакуйте архив, добавьте папку `bin` в PATH (Windows)
- Проверьте установку:
  ```sh
  mvn -version
  ```

---

## 2. Скачивание проекта

**Вариант 1. Через git:**
```sh
git clone https://github.com/ishukov65/Vacation_calculator.git
cd Vacation_calculator
```
**Вариант 2. Скопировать файлы вручную:**  
Скачайте все файлы проекта в одну папку.

---

## 3. Сборка и запуск приложения

1. Откройте терминал/командную строку в папке проекта.
2. Выполните команду:
   ```sh
   mvn clean install
   ```
3. Запустите приложение:
   ```sh
   mvn spring-boot:run
   ```
   или (после сборки):
   ```sh
   java -jar target/course_java-0.0.1-SNAPSHOT.jar
   ```

По умолчанию приложение будет доступно на [http://localhost:8080](http://localhost:8080)

---

## 4. Использование API

### Эндпоинт
```
GET /calculate
```

**Параметры:**
- `averageSalary` — средняя зарплата за 12 месяцев (например, 60000)
- `vacationDays` — количество дней отпуска (например, 10)
- `vacationDates` — (опционально, список дат отпуска в формате `YYYY-MM-DD`)

### Примеры

**Простой расчет:**
```sh
http://localhost:8080/calculate?averageSalary=60000&vacationDays=10
```

**Расчет с учетом конкретных дат:**
```sh
http://localhost:8080/calculate?averageSalary=60000&vacationDays=14&vacationDates=2025-01-01&vacationDates=2025-01-04&vacationDates=2025-01-06&vacationDates=2025-01-07&vacationDates=2025-01-11&vacationDates=2025-01-12&vacationDates=2025-01-13&vacationDates=2025-01-14&vacationDates=2025-01-18&vacationDates=2025-01-19&vacationDates=2025-01-20&vacationDates=2025-01-21&vacationDates=2025-01-25&vacationDates=2025-01-27
```
- Если из 14 дат 5 — рабочие, расчет:
  - Среднедневная: 170.65
  - Отпускные: 170.65 × 5 = 853.25

**Пример ответа:**
```json
{ "amount": 853.25 }
```

---

## 5. Проверка работоспособности

- Откройте браузер и выполните GET-запрос по примеру выше.
- Можно использовать Postman, curl или любой другой HTTP-клиент.

---

## 6. Запуск тестов

```sh
mvn test
```

---

## 7. Примечания

- Праздники и выходные определяются в классе `HolidayUtils` (можно расширить список).
- Все расчеты производятся по формуле по ТК РФ:
  - **Среднедневная:** `averageSalary / 12 / 29.3`
  - **Отпускные:** `среднедневная * количество оплачиваемых дней`

---
