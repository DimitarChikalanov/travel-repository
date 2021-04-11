package com.efbet.travel.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    private String currencyCode;

    private BigDecimal rates;

    private String base;

    private LocalDate refreshTime;

    @Column(nullable = false)
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Column(nullable = false)
    public BigDecimal getRates() {
        return rates;
    }

    public void setRates(BigDecimal rates) {
        this.rates = rates;
    }

    @Column(nullable = false)
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(LocalDate refreshTime) {
        this.refreshTime = refreshTime;
    }
}
