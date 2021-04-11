package com.efbet.travel.api;

import com.efbet.travel.domain.model.client.CountryNeighbouringModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "neighbouringApi", url = "https://api.geodatasource.com/")
public interface CountryNeighbouringApiClient {

    @GetMapping("neighbouring-countries?key=FP80RMTWCQNMDQQQSHUXDLLYVXUDGLWR")
    CountryNeighbouringModel[] getNeighboring(@RequestParam String country_code, @RequestParam String format);
}
