package com.efbet.travel.domain.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class TravelRequestModel {

    @NotBlank
    private String userName;

    @NotBlank
    private String startingCountry;

    @DecimalMin(value = "0.0")
    private BigDecimal budget;

    @DecimalMin(value = "0.0")
    private BigDecimal budgetPerCountry;

    @NotBlank
    private String currencyCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartingCountry() {
        return startingCountry;
    }

    public void setStartingCountry(String startingCountry) {
        this.startingCountry = startingCountry;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getBudgetPerCountry() {
        return budgetPerCountry;
    }

    public void setBudgetPerCountry(BigDecimal budgetPerCountry) {
        this.budgetPerCountry = budgetPerCountry;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
