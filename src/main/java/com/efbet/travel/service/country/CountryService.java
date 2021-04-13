package com.efbet.travel.service.country;

import com.efbet.travel.domain.entity.Country;
import com.efbet.travel.repository.CurrencyCodeView;

import java.util.HashMap;
import java.util.Set;

public interface CountryService {

    Country getCountryByName(String name);

    HashMap<String, String> getCountrySet(String countryCode);
}
