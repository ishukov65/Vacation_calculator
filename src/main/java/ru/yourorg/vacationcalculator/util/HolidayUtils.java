package ru.yourorg.vacationcalculator.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

public class HolidayUtils {
    // Данные по праздникам взяты из портала "Работа в Росии"
    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();
    static {
        int year = 2025;
        // Новогодние каникулы
        HOLIDAYS.add(LocalDate.of(year, 1, 1));
        HOLIDAYS.add(LocalDate.of(year, 1, 2));
        HOLIDAYS.add(LocalDate.of(year, 1, 3));
        HOLIDAYS.add(LocalDate.of(year, 1, 4));
        HOLIDAYS.add(LocalDate.of(year, 1, 5));
        HOLIDAYS.add(LocalDate.of(year, 1, 6));
        HOLIDAYS.add(LocalDate.of(year, 1, 8));
        // Рождество
        HOLIDAYS.add(LocalDate.of(year, 1, 7));
        // 23 февраля (перенос на 8 мая)
        // Международный женский день (перенос на 13 июня)
        // 1 мая
        HOLIDAYS.add(LocalDate.of(year, 5, 1));
        // 2 мая (перенос с 4 января)
        HOLIDAYS.add(LocalDate.of(year, 5, 2));
        // 8 мая (перенос с 23 февраля)
        HOLIDAYS.add(LocalDate.of(year, 5, 8));
        // 9 мая
        HOLIDAYS.add(LocalDate.of(year, 5, 9));
        // 13 июня (перенос с 8 марта)
        HOLIDAYS.add(LocalDate.of(year, 6, 13));
        // 12 июня
        HOLIDAYS.add(LocalDate.of(year, 6, 12));
        // 3 ноября (перенос с 1 ноября)
        HOLIDAYS.add(LocalDate.of(year, 11, 3));
        // 4 ноября
        HOLIDAYS.add(LocalDate.of(year, 11, 4));
        // 31 декабря (перенос с 5 января)
        HOLIDAYS.add(LocalDate.of(year, 12, 31));
    }

    public static boolean isWorkingDay(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }
        return !HOLIDAYS.contains(date);
    }
}
