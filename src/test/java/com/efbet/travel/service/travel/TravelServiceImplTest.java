package com.efbet.travel.service.travel;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;



@SpringBootTest
class TravelServiceImplTest {

    @Autowired
    private TravelService travelService;
    private UserService userService;


    @Test
    public void startTripTest(){
        TravelRequestModel travelRequestModel = new TravelRequestModel();
        travelRequestModel.setUsername("admin");
        travelRequestModel.setStartingCountry("Georgia");
        travelRequestModel.setBudget(BigDecimal.valueOf(4000));
        travelRequestModel.setBudgetPerCountry(BigDecimal.valueOf(500));
        travelRequestModel.setCurrencyCode("EUR");

       var result =  travelService.doTravel(travelRequestModel);

        Assertions.assertEquals(result.getUsername(), "admin");
        Assertions.assertEquals(result.getStartingCountry(),"Georgia");
        Assertions.assertEquals(result.getVisitsNeighbour().stream().count(),4);

    }

    @Test
    public void startingTripOnSmallBudget(){
        TravelRequestModel travelRequestModel = new TravelRequestModel();
        travelRequestModel.setUsername("admin");
        travelRequestModel.setStartingCountry("Brazil");
        travelRequestModel.setBudget(BigDecimal.valueOf(4000));
        travelRequestModel.setBudgetPerCountry(BigDecimal.valueOf(5500));
        travelRequestModel.setCurrencyCode("EUR");

        var result =  travelService.doTravel(travelRequestModel);

        Assertions.assertEquals(result.getUsername(), "admin");
        Assertions.assertEquals(result.getNumberOfTours(),0);
    }
}
