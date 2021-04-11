package com.efbet.travel.api;

import com.efbet.travel.domain.model.client.ResponseCurrencyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currencyApi", url = "https://api.ratesapi.io/api")
public interface CurrencyApiClient {

    @GetMapping("/latest")
    ResponseCurrencyModel getLatestCurrencyRate();
}
