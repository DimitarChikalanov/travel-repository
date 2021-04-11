package com.efbet.travel.api;

import com.efbet.travel.domain.model.client.ResponseCountryModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "countryName", url = "https://restcountries.eu/rest/v2")
public interface CountryApiClient {

    @GetMapping("/all")
    ResponseCountryModel[] getAllEuCountry();
}
