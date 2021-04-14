package com.efbet.travel.service.country;

import com.efbet.travel.domain.entity.Country;

import java.util.HashMap;

public interface CountryService {

    Country getCountryByName(String name);

    HashMap<String, String> getCurrencyCode(String countryCode);

}
