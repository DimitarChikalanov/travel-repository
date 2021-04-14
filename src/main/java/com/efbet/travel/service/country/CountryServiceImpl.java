package com.efbet.travel.service.country;

import com.efbet.travel.api.CountryApiClient;
import com.efbet.travel.domain.entity.Country;
import com.efbet.travel.domain.model.client.ResponseCountryModel;
import com.efbet.travel.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;

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
    public HashMap<String, String> getCurrencyCode(String countryCode) {
        HashMap<String, String> country = new HashMap<>();
        this.countryRepository.getCountryByCountryCode(countryCode).forEach(currencyCodeView -> {
            country.putIfAbsent(currencyCodeView.getCurrencyCode(), currencyCodeView.getName());
        });
        return country;
    }

    @PostConstruct
    public void saveCountryInDb() {
        if (this.countryRepository.count() == 0) {
            ResponseCountryModel[] receive = this.countryApiClient.getAllEuCountry();
            Arrays.stream(receive).forEach(countryModel -> {
                Country country = new Country();
                country.setName(countryModel.getName());
                country.setCountryCode(countryModel.getAlpha2Code());
                Arrays.stream(countryModel.getCurrencies()).forEach(codeModel -> {
                    country.setCurrencyCode(codeModel.getCode());
                    this.countryRepository.saveAndFlush(country);
                });
            });
        }
    }
}
