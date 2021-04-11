package com.efbet.travel.domain.model;

import java.math.BigDecimal;
import java.util.Set;

public class TravelResponseModel {

    private String username;

    private String startCountry;

    private int numberOfTours;

    private BigDecimal leftOver;

    private Set<NeighbourResponseModel> neighbourResponseModels;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(String startCountry) {
        this.startCountry = startCountry;
    }

    public int getNumberOfTours() {
        return numberOfTours;
    }

    public void setNumberOfTours(int numberOfTours) {
        this.numberOfTours = numberOfTours;
    }

    public BigDecimal getLeftOver() {
        return leftOver;
    }

    public void setLeftOver(BigDecimal leftOver) {
        this.leftOver = leftOver;
    }

    public Set<NeighbourResponseModel> getNeighbourResponseModels() {
        return neighbourResponseModels;
    }

    public void setNeighbourResponseModels(Set<NeighbourResponseModel> neighbourResponseModels) {
        this.neighbourResponseModels = neighbourResponseModels;
    }
}
