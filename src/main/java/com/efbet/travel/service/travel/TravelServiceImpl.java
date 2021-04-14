package com.efbet.travel.service.travel;

import com.efbet.travel.api.CountryNeighbouringApiClient;
import com.efbet.travel.domain.entity.Country;
import com.efbet.travel.domain.entity.Neighbour;
import com.efbet.travel.domain.entity.Travel;
import com.efbet.travel.domain.entity.User;
import com.efbet.travel.domain.model.NeighbourResponseModel;
import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;
import com.efbet.travel.domain.model.client.CountryNeighbouringModel;
import com.efbet.travel.repository.NeighborRepository;
import com.efbet.travel.repository.TravelRepository;
import com.efbet.travel.service.country.CountryService;
import com.efbet.travel.service.curency.CurrencyService;
import com.efbet.travel.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class TravelServiceImpl implements TravelService {

    private final TravelRepository travelRepository;
    private final CountryService countryService;
    private final CurrencyService currencyService;
    private final NeighborRepository neighborRepository;
    private final CountryNeighbouringApiClient countryNeighbouringApiClient;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public TravelServiceImpl(TravelRepository travelRepository,
                             CountryService countryService,
                             CurrencyService currencyService,
                             NeighborRepository neighborRepository,
                             CountryNeighbouringApiClient countryNeighbouringApiClient,
                             UserService userService,
                             ModelMapper modelMapper
    ) {
        this.travelRepository = travelRepository;
        this.countryService = countryService;
        this.currencyService = currencyService;
        this.neighborRepository = neighborRepository;
        this.countryNeighbouringApiClient = countryNeighbouringApiClient;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public TravelResponseModel doTravel(TravelRequestModel model) {
        User user = this.userService.getUser(model.getUserName());
        Country country = this.countryService.getCountryByName(model.getStartingCountry());
        Travel travel = this.modelMapper.map(model, Travel.class);
        travel.setUser(user);
        travel.setCountry(country);
        TravelResponseModel travelResponseModel = this.modelMapper.map(travel, TravelResponseModel.class);
        travelResponseModel.setUsername(user.getUsername());


        if (this.neighborRepository.getNeighbourCountry(country.getId()) == 0) {
            CountryNeighbouringModel[] countryNeighbouringModels = this.countryNeighbouringApiClient.
                    getNeighboring(country.getCountryCode(), "json");
            Arrays.stream(countryNeighbouringModels).forEach(countryNeighbouringModel -> {
                Neighbour neighbour = new Neighbour();
                neighbour.setCountryCode(countryNeighbouringModel.getCountry_code());
                neighbour.setCountryName(countryNeighbouringModel.getCountry_name());
                neighbour.setCountry(country);
                this.neighborRepository.saveAndFlush(neighbour);
            });
        }

        BigDecimal expense = model.getBudgetPerCountry().
                multiply(BigDecimal.valueOf(this.neighborRepository.getNeighbourCountry(country.getId())));

        if (model.getBudget().divide(expense, MathContext.DECIMAL32).compareTo(BigDecimal.valueOf(1L)) < 1) {
            travel.setTravelAround(0);
            travelResponseModel.setNumberOfTours(0);
            travelResponseModel.setLeftOver(model.getBudget());
        } else {
           long count = this.neighborRepository.getNeighbourCountry(country.getId());
            int around = Math.toIntExact(
                    model.getBudget().intValue() / (model.getBudgetPerCountry().intValue() * count));

            BigDecimal left = model.getBudget().subtract(model.getBudgetPerCountry().
                    multiply(BigDecimal.valueOf(count)));

            travel.setTravelAround(around);
            travelResponseModel.setNumberOfTours(around);
            travelResponseModel.setLeftOver(left);
        }

        this.travelRepository.saveAndFlush(travel);

        Set<Neighbour> neighbourSet = this.neighborRepository.findByCountry(country);
        Set<NeighbourResponseModel> neighbourResponseModels = new HashSet<>();

        neighbourSet.forEach(neighbour -> {
            NeighbourResponseModel neighbourResponseModel = new NeighbourResponseModel();
            this.countryService.getCurrencyCode(neighbour.getCountryCode()).forEach((currencyCode, countryName) -> {


                neighbourResponseModel.setName(countryName);

                if (currencyCode == null) {
                    neighbourResponseModel.setCurrencyCode("USD");
                }else {
                    neighbourResponseModel.setCurrencyCode(countryName);
                }

                neighbourResponseModel.setValue(this.currencyService.convertCurrency(
                        model.getCurrencyCode(),
                        currencyCode,
                        model.getBudgetPerCountry()));
            });
            neighbourResponseModels.add(neighbourResponseModel);
        });

        travelResponseModel.setVisitsNeighbour(neighbourResponseModels);

        return travelResponseModel;
    }
}
