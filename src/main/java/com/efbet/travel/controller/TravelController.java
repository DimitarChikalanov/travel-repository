package com.efbet.travel.controller;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;
import com.efbet.travel.service.travel.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping("/travel")
    @ResponseStatus(HttpStatus.CREATED)
    public TravelResponseModel startTravel(@RequestBody TravelRequestModel model){
        return this.travelService.doTravel(model);
    }
}
