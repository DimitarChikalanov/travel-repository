package com.efbet.travel.controller;

import com.efbet.travel.domain.model.TravelRequestModel;
import com.efbet.travel.domain.model.TravelResponseModel;
import com.efbet.travel.service.travel.TravelService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { ApplicationConfig.class })
@WebMvcTest(controllers = TravelController.class)
class TravelControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TravelService travelService;

    @Autowired
    private Gson gson;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void startTravelTestStatus201() throws Exception {
        TravelRequestModel travelRequestModel = new TravelRequestModel();
        travelRequestModel.setUserName("admin");
        travelRequestModel.setStartingCountry("Georgia");
        travelRequestModel.setBudget(BigDecimal.valueOf(4000));
        travelRequestModel.setBudgetPerCountry(BigDecimal.valueOf(500));
        travelRequestModel.setCurrencyCode("EUR");

        mockMvc.perform(post("/travel").
                content(gson.toJson(travelRequestModel)).
                contentType("application/json")).
                andExpect(status().isCreated());
    }
}
