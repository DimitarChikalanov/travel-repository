package com.efbet.travel.repository;

import com.efbet.travel.domain.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CountryRepository extends JpaRepository<Country, String> {

    Country findByName(String countryName);

    Set<Country> findByCountryCode(String countryCode);
}
