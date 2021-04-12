package com.efbet.travel.api;

import com.efbet.travel.domain.model.client.ResponseCurrencyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currencyApi", url = "https://api.currencyscoop.com/v1")
public interface CurrencyApiClient {

    @GetMapping("/latest?api_key=c3b57cb8e63ff6292714543dce8f40d7")
    ResponseCurrencyModel getLatestCurrencyRate();
}
