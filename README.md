# Vacation Calculator — подробный отчет и описание проекта

## Описание проекта

Микросервис на Spring Boot (Java 11), реализующий расчет суммы отпускных по среднему заработку за 12 месяцев. Поддерживается как простой расчет по количеству дней, так и расчет с учетом конкретных дат отпуска (выходные и праздники не оплачиваются).

---

## Важные моменты и тонкости реализации

[Перейти к инструкции пользователя (User_instructions.md)](./User_instructions.md)

1. **Расчет отпускных ведется по формуле:**
   - **Среднедневная зарплата:**
     ```
     Среднедневная = Средняя зарплата за 12 мес / 12 / 29.3
     ```
     (29.3 — усредненное число дней в месяце по ТК РФ)
   - **Сумма отпускных:**
     ```
     Отпускные = Среднедневная × Количество оплачиваемых дней
     ```

2. **Если не указаны конкретные даты отпуска:**
   - Оплачиваются все запрошенные дни (например, 14 дней).

3. **Если указаны даты отпуска:**
   - В расчет включаются только рабочие дни (выходные и праздники не оплачиваются).
   - Праздники задаются в HolidayUtils (можно расширить список).

4. **Особенности работы в WSL:**
   - Для доступа к сервису из браузера Windows по localhost необходимо добавить `server.address=0.0.0.0` в `application.properties`.
   - Иногда требуется использовать IP WSL (например, http://172.28.84.180:8080).

---

## Примеры расчетов

### 1. Простой расчет (без учета дат)
- Запрос:
  ```
  http://localhost:8080/calculate?averageSalary=60000&vacationDays=14
  ```
- Расчет:
  - Среднедневная: 60000 / 12 / 29.3 = 170.65
  - Отпускные: 170.65 × 14 = 2389.10
- Ответ:
  ```json
  { "amount": 2389.08 }
  ```

### 2. Расчет с конкретными датами
- Запрос:
  ```
  http://localhost:8080/calculate?averageSalary=60000&vacationDays=14&vacationDates=2025-01-01&vacationDates=2025-01-04&vacationDates=2025-01-06&vacationDates=2025-01-07&vacationDates=2025-01-11&vacationDates=2025-01-12&vacationDates=2025-01-13&vacationDates=2025-01-14&vacationDates=2025-01-18&vacationDates=2025-01-19&vacationDates=2025-01-20&vacationDates=2025-01-21&vacationDates=2025-01-25&vacationDates=2025-01-27
  ```
- Если из 14 дат 5 — рабочие, расчет:
  - Среднедневная: 170.65
  - Отпускные: 170.65 × 5 = 853.25
- Ответ:
  ```json
  { "amount": 853.25 }
  ```

---

## Юнит-тесты (листинг и пояснения)

**VacationCalculatorServiceTest.java:**
```java
@Test
void testCalculateSimple() {
    double salary = 60000.0;
    int days = 10;
    VacationCalculationResponse resp = service.calculate(salary, days, null);
    double expected = Math.round((salary / 12.0 / 29.3 * days) * 100.0) / 100.0;
    assertEquals(expected, resp.getAmount());
}

@Test
void testCalculateWithDates() {
    double salary = 60000.0;
    int days = 5;
    var dates = Arrays.asList("2025-04-14", "2025-04-15", "2025-04-19", "2025-04-20", "2025-04-16");
    VacationCalculationResponse resp = service.calculate(salary, days, dates);
    // Только Mon, Tue, Wed — рабочие дни
    double expected = Math.round((salary / 12.0 / 29.3 * 3) * 100.0) / 100.0;
    assertEquals(expected, resp.getAmount());
}

@Test
void testCalculateWithHoliday() {
    double salary = 60000.0;
    int days = 2;
    var dates = Arrays.asList("2025-01-01", "2025-01-02"); // праздники
    VacationCalculationResponse resp = service.calculate(salary, days, dates);
    // Оба дня — праздники, оплачиваемых дней нет
    assertEquals(0.0, resp.getAmount());
}

@Test
void testCalculateEmptyDates() {
    double salary = 60000.0;
    int days = 5;
    VacationCalculationResponse resp = service.calculate(salary, days, Collections.emptyList());
    double expected = Math.round((salary / 12.0 / 29.3 * days) * 100.0) / 100.0;
    assertEquals(expected, resp.getAmount());
}
```

**Результаты тестов:**
- Все тесты проходят успешно (`BUILD SUCCESS`).
- Проверяются как простые случаи, так и варианты с праздниками, выходными и пустым списком дат.

---

## Вывод

- Проект реализует корректный расчет отпускных по российским стандартам.
- Все расчеты и исключения (выходные, праздники) покрыты юнит-тестами.
- Проект готов к использованию и расширению (например, добавление новых праздников, интеграция с базой данных и др.).
