package ru.neoflex.vacationcalculator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neoflex.vacationcalculator.model.VacationCalculationResponse;
import ru.neoflex.vacationcalculator.service.VacationCalculatorService;
import java.util.List;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;

    @Autowired
    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    @GetMapping
    public VacationCalculationResponse calculate(@RequestParam double averageSalary,
                                                @RequestParam int vacationDays,
                                                @RequestParam(required = false) List<String> vacationDates) {
        return vacationCalculatorService.calculate(averageSalary, vacationDays, vacationDates);
    }
}
