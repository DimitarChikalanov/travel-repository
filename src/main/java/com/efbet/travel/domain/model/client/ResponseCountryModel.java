package com.efbet.travel.domain.model.client;

import com.efbet.travel.domain.model.client.CurrencyCodeModel;

public class ResponseCountryModel {

    private String name;

    private String alpha2Code;

    public CurrencyCodeModel[] currencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public CurrencyCodeModel[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(CurrencyCodeModel[] currencies) {
        this.currencies = currencies;
    }
}
