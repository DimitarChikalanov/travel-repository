package com.efbet.travel.controller;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;
import com.efbet.travel.service.travel.TravelService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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


    @GetMapping("/travel/{name}")
    public ResponseEntity<?> getCsv(@PathVariable String name) throws IOException {
        String filename = name.concat(".csv");
        InputStreamResource file = new InputStreamResource(this.travelService.exportCsv(name));

        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename).
                contentType(MediaType.parseMediaType("application/csv")).
                body(file);
    }
}
