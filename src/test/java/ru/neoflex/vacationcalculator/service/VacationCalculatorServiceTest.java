package ru.neoflex.vacationcalculator.service;

import org.junit.jupiter.api.Test;
import ru.neoflex.vacationcalculator.model.VacationCalculationResponse;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class VacationCalculatorServiceTest {
    private final VacationCalculatorService service = new VacationCalculatorService();

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
        // 2025-04-14 (Mon), 2025-04-15 (Tue), 2025-04-19 (Sat), 2025-04-20 (Sun), 2025-04-16 (Wed)
        var dates = Arrays.asList("2025-04-14", "2025-04-15", "2025-04-19", "2025-04-20", "2025-04-16");
        VacationCalculationResponse resp = service.calculate(salary, days, dates);
        // Only Mon, Tue, Wed are working days
        double expected = Math.round((salary / 12.0 / 29.3 * 3) * 100.0) / 100.0;
        assertEquals(expected, resp.getAmount());
    }

    @Test
    void testCalculateWithHoliday() {
        double salary = 60000.0;
        int days = 2;
        // 2025-01-01 (holiday), 2025-01-02 (holiday)
        var dates = Arrays.asList("2025-01-01", "2025-01-02");
        VacationCalculationResponse resp = service.calculate(salary, days, dates);
        // Both are holidays, so 0 payable days
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
}
