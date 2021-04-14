package com.efbet.travel.api;

import com.efbet.travel.domain.model.client.ResponseCurrencyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currencyApi", url = "https://api.currencyscoop.com/v1")
public interface CurrencyApiClient {

    @GetMapping("/latest")
    ResponseCurrencyModel getLatestCurrencyRate(@RequestParam String api_key);
}
