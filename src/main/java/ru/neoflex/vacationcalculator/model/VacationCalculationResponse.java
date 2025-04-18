package ru.neoflex.vacationcalculator.model;

public class VacationCalculationResponse {
    private double amount;

    public VacationCalculationResponse(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
