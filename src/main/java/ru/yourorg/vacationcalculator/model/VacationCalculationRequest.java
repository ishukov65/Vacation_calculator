package ru.yourorg.vacationcalculator.model;

import java.util.List;

public class VacationCalculationRequest {
    private double averageSalary;
    private int vacationDays;
    private List<String> vacationDates;

    public VacationCalculationRequest() {}

    public VacationCalculationRequest(double averageSalary, int vacationDays, List<String> vacationDates) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.vacationDates = vacationDates;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public List<String> getVacationDates() {
        return vacationDates;
    }

    public void setVacationDates(List<String> vacationDates) {
        this.vacationDates = vacationDates;
    }
}
