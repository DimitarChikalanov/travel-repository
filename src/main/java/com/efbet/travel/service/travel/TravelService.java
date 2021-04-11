package com.efbet.travel.service.travel;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;

public interface TravelService {

    TravelResponseModel doTravel(TravelRequestModel model);
}
