package com.efbet.travel.service.travel;

import com.efbet.travel.domain.model.TravelRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravelServiceImplTest {

    @Autowired
    private TravelService travelService;

    @Test
    public void createTravelTest(){
        TravelRequestModel travelRequestModel = new TravelRequestModel();
        travelRequestModel.setUserName("admin");
        travelRequestModel.setStartingCountry("Georgia");
        travelRequestModel.setBudget(BigDecimal.valueOf(4000));
        travelRequestModel.setBudgetPerCountry(BigDecimal.valueOf(500));
        travelRequestModel.setCurrencyCode("EUR");

       var result =  travelService.doTravel(travelRequestModel);

        System.out.println(result.getNumberOfTours());
    }
}
