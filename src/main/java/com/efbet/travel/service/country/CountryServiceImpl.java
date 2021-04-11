package com.efbet.travel.service.country;

import com.efbet.travel.api.CountryApiClient;
import com.efbet.travel.domain.entity.Country;
import com.efbet.travel.domain.model.client.ResponseCountryModel;
import com.efbet.travel.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryApiClient countryApiClient;

    public CountryServiceImpl(CountryRepository countryRepository,
                              CountryApiClient countryApiClient
    ) {
        this.countryRepository = countryRepository;
        this.countryApiClient = countryApiClient;
    }

    @Override
    public Country getCountryByName(String name) {
        return this.countryRepository.findByName(name);
    }

    @Override
    public Set<Country> getCountrySet(String countryCode) {
        return this.countryRepository.findByCountryCode(countryCode);
    }

    @PostConstruct
    public void saveCountryInDb() {
        if (this.countryRepository.count() == 0) {
            ResponseCountryModel[] receive = this.countryApiClient.getAllEuCountry();
            Arrays.stream(receive).forEach(e -> {
                Country country = new Country();
                country.setName(e.getName());
                country.setCountryCode(e.getAlpha2Code());
                Arrays.stream(e.getCurrencies()).forEach(k -> {
                    country.setCurrencyCode(k.getCode());
                    this.countryRepository.saveAndFlush(country);
                });
            });
        }
    }
}
