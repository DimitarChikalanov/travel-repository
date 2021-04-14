package com.efbet.travel.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "travels")
public class Travel extends BaseEntity {

    private String startingCountry;

    private BigDecimal budget;

    private BigDecimal budgetPerCountry;

    private int travelAround;

    private String currencyCode;

    private User user;

    private Country country;

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

    public int getTravelAround() {
        return travelAround;
    }

    public void setTravelAround(int travelAround) {
        this.travelAround = travelAround;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
