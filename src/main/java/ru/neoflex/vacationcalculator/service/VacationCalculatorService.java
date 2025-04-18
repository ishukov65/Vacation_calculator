package ru.neoflex.vacationcalculator.service;

import org.springframework.stereotype.Service;
import ru.neoflex.vacationcalculator.model.VacationCalculationResponse;
import ru.neoflex.vacationcalculator.util.HolidayUtils;
import java.time.LocalDate;
import java.util.List;

@Service
public class VacationCalculatorService {
    public VacationCalculationResponse calculate(double averageSalary, int vacationDays, List<String> vacationDates) {
        double dailySalary = averageSalary / 12.0 / 29.3;
        int payableDays = vacationDays;
        if (vacationDates != null && !vacationDates.isEmpty()) {
            payableDays = 0;
            for (String dateStr : vacationDates) {
                LocalDate date = LocalDate.parse(dateStr);
                if (HolidayUtils.isWorkingDay(date)) {
                    payableDays++;
                }
            }
        }
        double amount = dailySalary * payableDays;
        return new VacationCalculationResponse(Math.round(amount * 100.0) / 100.0);
    }
}
