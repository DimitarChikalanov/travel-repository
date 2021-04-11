package com.efbet.travel.service.country;

import com.efbet.travel.domain.entity.Country;

import java.util.Set;

public interface CountryService {

    Country getCountryByName(String name);

    Set<Country> getCountrySet(String countryCode);
}
