package com.efbet.travel.service.curency;


import com.efbet.travel.domain.entity.Currency;

import java.math.BigDecimal;

public interface CurrencyService {

    Currency findByCurrencyCode(String currencyCode);

    BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal sum);
}
