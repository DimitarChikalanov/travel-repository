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

    public TravelServiceImpl(TravelRepository travelRepository,
                             CountryService countryService,
                             CurrencyService currencyService,
                             NeighborRepository neighborRepository,
                             CountryNeighbouringApiClient countryNeighbouringApiClient,
                             UserService userService
    ) {
        this.travelRepository = travelRepository;
        this.countryService = countryService;
        this.currencyService = currencyService;
        this.neighborRepository = neighborRepository;
        this.countryNeighbouringApiClient = countryNeighbouringApiClient;
        this.userService = userService;
    }


    @Override
    public TravelResponseModel doTravel(TravelRequestModel model) {
        TravelResponseModel travelResponseModel = new TravelResponseModel();
        User user = this.userService.getUser(model.getUserName());
        travelResponseModel.setUsername(user.getUsername());
        Country country = this.countryService.getCountryByName(model.getStartCountry());
        travelResponseModel.setStartCountry(country.getName());
        Travel travel = new Travel();
        travel.setStartingCountry(model.getStartCountry());
        travel.setUser(user);
        travel.setCountry(country);
        travel.setCurrencyCode(model.getCurrencyName());
        travel.setTotalBudget(model.getBudget());
        travel.setBudgetPerCountry(model.getBudgetPerCountry());



        if (this.neighborRepository.getNeighbourCountry(country.getId()) == 0) {
            CountryNeighbouringModel[] countryNeighbouringModels = this.countryNeighbouringApiClient.
                    getNeighboring(country.getCountryCode(), "json");
            Arrays.stream(countryNeighbouringModels).forEach(k -> {
                Neighbour neighbour = new Neighbour();
                neighbour.setCountry(country);
                neighbour.setCountryName(k.getCountry_name());
                neighbour.setCountryCode(k.getCountry_code());
                this.neighborRepository.saveAndFlush(neighbour);
            });
        }

        BigDecimal expense = model.getBudgetPerCountry().
                multiply(BigDecimal.valueOf(this.neighborRepository.getNeighbourCountry(country.getId())));

        if (model.getBudget().divide(expense, MathContext.DECIMAL32).compareTo(BigDecimal.valueOf(1L)) <1) {
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

        Set<Neighbour> neighbourSet = this.neighborRepository.findByCountry(country);
        Set<NeighbourResponseModel> neighbourResponseModels = new HashSet<>();

        for (Neighbour neighbour : neighbourSet) {
            NeighbourResponseModel neighbourResponseModel = new NeighbourResponseModel();
            this.countryService.getCountrySet(neighbour.getCountryCode()).forEach((key, value) -> {
                this.travelRepository.saveAndFlush(travel);
                neighbourResponseModel.setName(key);
                neighbourResponseModel.setCurrencyCode(value);
                neighbourResponseModel.setValue(this.currencyService.convertCurrency(model.getCurrencyName(),
                                this.countryService.getCountryByName(key).getCurrencyCode(),
                                model.getBudgetPerCountry()));
            });
            neighbourResponseModels.add(neighbourResponseModel);
        }

        travelResponseModel.setNeighbourResponseModels(neighbourResponseModels);

        return travelResponseModel;
    }

}
