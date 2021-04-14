package com.efbet.travel.repository;

import com.efbet.travel.domain.entity.Country;
import com.efbet.travel.repository.view.CountryView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface CountryRepository extends JpaRepository<Country, String> {

    Country findByName(String countryName);

    HashSet<CountryView> getCountryByCountryCode(String countryCode);
}
