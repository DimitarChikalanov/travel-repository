package com.efbet.travel.controller;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;
import com.efbet.travel.service.travel.TravelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping("/travel")
    public TravelResponseModel startTravel(@RequestBody TravelRequestModel model){
        return this.travelService.doTravel(model);
    }
}
