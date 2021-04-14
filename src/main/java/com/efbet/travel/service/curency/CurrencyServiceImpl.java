package com.efbet.travel.service.curency;

import com.efbet.travel.api.CurrencyApiClient;
import com.efbet.travel.domain.entity.Currency;
import com.efbet.travel.domain.model.client.ResponseCurrencyModel;
import com.efbet.travel.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class CurrencyServiceImpl implements CurrencyService {


    private final CurrencyRepository currencyRepository;
    private final CurrencyApiClient currencyApiClient;
    private final String api_key;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository,
                               CurrencyApiClient currencyApiClient,
                               @Value("${country.api.key}") String api_key) {
        this.currencyRepository = currencyRepository;
        this.currencyApiClient = currencyApiClient;
        this.api_key = api_key;
    }

    @Override
    public Currency findByCurrencyCode(String currencyCode) {
        return this.currencyRepository.findByCurrencyCode(currencyCode);
    }

    @Override
    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal sum) {

        if (toCurrency == null){
            toCurrency = "USD";
        }

        Currency currencyTo = this.findByCurrencyCode(toCurrency);
        Currency currencyFrom = this.findByCurrencyCode(fromCurrency);

        if (fromCurrency.equals("USD") && toCurrency.equals("USD")) {
            return sum;
        } else if (fromCurrency.equals("USD")) {
            double exchangeResult = currencyTo.getRates().doubleValue() * sum.doubleValue();
            return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
        }

        double exchangeResult = (currencyTo.getRates().doubleValue() / (currencyFrom.getRates().doubleValue()) * (sum.doubleValue()));
        return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
    }

    @PostConstruct
    @Scheduled(cron = "0 0 05 * * ?")
    public void saveCurrency() {
        ResponseCurrencyModel receive = this.currencyApiClient.getLatestCurrencyRate(api_key);
        this.currencyRepository.deleteAll();
        receive.getResponse().getRates().forEach((key, value) -> {
            Currency currency = new Currency();
            currency.setCurrencyCode(key);
            currency.setRates(value);
            currency.setBase(receive.getResponse().getBase());
            currency.setRefreshTime(receive.getResponse().getDate());
            this.currencyRepository.saveAndFlush(currency);
        });
    }

}
