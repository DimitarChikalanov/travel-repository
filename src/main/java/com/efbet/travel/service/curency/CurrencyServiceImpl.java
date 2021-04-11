package com.efbet.travel.service.curency;

import com.efbet.travel.api.CurrencyApiClient;
import com.efbet.travel.domain.entity.Currency;
import com.efbet.travel.domain.model.client.ResponseCurrencyModel;
import com.efbet.travel.repository.CurrencyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class CurrencyServiceImpl implements CurrencyService {


    private final CurrencyRepository currencyRepository;
    private final CurrencyApiClient currencyApiClient;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository,
                               CurrencyApiClient currencyApiClient) {
        this.currencyRepository = currencyRepository;
        this.currencyApiClient = currencyApiClient;
    }

    @Override
    public Currency findByCurrencyCode(String currencyCode) {
        return this.currencyRepository.findByCurrencyCode(currencyCode);
    }

    @Override
    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal sum) {
        if (fromCurrency.equals("EUR") && toCurrency.equals("EUR")){
            return sum;
        }
        else if (fromCurrency.equals("EUR")){
            Currency currencyTo = this.findByCurrencyCode(toCurrency);
            double exchangeResult = currencyTo.getRates().doubleValue() * sum.doubleValue();
            return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
        }
        Currency currencyTo = this.findByCurrencyCode(toCurrency);

        Currency currencyFrom = this.findByCurrencyCode(fromCurrency);
        double exchangeResult = (currencyTo.getRates().doubleValue() / (currencyFrom.getRates().doubleValue()) * (sum.doubleValue()));
        return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
    }

    @PostConstruct
    @Scheduled(cron = "0 0 05 * * ?")
    public void saveCurrency() {
        ResponseCurrencyModel receive = this.currencyApiClient.getLatestCurrencyRate();
        this.currencyRepository.deleteAll();
        receive.getRates().forEach((key, value) -> {
            Currency currency = new Currency();
            currency.setCurrencyCode(key);
            currency.setRates(value);
            currency.setBase(receive.getBase());
            currency.setRefreshTime(receive.getDate());
            this.currencyRepository.saveAndFlush(currency);
        });
    }

}
