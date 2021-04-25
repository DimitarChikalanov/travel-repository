package com.efbet.travel.service.travel;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface TravelService {

    TravelResponseModel doTravel(TravelRequestModel model);

    ByteArrayInputStream exportCsv(String username) throws IOException;
}
